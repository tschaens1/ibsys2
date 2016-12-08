package de.hska.inputmanagement.resource;

import de.hska.articlemanagement.domain.Article;
import de.hska.inputmanagement.business.IInputRepository;
import de.hska.inputmanagement.domain.Input;
import de.hska.itemmanagement.domain.Item;
import de.hska.orderlistmanagement.domain.OrderList;
import de.hska.ordermanagement.domain.Order;
import de.hska.productionlistmanagement.domain.ProductionList;
import de.hska.productionmanagement.domain.Production;
import de.hska.selldirectmanagement.domain.Selldirect;
import de.hska.sellwishmanagement.domain.Sellwish;
import de.hska.workingtimelistmanagement.domain.WorkingtimeList;
import de.hska.workingtimemanagement.domain.Workingtime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController(value = "Input Resource")
@RequestMapping(value = "/api/rest/inputs", consumes = {MediaType.APPLICATION_XML_VALUE})
public class InputResource {

    @Autowired
    private IInputRepository inputRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody @Valid final Input input) {
        final Sellwish sellwish = input.getSellwish();
        for (Item item : sellwish.getSellwishes()) {
            item.setSellwish(sellwish);
        }

        final Selldirect selldirect = input.getSelldirect();
        for (Item item : selldirect.getItems()) {
            item.setSelldirect(selldirect);
        }

        final OrderList orderlist = input.getOrderlist();
        for (Order order : orderlist.getOrders()) {
            order.setOrderlist(orderlist);
        }

        final ProductionList productionlist = input.getProductionlist();
        for (Production production : productionlist.getProductions()) {
            production.setProductionlist(productionlist);
        }

        final WorkingtimeList workingtimelist = input.getWorkingtimelist();
        for (Workingtime workingtime : workingtimelist.getWorkingtimes()) {
            workingtime.setWorkingtimelist(workingtimelist);
        }

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
