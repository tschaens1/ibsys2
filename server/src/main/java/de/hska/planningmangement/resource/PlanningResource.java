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
import de.hska.kpimanagement.business.KpiService;
import de.hska.periodmanagement.business.IPeriodRepository;
import de.hska.periodmanagement.domain.Period;
import de.hska.planningmangement.business.PlanningService;
import de.hska.planningmangement.domain.Planning;
import de.hska.procurementmanagement.business.ProcurementCalculationService;
import de.hska.procurementmanagement.business.ProcurementService;
import de.hska.productionmanagement.business.ProductionService;
import de.hska.simulationmanagement.business.SimulationService;
import de.hska.warehousemanagement.business.WarehouseService;
import javassist.NotFoundException;

@RestController
@RequestMapping(value = "/api/rest", produces = MediaType.APPLICATION_JSON_VALUE)
public class PlanningResource {

	@Autowired
	private IPeriodRepository periodRepository;

	@Autowired
	private PlanningService planningService;

	@Autowired
	private KpiService kpiService;

	@Autowired
	private WarehouseService warehouseService;

	@Autowired
	private ProductionService productionService;

	@Autowired
	private ProcurementService procurementService;

	@Autowired
	private ProcurementCalculationService procurementCalculationService;

	@Autowired
	private SimulationService simulationService;

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

		JsonFile periodJsonPeriod = period.getJsonFile();
		// initialize Services
		kpiService.initialize(periodJsonPeriod);
		simulationService.initialize();

		warehouseService.initialize(periodJsonPeriod);
		planningService.initialize(jsonFile);
		productionService.initialize(periodJsonPeriod);

		procurementService.initialize(periodJsonPeriod);
		procurementCalculationService.initialize(periodJsonPeriod);

		simulationService.initializeDisposition();
		return periodRepository.save(period).toString();
	}

}
