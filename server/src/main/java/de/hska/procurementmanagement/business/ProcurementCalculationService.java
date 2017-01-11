package de.hska.procurementmanagement.business;

import de.hska.dispositionmanagement.business.DispositionService;
import de.hska.partsmanagement.business.PartsNodeService;
import de.hska.partsmanagement.business.PartsService;
import de.hska.partsmanagement.domain.BuyPart;
import de.hska.partsmanagement.domain.PartNode;
import de.hska.planningmangement.business.PlanningService;
import de.hska.procurementmanagement.domain.BuyMode;
import de.hska.procurementmanagement.domain.BuyOrder;
import de.hska.warehousemanagement.business.WarehouseService;
import de.hska.warehousemanagement.domain.WarehouseArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class ProcurementCalculationService {

    @Autowired
    private ProcurementService procurementService;

    @Autowired
    private DispositionService dispositionService;

    @Autowired
    private PartsService partsService;

    @Autowired
    private PartsNodeService partsNodeService;

    @Autowired
    private PlanningService planningService;

    @Autowired
    private WarehouseService warehouseService;

    private Integer currentPeriod;

    public void initialize() {

        this.currentPeriod = planningService.getPeriod() + 1;
        this.procurementService.getNewBuyOrders().clear();

        generateBuyOrders();
    }

    private void generateBuyOrders() {
        for (BuyPart part : partsService.getBuyParts()) {
            Integer useAmount0 = dispositionService.getAmountOfBuyPartInCurrentProduction(part.getNumber());

            Integer useAmountFuture1 = 0;
            useAmountFuture1 += (
                    planningService.getForecastOne().get(0).getQuantity() * getAmountInSubTree(partsNodeService.getChildrenManufactoringNode(), part.getNumber())
                            + planningService.getForecastOne().get(1).getQuantity() * getAmountInSubTree(partsNodeService.getWomanManufactoringNode(), part.getNumber())
                            + planningService.getForecastOne().get(2).getQuantity() * getAmountInSubTree(partsNodeService.getManManufactoringNode(), part.getNumber())
            );

            Integer useAmountFuture2 = 0;
            useAmountFuture2 += (
                    planningService.getForecastTwo().get(0).getQuantity() * getAmountInSubTree(partsNodeService.getChildrenManufactoringNode(), part.getNumber())
                            + planningService.getForecastTwo().get(1).getQuantity() * getAmountInSubTree(partsNodeService.getWomanManufactoringNode(), part.getNumber())
                            + planningService.getForecastTwo().get(2).getQuantity() * getAmountInSubTree(partsNodeService.getManManufactoringNode(), part.getNumber())
            );

            Integer useAmountFuture3 = 0;
            useAmountFuture3 += (
                    planningService.getForecastThree().get(0).getQuantity() * getAmountInSubTree(partsNodeService.getChildrenManufactoringNode(), part.getNumber())
                            + planningService.getForecastThree().get(1).getQuantity() * getAmountInSubTree(partsNodeService.getWomanManufactoringNode(), part.getNumber())
                            + planningService.getForecastThree().get(2).getQuantity() * getAmountInSubTree(partsNodeService.getManManufactoringNode(), part.getNumber())
            );

            Integer[] upcomingAmount = new Integer[]{useAmount0, useAmountFuture1, useAmountFuture2, useAmountFuture3};
            BuyOrder order = checkForBuyInCurrentPeriod(part, upcomingAmount);

            if (order != null) {
                procurementService.generateNewBuyOrder(part, order.getBuyMode(), order.getAmount(), currentPeriod);
            }
        }
    }

    // Returns a new order if necessary and null otherwise
    private BuyOrder checkForBuyInCurrentPeriod(BuyPart buyPart, Integer[] futureAmounts) {
        ArrayList<BuyOrder> currentOrders = procurementService.getPendingBuyOrdersForPart(buyPart.getNumber());
        WarehouseArticle article = warehouseService.getWarehouseArticle(buyPart.getNumber());
        Integer currentAmount = article.getAmount();

        Double worstCaseTime = Math.ceil(buyPart.getTimeToRebuy() + buyPart.getRiskFactor() * buyPart.getRebuyDerivation());
        int worstCaseInt = worstCaseTime < 5 ? worstCaseTime.intValue() : 4;

        Integer neededAmount = 0;

        for (int i = 0; i < worstCaseInt; i++) {
            int calcPeriod = currentPeriod + i;

            if (i == 0) {
                currentAmount += procurementService.getIncomingAmountForPart(buyPart.getNumber());
            }

            // Check for existing order(s) in the future
            if (currentOrders.size() > 0) {
                for (BuyOrder order : currentOrders) {
                    if (procurementService.getWorstCaseArrivalPeriodByOrder(order) == calcPeriod) {
                        currentAmount += order.getAmount();
                    }
                }
            }

            currentAmount -= futureAmounts[i];

            if (currentAmount < 0) {
                neededAmount += currentAmount * (-1);
                if (calcPeriod == (currentPeriod + worstCaseInt - 1)) {
                    // Normal
                    if ((procurementService.getBuyCosts(buyPart, BuyMode.Normal, buyPart.getDiscountAmount()) < procurementService.getBuyCosts(buyPart, BuyMode.Normal, neededAmount))
                            && neededAmount < buyPart.getDiscountAmount()) {
                        neededAmount = buyPart.getDiscountAmount();
                    }
                    return new BuyOrder(buyPart.getNumber(), BuyMode.Normal, neededAmount, currentPeriod, 0.0);
                } else {
                    // Fast
                    if ((procurementService.getBuyCosts(buyPart, BuyMode.Fast, buyPart.getDiscountAmount()) < procurementService.getBuyCosts(buyPart, BuyMode.Fast, neededAmount))
                            && neededAmount < buyPart.getDiscountAmount()) {
                        neededAmount = buyPart.getDiscountAmount();
                    }
                    return new BuyOrder(buyPart.getNumber(), BuyMode.Fast, neededAmount, currentPeriod, 0.0);
                }
            }
        }

        // Else, do not buy stuff
        return null;
    }

    private Integer getAmountInSubTree(PartNode tree, Integer partNumber) {
        return this.partsNodeService.getAmountInTree(tree, partNumber);
    }
}
