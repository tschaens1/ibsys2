package de.hska.planningmangement.resource;

import de.hska.capacitymanagement.business.CapacityService;
import de.hska.dispositionmanagement.business.DispositionService;
import de.hska.filemanagement.domain.JsonFile;
import de.hska.inputmanagement.business.InputService;
import de.hska.periodmanagement.business.IPeriodRepository;
import de.hska.periodmanagement.domain.Period;
import de.hska.planningmangement.business.PlanningService;
import de.hska.planningmangement.domain.Planning;
import de.hska.procurementmanagement.business.ProcurementCalculationService;
import de.hska.productionmanagement.business.ProductionService;
import javassist.NotFoundException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping(value = "/api/rest", produces = MediaType.APPLICATION_JSON_VALUE)
public class PlanningResource {

    @Autowired
    private IPeriodRepository periodRepository;

    @Autowired
    private DispositionService dispositionService;

    @Autowired
    private PlanningService planningService;

    @Autowired
    private ProductionService productionService;

    @Autowired
    private ProcurementCalculationService procurementCalculationService;

    @Autowired
    private CapacityService capacityService;

    @Autowired
    private InputService inputService;

    @RequestMapping(method = RequestMethod.POST, value = "/periods/{counter}/planning")
    public ResponseEntity<String> save(@PathVariable Long counter, @RequestBody String jsonObject)
            throws NotFoundException, ParseException, JSONException {

        if (jsonObject == null)
            return ResponseEntity.badRequest().body("No planning found. You must upload a planning file!");

        JsonFile jsonFile = new JsonFile();
        jsonFile.setContent(jsonObject);

        Planning planning = new Planning();
        planning.setJsonFile(jsonFile);

        Period period = periodRepository.findByCounter(counter - 1);

        if (period == null) {
            return ResponseEntity.badRequest().body("Period not found in database! Have you uploaded the correct result XML?");
        }

        period.setPlanning(planning);

        planningService.initialize(jsonFile);
        productionService.initialize(period.getResult());

        dispositionService.initialize();
        capacityService.initialize();
        procurementCalculationService.initialize();

        period.setInput(inputService.generateInputJson());
        periodRepository.save(period);

        return ResponseEntity.ok(periodRepository.findByCounter(counter - 1).getInput().getContent());
    }

    @ExceptionHandler(JSONException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ResponseEntity<String> handleJsonException(JSONException ex) {
        System.out.println(ex.getMessage());
        return ResponseEntity.badRequest().body("Could not parse JSON! Have you uploaded the correct file?");
    }
}
