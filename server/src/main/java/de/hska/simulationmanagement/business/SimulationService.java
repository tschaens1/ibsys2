package de.hska.simulationmanagement.business;

import de.hska.partsmanagement.business.PartsService;
import de.hska.workplacemanagement.business.WorkplaceService;
import de.hska.workplacemanagement.domain.Workplace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimulationService {

    @Autowired
    private PartsService partsService;

    @Autowired
    private WorkplaceService workplaceService;

    public void initialize() {
        partsService.inizialize();
        workplaceService.initialize();
    }
}
