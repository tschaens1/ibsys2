package de.hska.xmlfilemanagement.resource;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.hska.periodmanagement.business.IPeriodRepository;
import de.hska.periodmanagement.domain.Period;
import de.hska.util.FileConverterService;
import de.hska.xmlfilemanagement.domain.JsonFile;
import de.hska.inputmanagement.business.InputService;

@RestController
@RequestMapping(value = "/api/rest/files", produces = MediaType.APPLICATION_JSON_VALUE)
public class JsonFileResource {

	@Autowired
	private IPeriodRepository periodRepository;

	@Autowired
	private FileConverterService fileConverterService;

	@Autowired
	private InputService inputService;

	@RequestMapping(method = RequestMethod.POST)
	public void save(@RequestBody JsonFile jsonFile) {
		String jsonContent = jsonFile.getContent();
		JSONObject jsonObject = fileConverterService.convertXmlToJson(jsonContent);
		System.out.println(jsonObject.toString());
		try {
			Long game = (Long) jsonObject.getJSONObject("results").get("game");
			Long group = (Long) jsonObject.getJSONObject("results").get("group");
			Long periodLong = (Long) jsonObject.getJSONObject("results").get("period");

			Period period = new Period();
			period.setGroup(group);
			period.setCounter(periodLong);
			period.setGame(game);
			period.setJsonFile(jsonFile);

			periodRepository.save(period);
		} catch (Exception ex) {
			System.out.println("Error while parsing period: " + ex.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "games/{game}/groups/{group}/periods/{period}")
	public String returnInputForPeriod(@PathVariable Long game, @PathVariable Long group, @PathVariable Long period) {
		List<Period> periods = periodRepository.findByGameAndGroupAndCounter(game, group, period);

		if (periods.size() > 1) {
			// TODO: Throw exception
		}

		return inputService.generateInputJson(periods.get(0)).toString();
	}
}
