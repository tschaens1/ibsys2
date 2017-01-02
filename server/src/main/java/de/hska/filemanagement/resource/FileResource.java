package de.hska.filemanagement.resource;

import de.hska.filemanagement.domain.JsonFile;
import de.hska.filemanagement.domain.XmlFile;
import de.hska.inputmanagement.business.InputService;
import de.hska.kpimanagement.business.KpiService;
import de.hska.kpimanagement.domain.KpiContainer;
import de.hska.kpimanagement.domain.KpiPercentContainer;
import de.hska.periodmanagement.business.IPeriodRepository;
import de.hska.periodmanagement.domain.Period;
import de.hska.util.FileConverterService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(method = RequestMethod.POST, value = "result")
    public ResponseEntity save(@RequestBody XmlFile xmlFile) {
        String jsonContent = xmlFile.getContent();
        JSONObject jsonObject = fileConverterService.convertXmlToJson(jsonContent);
        JsonFile jsonFile = new JsonFile();
        jsonFile.setContent(jsonObject.toString());
        try {
            Long periodLong = (Long) jsonObject.getJSONObject("results").get("period");

            Period period = new Period();
            period.setCounter(periodLong);
            period.setJsonFile(jsonFile);

            periodRepository.save(period);

            kpiService.initialize(jsonFile);
            // inputService.initialize(jsonFile);

            KpiContainer container = kpiService.getCapacity();
            double abc = container.getAverage();
            double afijsf = container.getSum();

            KpiPercentContainer container1 = kpiService.getDeliveryReliability();
            double ofdsiasdf = container1.getAverage();
            double sfosaf = container1.getCurrent();

            System.out.println("Summary: " + kpiService.getSummary().getAverage() + " delivavg: " + ofdsiasdf + " kapaall: " + afijsf + " kapaavg: " + abc);

            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            // return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            return null;
        }


    }

    @RequestMapping(method = RequestMethod.GET, value = "periods/{period}/result")
    public ResponseEntity ReturnResultsForPeriod(@PathVariable Long period) {
        List<Period> periods = periodRepository.findByCounter(period);

        if (periods.size() > 1) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(periods.get(0).getJsonFile().getContent());
    }

    @RequestMapping(method = RequestMethod.GET, value = "periods/{period}/input")
    public ResponseEntity returnInputForPeriod(@PathVariable Long period) {
        List<Period> periods = periodRepository.findByCounter(period);

        if (periods.size() > 1) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(inputService.generateInputJson(periods.get(0)).toString());
    }
}