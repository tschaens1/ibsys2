package de.hska.procurementmanagement.business;

import de.hska.filemanagement.domain.JsonFile;
import de.hska.partsmanagement.business.PartsService;
import de.hska.planningmangement.business.PlanningService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcurementCalculationService {

    @Autowired
    private ProcurementService procurementService;

    @Autowired
    private ProductionService productionService;

    @Autowired
    private PartsService partsService;

    @Autowired
    private PlanningService planningService;



    private JSONObject resultsJson;

    public void initialize(JsonFile jsonFile) {
        JSONObject jsonObject = new JSONObject(jsonFile.getContent());
        this.resultsJson = jsonObject.getJSONObject("results");
    }
}
