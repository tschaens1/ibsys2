package de.hska;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/rest/file/xml")
public class XmlFileResource {

    @Autowired
    private IXmlFileRepository xmlFileRepository;

    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody XmlFile xmlFile){

        xmlFileRepository.save(xmlFile);
    }
}
