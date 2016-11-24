package de.hska.resultsmanagement.resource;

import de.hska.resultsmanagement.business.IResultsRepository;
import de.hska.resultsmanagement.domain.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController(value = "Results Resource")
@RequestMapping(value = "/api/rest/results", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class ResultsResource {

    @Autowired
    private IResultsRepository resultsRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public Results save(@RequestBody @Valid final Results results) {
        return resultsRepository.save(results);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Results getById(@PathVariable final Long id) {
        return resultsRepository.findOne(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "")
    public List<Results> getAll() {
        return resultsRepository.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable final Long id) {
        resultsRepository.delete(id);
    }
}
