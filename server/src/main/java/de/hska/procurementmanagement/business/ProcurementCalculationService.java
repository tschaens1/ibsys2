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
import java.util.Objects;

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
            Integer useAmount = 0;
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

            Integer[] upcomingAmount = new Integer[]{useAmount, useAmountFuture1, useAmountFuture2, useAmountFuture3};

            // TODO: Use amount, amount1, amount2, amount3 to calculate order range
            // TODO: Generate orders if necessary
            BuyOrder order = null;

            System.out.println("Product: " + part.getNumber() + " Timeline: " + Arrays.toString(getFutureDailyOrderIncoming(part, upcomingAmount)));
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

    public BuyPart findBuyPartByNumber(Integer partNumber) {
        for (BuyPart part : this.partsService.getBuyParts()) {
            if (Objects.equals(part.getNumber(), partNumber)) {
                return part;
            }
        }

        return null;
    }

    public Integer[] getFutureDailyOrderIncoming(BuyPart part, Integer[] futureAmounts) {
        Integer[] upcomingAmountsDaily = new Integer[]{
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        };

        ArrayList<BuyOrder> pendingBuyOrders = getPendingBuyOrdersForPart(part.getNumber());
        ArrayList<BuyOrder> newBuyOrders = getNewBuyOrdersForPart(part.getNumber());
        ArrayList<IncomingBuyOrder> incomingBuyOrders = getIncomingBuyOrdersForPart(part.getNumber());

        incomingBuyOrders.stream().filter(incomingBuyOrder -> Objects.equals(incomingBuyOrder.getArrivePeriod(), this.currentPeriod)).forEach(incomingBuyOrder -> {
            upcomingAmountsDaily[0] += incomingBuyOrder.getAmount();
        });

        for (BuyOrder pendingBuyOrder : pendingBuyOrders) {
            double liefertermin;
            if (pendingBuyOrder.getBuyMode() == BuyMode.Fast) {
                liefertermin = Math.toIntExact(Math.round(part.getTimeToRebuy() / 2));
            } else {
                liefertermin = (part.getTimeToRebuy() + part.getRiskFactor() * part.getRebuyDerivation() - (currentPeriod - pendingBuyOrder.getOrderPeriod()));
            }
            if (liefertermin <= 4) {
                if (liefertermin * 5 < 0) {
                    upcomingAmountsDaily[0] += pendingBuyOrder.getAmount();
                } else {
                    upcomingAmountsDaily[Math.toIntExact(Math.round(liefertermin * 5))] += pendingBuyOrder.getAmount();
                }
            }
        }

        for (BuyOrder newBuyOrder : newBuyOrders) {
            double liefertermin;
            if (newBuyOrder.getBuyMode() == BuyMode.Fast) {
                liefertermin = Math.toIntExact(Math.round(part.getTimeToRebuy() / 2));
            } else {
                liefertermin = (part.getTimeToRebuy() + part.getRiskFactor() * part.getRebuyDerivation() - (currentPeriod - newBuyOrder.getOrderPeriod()));
            }
            if (liefertermin <= 4) {
                if (liefertermin * 5 < 0) {
                    upcomingAmountsDaily[0] += newBuyOrder.getAmount();
                } else {
                    upcomingAmountsDaily[Math.toIntExact(Math.round(liefertermin * 5))] += newBuyOrder.getAmount();
                }
            }
        }

        WarehouseArticle article = warehouseService.getWarehouseArticle(part.getNumber());
        Integer currentAmount = article.getAmount();
        Integer week = 1;

        for (int i = 0; i < 20; i++) {
            currentAmount -= Math.round(futureAmounts[week-1]/5);
            upcomingAmountsDaily[i] += currentAmount;
            currentAmount = upcomingAmountsDaily[i];
            if (i > 0 && i % 5 == 4) {
                week++;
            }
        }

        return upcomingAmountsDaily;
    }

    public Double getOrderRange(BuyPart buyPart, Integer[] futureAmounts) {
        WarehouseArticle article = warehouseService.getWarehouseArticle(buyPart.getNumber());
        Integer currentAmount = article.getAmount();

        return 0.0;
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
