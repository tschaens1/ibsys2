package de.hska.filemanagement.resource;

import de.hska.filemanagement.domain.JsonFile;
import de.hska.filemanagement.domain.XmlFile;
import de.hska.inputmanagement.business.InputService;
import de.hska.kpimanagement.business.KpiService;
import de.hska.periodmanagement.business.IPeriodRepository;
import de.hska.periodmanagement.domain.Period;
import de.hska.util.FileConverterService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    public void save(@RequestBody XmlFile xmlFile) {
        String jsonContent = xmlFile.getContent();
        JSONObject jsonObject = fileConverterService.convertXmlToJson(jsonContent);
        JsonFile jsonFile = new JsonFile();
        jsonFile.setContent(jsonObject.toString());
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

            kpiService.initialize(jsonFile);
            inputService.initialize(jsonFile);

        } catch (Exception ex) {
            System.out.println("Error while parsing period: " + ex.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "periods/{period}/result")
    public String ReturnResultsForPeriod(@PathVariable Long period) {
        List<Period> periods = periodRepository.findByCounter(period);

        if (periods.size() > 1) {
            // TODO: Throw exception
        }

        return periods.get(0).getJsonFile().getContent();
    }

    @RequestMapping(method = RequestMethod.GET, value = "periods/{period}/input")
    public String returnInputForPeriod(@PathVariable Long period) {
        List<Period> periods = periodRepository.findByCounter(period);

        if (periods.size() > 1) {
            // TODO: Throw exception
        }

        return inputService.generateInputJson(periods.get(0)).toString();
    }
}