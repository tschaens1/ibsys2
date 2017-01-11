package de.hska.filemanagement.resource;

import de.hska.filemanagement.domain.JsonFile;
import de.hska.filemanagement.domain.XmlFile;
import de.hska.partsmanagement.business.PartsNodeService;
import de.hska.partsmanagement.business.PartsService;
import de.hska.periodmanagement.business.IPeriodRepository;
import de.hska.periodmanagement.domain.Period;
import de.hska.procurementmanagement.business.ProcurementService;
import de.hska.util.FileConverterService;
import de.hska.warehousemanagement.business.WarehouseService;
import de.hska.workplacemanagement.business.WorkplaceService;
import org.json.JSONException;
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
    private PartsService partsService;

    @Autowired
    private WorkplaceService workplaceService;

    @Autowired
    private PartsNodeService partsNodeService;

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private ProcurementService procurementService;

    @Autowired
    private FileConverterService fileConverterService;

    @RequestMapping(method = RequestMethod.POST, value = "result")
    public ResponseEntity<String> save(@RequestBody XmlFile xmlFile) throws ParseException, JSONException {
        String jsonContent = xmlFile.getContent();
        JSONObject jsonObject = fileConverterService.convertXmlToJson(jsonContent);
        JsonFile jsonFile = new JsonFile();
        jsonFile.setContent(jsonObject.toString());
        Long periodLong = (Long) jsonObject.getJSONObject("results").get("period");
        Period period = periodRepository.findByCounter(periodLong);

        if (period != null) {
            period.setCounter(periodLong);
            period.setResult(jsonFile);
            periodRepository.save(period);
        } else {
            Period newPeriod = new Period();
            newPeriod.setCounter(periodLong);
            newPeriod.setResult(jsonFile);
            periodRepository.save(newPeriod);
        }

        partsService.inizialize();
        workplaceService.initialize();
        partsNodeService.initialize();

        warehouseService.initialize(jsonFile);
        procurementService.initialize(jsonFile);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "periods/{periodCounter}/result")
    public ResponseEntity<String> getResultsForPeriod(@PathVariable Long periodCounter) {
        Period period = periodRepository.findByCounter(periodCounter);

        if (period == null) {
            return ResponseEntity.badRequest().body("Period not found in database! Have you uploaded the correct result XML?");
        }

        JSONObject json = new JSONObject(period.getResult().getContent());
        return ResponseEntity.ok(json.getJSONObject("results").getJSONObject("result").toString());
    }

    @RequestMapping(method = RequestMethod.GET, value = "periods/{periodCounter}/input")
    public ResponseEntity<String> getInputForPeriod(@PathVariable Long periodCounter) {
        Period period = periodRepository.findByCounter(periodCounter - 1);

        if (period == null) {
            return ResponseEntity.badRequest().body("Period not found in database! Have you uploaded the correct result XML?");
        }

        return ResponseEntity.ok(period.getInput().getContent());
    }

    @RequestMapping(method = RequestMethod.GET, value = "periods/{periodCounter}/stock")
    public ResponseEntity<String> getStockForPeriod(@PathVariable Long periodCounter) {
        Period period = periodRepository.findByCounter(periodCounter);

        if (period == null) {
            return ResponseEntity.badRequest().body("Period not found in database! Have you uploaded the correct result XML?");
        }

        JSONObject json = new JSONObject(period.getResult().getContent());
        return ResponseEntity.ok(json.getJSONObject("results").getJSONObject("warehousestock").toString());
    }

    @ExceptionHandler(JSONException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ResponseEntity<String> handleJsonException() {
        return ResponseEntity.badRequest().body("Could not parse JSON! Have you done a correct planning?");
    }
}