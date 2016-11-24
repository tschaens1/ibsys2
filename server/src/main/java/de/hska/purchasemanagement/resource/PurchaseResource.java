package de.hska.purchasemanagement.resource;

import de.hska.purchasemanagement.business.IPurchaseRepository;
import de.hska.purchasemanagement.domain.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController(value = "Purchase Resource")
@RequestMapping(value = "/api/rest/purchases", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class PurchaseResource {

    @Autowired
    private IPurchaseRepository purchaseRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Purchase save(@RequestBody @Valid final Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Purchase getById(@PathVariable final Long id) {
        return purchaseRepository.findOne(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "")
    public List<Purchase> getAll() {
        return purchaseRepository.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable final Long id) {
        purchaseRepository.delete(id);
    }
}
