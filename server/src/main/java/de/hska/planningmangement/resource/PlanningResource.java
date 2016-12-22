package de.hska.planningmangement.resource;

import java.util.List;

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
import de.hska.planningmangement.domain.Planning;
import javassist.NotFoundException;

@RestController
@RequestMapping(value = "/api/rest", produces = MediaType.APPLICATION_JSON_VALUE)
public class PlanningResource {

	@Autowired
	private IPeriodRepository periodRepository;

	@ExceptionHandler({ org.springframework.http.converter.HttpMessageNotReadableException.class })
	@RequestMapping(method = RequestMethod.POST, value = "/periods/{counter}/planning")
	public String save(@PathVariable Long counter, @RequestBody String jsonObject) throws NotFoundException {

		if (jsonObject == null)
			return "error";

		JsonFile jsonFile = new JsonFile();
		jsonFile.setContent(jsonObject);

		Planning planning = new Planning();
		planning.setJsonFile(jsonFile);

		List<Period> period = periodRepository.findByCounter(counter);
		if (period.size() > 1)
			throw new NotFoundException("Couldn't specify period.");

		period.get(0).setPlanning(planning);

		return periodRepository.save(period).toString();
	}

}
