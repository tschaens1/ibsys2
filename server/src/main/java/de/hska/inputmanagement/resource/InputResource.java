package de.hska.inputmanagement.resource;

import de.hska.inputmanagement.business.IInputRepository;
import de.hska.inputmanagement.domain.Input;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController(value = "Input Resource")
@RequestMapping(value = "/api/rest/inputs", consumes = {MediaType.TEXT_XML_VALUE})
public class InputResource {

    @Autowired
    private IInputRepository inputRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody @Valid final Input input) {
         inputRepository.save(input);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Input getById(@PathVariable final Long id) {
        return inputRepository.findOne(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "")
    public List<Input> getAll() {
        return inputRepository.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable final Long id) {
        inputRepository.delete(id);
    }
}
