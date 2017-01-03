package de.hska.planningmangement.resource;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.hska.filemanagement.domain.JsonFile;
import de.hska.periodmanagement.business.IPeriodRepository;
import de.hska.periodmanagement.domain.Period;
import de.hska.planningmangement.business.PlanningService;
import de.hska.planningmangement.domain.Planning;
import javassist.NotFoundException;

@RestController
@RequestMapping(value = "/api/rest", produces = MediaType.APPLICATION_JSON_VALUE)
public class PlanningResource {

	@Autowired
	private IPeriodRepository periodRepository;

	@Autowired
	private PlanningService planningService;

	@ExceptionHandler({ org.springframework.http.converter.HttpMessageNotReadableException.class })
	@RequestMapping(method = RequestMethod.POST, value = "/periods/{counter}/planning")
	public String save(@PathVariable Long counter, @RequestBody String jsonObject)
			throws NotFoundException, ParseException {

		if (jsonObject == null)
			return "error";

		JsonFile jsonFile = new JsonFile();
		jsonFile.setContent(jsonObject);

		Planning planning = new Planning();
		planning.setJsonFile(jsonFile);

		Period period = periodRepository.findByCounter(counter);
		period.setPlanning(planning);

		planningService.initialize(jsonFile);

		return periodRepository.save(period).toString();
	}

}
