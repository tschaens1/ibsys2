package de.hska.simulationmanagement.business;

import de.hska.partsmanagement.business.PartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimulationService {

    @Autowired
    private PartsService partsService;

    public void initialize() {
        partsService.inizialize();
    }
}
