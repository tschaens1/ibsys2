package de.hska.xmlfilemanagement.resource;

import de.hska.periodmanagement.business.IPeriodRepository;
import de.hska.periodmanagement.domain.Period;
import de.hska.util.FileConverterService;
import de.hska.xmlfilemanagement.domain.JsonFile;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/rest/file/xml")
public class JsonFileResource {

    @Autowired
    private IPeriodRepository periodRepository;

    @Autowired
    private FileConverterService fileConverterService;

    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody JsonFile jsonFile) {
        String jsonContent = jsonFile.getContent();
        JSONObject jsonObject = fileConverterService.convertXmlToJson(jsonContent);

        try {
            Long game = (Long) jsonObject.get("game");
            Long group = (Long) jsonObject.get("group");
            Long periodLong = (Long) jsonObject.get("period");

            Period period = new Period();
            period.setGroup(group);
            period.setCounter(periodLong);
            period.setGame(game);
            period.setJsonFile(jsonFile);
        } catch (Exception ex) {
            System.out.println("Error while parsing period: " + ex.getStackTrace().toString());
        }
    }
}
