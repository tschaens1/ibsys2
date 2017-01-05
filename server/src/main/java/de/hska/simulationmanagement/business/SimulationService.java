package de.hska.simulationmanagement.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hska.dispositionmanagement.business.DispositionService;
import de.hska.partsmanagement.business.PartsNodeService;
import de.hska.partsmanagement.business.PartsService;
import de.hska.workplacemanagement.business.WorkplaceService;

@Service
public class SimulationService {

	@Autowired
	private PartsService partsService;

	@Autowired
	private WorkplaceService workplaceService;

	@Autowired
	private PartsNodeService partsNodeService;

	@Autowired
	private DispositionService dispositionService;

	public void initialize() {
		partsService.inizialize();
		workplaceService.initialize();
		partsNodeService.initialize();
	}

	public void initializeDisposition() {
		dispositionService.initialize();
	}
}
