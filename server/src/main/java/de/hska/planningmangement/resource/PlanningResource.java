package de.hska.planningmangement.resource;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.hska.periodmanagement.business.IPeriodRepository;
import de.hska.periodmanagement.domain.Period;
import de.hska.planningmangement.domain.Planning;
import de.hska.xmlfilemanagement.domain.JsonFile;
import javassist.NotFoundException;

@RestController
@RequestMapping(value = "/api/rest", produces = MediaType.APPLICATION_JSON_VALUE)
public class PlanningResource {

	@Autowired
	private IPeriodRepository periodRepository;

	@ExceptionHandler({ org.springframework.http.converter.HttpMessageNotReadableException.class })
	@RequestMapping(method = RequestMethod.POST, value = "/games/{game}/groups/{group}/periods/{counter}/plannings")
	public String save(@PathVariable Long game, @PathVariable Long group, @PathVariable Long counter,
			@RequestBody JSONObject jsonObject) throws NotFoundException {

		if (jsonObject == null)
			return "error";

		JsonFile jsonFile = new JsonFile();
		jsonFile.setContent(jsonObject.toString());

		Planning planning = new Planning();
		planning.setJsonFile(jsonFile);

		List<Period> period = periodRepository.findByGameAndGroupAndCounter(game, group, counter);
		if (period.size() > 1)
			throw new NotFoundException("Couldn't specify period.");

		period.get(0).setPlanning(planning);

		return periodRepository.save(period).toString();
	}

}