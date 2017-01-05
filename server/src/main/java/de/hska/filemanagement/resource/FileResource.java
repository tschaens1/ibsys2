package de.hska.filemanagement.resource;

import de.hska.dispositionmanagement.business.DispositionService;
import de.hska.filemanagement.domain.JsonFile;
import de.hska.filemanagement.domain.XmlFile;
import de.hska.inputmanagement.business.InputService;
import de.hska.kpimanagement.business.KpiService;
import de.hska.periodmanagement.business.IPeriodRepository;
import de.hska.periodmanagement.domain.Period;
import de.hska.planningmangement.business.PlanningService;
import de.hska.procurementmanagement.business.ProcurementService;
import de.hska.productionmanagement.business.ProductionService;
import de.hska.simulationmanagement.business.SimulationService;
import de.hska.util.FileConverterService;
import de.hska.warehousemanagement.business.WarehouseService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping(value = "/api/rest/files", produces = MediaType.APPLICATION_JSON_VALUE)
public class FileResource {
    @Autowired
    private IPeriodRepository periodRepository;

    @Autowired
    private InputService inputService;

    @Autowired
    private FileConverterService fileConverterService;

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private KpiService kpiService;

    @Autowired
    private ProductionService productionService;

    @Autowired
    private ProcurementService procurementService;

    @Autowired
    private SimulationService simulationService;

    @Autowired
    private PlanningService planningService;

    @Autowired
    private DispositionService dispositionService;

    @RequestMapping(method = RequestMethod.POST, value = "result")
    public ResponseEntity<String> save(@RequestBody XmlFile xmlFile)
            throws ParseException {
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
        productionService.initialize(jsonFile);
        simulationService.initialize();
        dispositionService.initialize();
        procurementService.initialize(jsonFile);

        return new ResponseEntity<>(HttpStatus.CREATED);
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

    @RequestMapping(method = RequestMethod.GET, value = "periods/{periodCounter}/result")
    public ResponseEntity<String> getResultsForPeriod(@PathVariable Long periodCounter) {
        Period period = periodRepository.findByCounter(periodCounter);

        if (period == null) {
            return ResponseEntity.badRequest().body("Period not found in database! Have you uploaded the result XML?");
        }

        JSONObject json = new JSONObject(period.getJsonFile().getContent());
        return ResponseEntity.ok(json.getJSONObject("results").getJSONObject("result").toString());
    }

    @RequestMapping(method = RequestMethod.GET, value = "periods/{periodCounter}/input")
    public ResponseEntity<String> getInputForPeriod(@PathVariable Long periodCounter) {
        Period period = periodRepository.findByCounter(periodCounter);

        if (period == null) {
            return ResponseEntity.badRequest().body("Period not found in database! Have you uploaded the result XML?");
        }

        return ResponseEntity.ok(inputService.generateInputJson(period).toString());
    }
}