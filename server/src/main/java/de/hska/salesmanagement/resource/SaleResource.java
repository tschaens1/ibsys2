package de.hska.salesmanagement.resource;

import de.hska.salesmanagement.business.ISaleRepository;
import de.hska.salesmanagement.domain.DirectSale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController(value = "Direct Sale Resource")
@RequestMapping(value = "/api/rest/sales", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class SaleResource {

    @Autowired
    private ISaleRepository saleRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public DirectSale save(@RequestBody @Valid final DirectSale sale) {
        return saleRepository.save(sale);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public DirectSale getById(@PathVariable final Long id) {
        return saleRepository.findOne(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "")
    public List<DirectSale> getAll() {
        return saleRepository.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable final Long id) {
        saleRepository.delete(id);
    }
}
