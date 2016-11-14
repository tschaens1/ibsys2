package de.hska.waitingqueuemanagement.resource;

import de.hska.waitingqueuemanagement.business.IWaitingQueueRepository;
import de.hska.waitingqueuemanagement.domain.WaitingQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController(value = "Waiting Queue Resource")
@RequestMapping(value = "/api/rest/waitingqueues", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class WaitingQueueResource {

    @Autowired
    private IWaitingQueueRepository waitingQueueRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public WaitingQueue save(@RequestBody @Valid final WaitingQueue waitingQueue) {
        return waitingQueueRepository.save(waitingQueue);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public WaitingQueue getById(@PathVariable final Long id) {
        return waitingQueueRepository.findOne(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "")
    public List<WaitingQueue> getAll() {
        return waitingQueueRepository.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable final Long id) {
        waitingQueueRepository.delete(id);
    }
}
