package de.hska.filemanagement.resource;

import java.text.ParseException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.hska.filemanagement.domain.JsonFile;
import de.hska.filemanagement.domain.XmlFile;
import de.hska.inputmanagement.business.InputService;
import de.hska.kpimanagement.business.KpiService;
import de.hska.periodmanagement.business.IPeriodRepository;
import de.hska.periodmanagement.domain.Period;
import de.hska.simulationmanagement.business.SimulationService;
import de.hska.util.FileConverterService;
import de.hska.warehousemanagement.business.WarehouseService;

@RestController
@RequestMapping(value = "/api/rest/files", produces = MediaType.APPLICATION_JSON_VALUE)
public class FileResource {

	@Autowired
	private IPeriodRepository periodRepository;

	@Autowired
	private FileConverterService fileConverterService;

	@Autowired
	private KpiService kpiService;

	@Autowired
	private InputService inputService;

	@Autowired
	private WarehouseService warehouseService;

	@Autowired
	private SimulationService simulationService;

	@RequestMapping(method = RequestMethod.POST, value = "result")
	public ResponseEntity<String> save(@RequestBody XmlFile xmlFile) throws ParseException {
		String jsonContent = xmlFile.getContent();
		JSONObject jsonObject = fileConverterService.convertXmlToJson(jsonContent);
		JsonFile jsonFile = new JsonFile();
		jsonFile.setContent(jsonObject.toString());
		Long periodLong = (Long) jsonObject.getJSONObject("results").get("period");
		Period period = periodRepository.findByCounter(periodLong);

		if (period != null) {
			period.setCounter(periodLong);
			period.setJsonFile(jsonFile);
			periodRepository.save(period);
		} else {
			Period newPeriod = new Period();
			newPeriod.setCounter(periodLong);
			newPeriod.setJsonFile(jsonFile);
			periodRepository.save(newPeriod);
		}

		kpiService.initialize(jsonFile);
		warehouseService.initialize(jsonFile);
		// inputService.initialize(jsonFile);
		simulationService.initialize();

		return new ResponseEntity<String>(HttpStatus.CREATED);

	}

	@RequestMapping(method = RequestMethod.GET, value = "periods/{periodCounter}/result")
	public ResponseEntity<String> ReturnResultsForPeriod(@PathVariable Long periodCounter) {
		Period period = periodRepository.findByCounter(periodCounter);
		return ResponseEntity.ok(period.getJsonFile().getContent());
	}

	@RequestMapping(method = RequestMethod.GET, value = "periods/{periodCounter}/input")
	public ResponseEntity<String> returnInputForPeriod(@PathVariable Long periodCounter) {
		Period period = periodRepository.findByCounter(periodCounter);
		return ResponseEntity.ok(inputService.generateInputJson(period).toString());
	}
}