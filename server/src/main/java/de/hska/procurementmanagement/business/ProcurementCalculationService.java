package de.hska.procurementmanagement.business;

import de.hska.filemanagement.domain.JsonFile;
import de.hska.partsmanagement.business.PartsNodeService;
import de.hska.partsmanagement.business.PartsService;
import de.hska.partsmanagement.domain.BuyPart;
import de.hska.partsmanagement.domain.ManufacturingPart;
import de.hska.partsmanagement.domain.PartNode;
import de.hska.planningmangement.business.PlanningService;
import de.hska.productionmanagement.business.ProductionService;
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
    private PartsNodeService partsNodeService;

    @Autowired
    private PlanningService planningService;

    private JSONObject resultsJson;

    private Integer currentPeriod;

    public void initialize(JsonFile jsonFile) {
        JSONObject jsonObject = new JSONObject(jsonFile.getContent());
        this.resultsJson = jsonObject.getJSONObject("results");

        for (BuyPart part : partsService.getBuyParts()) {
            System.out.println("Part " + part.getNumber() + " in P1: " + getAmountInSubTree(this.partsNodeService.getChildrenManufactoringNode(), part.getNumber())
            + " in P2: " + getAmountInSubTree(this.partsNodeService.getWomanManufactoringNode(), part.getNumber())
                    + " in P3: " + getAmountInSubTree(this.partsNodeService.getManManufactoringNode(), part.getNumber()));
        }
    }

    public Integer getAmountInSubTree(PartNode tree, Integer partNumber) {
        return this.partsNodeService.getAmountInTree(tree, partNumber);
    }

    
}
