package de.hska.procurementmanagement.business;

import de.hska.partsmanagement.business.PartsNodeService;
import de.hska.partsmanagement.business.PartsService;
import de.hska.partsmanagement.domain.BuyPart;
import de.hska.partsmanagement.domain.PartNode;
import de.hska.planningmangement.business.PlanningService;
import de.hska.procurementmanagement.domain.BuyMode;
import de.hska.procurementmanagement.domain.BuyOrder;
import de.hska.procurementmanagement.domain.IncomingBuyOrder;
import de.hska.productionmanagement.business.ProductionService;
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
    private ProductionService productionService;

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

        // We do plan a new period, so we add 1
        this.currentPeriod = planningService.getPeriod() + 1;

        generateBuyOrders();
    }

    public Integer getAmountInSubTree(PartNode tree, Integer partNumber) {
        return this.partsNodeService.getAmountInTree(tree, partNumber);
    }

    public void generateBuyOrders() {
        for (BuyPart part : partsService.getBuyParts()) {
            Integer useAmount0 = 0;
            // TODO: Initialize useAmount with Andi's method from productionService

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

            // TODO: Generate orders if necessary

            System.out.println(
                    "Product: " + part.getNumber()
                    + " Usage: " + Arrays.toString(upcomingAmount)
                    + " Average: " + getAverageAmountInFuture(upcomingAmount)
                    + " Calc amount: " + getCalculationAmount(part)
                    + " Lager: " + warehouseService.getWarehouseArticle(part.getNumber()).getAmount()
                    + " Order Range: " + getOrderRange(part, upcomingAmount));
        }
    }

    public ArrayList<BuyOrder> getPendingBuyOrdersForPart(Integer partNumber) {
        return procurementService.getPendingBuyOrdersForPart(partNumber);
    }

    public ArrayList<BuyOrder> getNewBuyOrdersForPart(Integer partNumber) {
        return procurementService.getNewBuyOrdersForPart(partNumber);
    }

    public ArrayList<IncomingBuyOrder> getIncomingBuyOrdersForPart(Integer partNumber) {
        return procurementService.getIncomingBuyOrdersForPart(partNumber);
    }

    public Double getOrderRange(BuyPart buyPart, Integer[] futureAmounts) {
        Integer amountToCalculate = getCalculationAmount(buyPart);
        Integer averageAmountInFuture = getAverageAmountInFuture(futureAmounts);

        return amountToCalculate / averageAmountInFuture - (buyPart.getTimeToRebuy() + buyPart.getRiskFactor() * buyPart.getRebuyDerivation());
    }

    private Integer getCalculationAmount(BuyPart buyPart) {
        WarehouseArticle article = warehouseService.getWarehouseArticle(buyPart.getNumber());
        Integer currentAmount = article.getAmount();
        Integer incomingInFuture = (procurementService.getFutureAmountForPart(buyPart.getNumber()) / 2)
                + (procurementService.getIncomingAmountForPart(buyPart.getNumber()));

        return currentAmount + incomingInFuture;
    }

    private Integer getAverageAmountInFuture(Integer[] futureAmounts) {
        if (futureAmounts.length == 0) return 0;

        int sum = 0;
        for (Integer i : futureAmounts) {
            sum += i;
        }

        return sum / futureAmounts.length;
    }

    // Returns null if there is no need for an order
    public BuyOrder checkForBuyInCurrentPeriod(BuyPart buyPart, Integer[] futureAmounts) {
        // TODO: Calculate Range and decide about Fast and Normal
        Integer amount = 1000;

        if (amount > 0) {
            // Fast
            if ((procurementService.getBuyCosts(buyPart, BuyMode.Fast, buyPart.getDiscountAmount()) < procurementService.getBuyCosts(buyPart, BuyMode.Fast, amount))
                    && amount < buyPart.getDiscountAmount()) {
                amount = buyPart.getDiscountAmount();
            }

            return new BuyOrder(buyPart.getNumber(), BuyMode.Fast, amount, currentPeriod, 0.0);
        }

        // Normal
        if (amount < 0) {
            if ((procurementService.getBuyCosts(buyPart, BuyMode.Normal, buyPart.getDiscountAmount()) < procurementService.getBuyCosts(buyPart, BuyMode.Normal, amount))
                    && amount < buyPart.getDiscountAmount()) {
                amount = buyPart.getDiscountAmount();
            }

            return new BuyOrder(buyPart.getNumber(), BuyMode.Normal, amount, currentPeriod, 0.0);
        }

        // Else, do not buy stuff
        return null;
    }
}
