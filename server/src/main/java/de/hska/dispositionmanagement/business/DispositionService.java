package de.hska.dispositionmanagement.business;

import de.hska.dispositionmanagement.domain.Disposition;
import de.hska.partsmanagement.business.PartsNodeService;
import de.hska.partsmanagement.business.PartsService;
import de.hska.partsmanagement.domain.PartNode;
import de.hska.planningmangement.business.PlanningService;
import de.hska.productionmanagement.business.ProductionService;
import de.hska.warehousemanagement.business.WarehouseService;
import de.hska.workplacemanagement.business.WorkplaceService;
import de.hska.workplacemanagement.domain.ProductionOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;

@Service
public class DispositionService {

    @Autowired
    private PlanningService planningService;

    @Autowired
    private PartsNodeService partsNodeService;

    @Autowired
    private PartsService partsService;

    @Autowired
    private ProductionService productionService;

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private WorkplaceService workplaceService;

    private ArrayList<Disposition> dispositionP1;
    private ArrayList<Disposition> dispositionP2;
    private ArrayList<Disposition> dispositionP3;
    private ArrayList<Disposition> dispositions;

    public void initialize() {
        ConstructContainers();

        this.dispoRecursively(this.partsNodeService.getChildrenManufactoringNode(), new Disposition());
        this.dispoRecursively(this.partsNodeService.getWomanManufactoringNode(), new Disposition());
        this.dispoRecursively(this.partsNodeService.getManManufactoringNode(), new Disposition());
        this.mergeDispositions();
        this.productionService.deployRemainingProductionOrders(dispositions);

        for (Disposition dispo : this.dispositions) {
            int waiting = 0;
            for (ProductionOrder order : dispo.getProductionOrderInWaitingQueue()) {
                waiting += order.getAmount();
            }

            int work = 0;
            for (ProductionOrder order : dispo.getProductionOrdersInWork()) {
                work += order.getAmount();
            }

            System.out.println("Artikel: " + dispo.getPartNumber() + " Sellwish/direct: " + dispo.getSellwish().getAmount()
                    + " + Safetystock: " + dispo.getSafetyStockvalue() + " - Stock: " + dispo.getWarehouseStock()
                    + " - WaitingQueue: " + waiting + " - InWork: " + work + " = " + dispo.getFinalNewProduction().getAmount());
        }


    }

    private void ConstructContainers() {
        this.dispositionP1 = new ArrayList<>();
        this.dispositionP2 = new ArrayList<>();
        this.dispositionP3 = new ArrayList<>();
        this.dispositions = new ArrayList<>();
    }

    private void dispoRecursively(PartNode node, Disposition parent) {

        Disposition disposition = new Disposition();
        disposition.setPartNumber(node.getPartNumber());

        if (node.getPartNumber() != 1 || node.getPartNumber() != 2 || node.getPartNumber() != 3) {
            disposition.setParent(parent);
            disposition.setAmount(parent.getAmount());
        }

        disposition.setAmount(calculateDispositionAmount(node.getPartNumber(), disposition));

        if (node.getPartNumber() == 1)
            this.dispositionP1.add(disposition);

        if (node.getPartNumber() == 2)
            this.dispositionP2.add(disposition);

        if (node.getPartNumber() == 3)
            this.dispositionP3.add(disposition);

        this.dispositions.add(disposition);

        node.getParts()
                .stream()
                .filter(partNode -> partNode.getParts() != null)
                .forEach(partNode -> this.dispoRecursively(partNode, disposition));
    }

    private void mergeDispositions() {
        ArrayList<Disposition> mergedDispositions = new ArrayList<>();
        final Iterator<Disposition> iterator = dispositions.iterator();
        while (iterator.hasNext()) {
            Disposition disposition = iterator.next();
            boolean contains = false;
            for (Disposition mergedDisposition : mergedDispositions) {
                if (disposition.getPartNumber() == mergedDisposition.getPartNumber()) {
                    contains = true;
                    mergedDisposition.getSellwish().setAmount(mergedDisposition.getSellwish().getAmount() + disposition.getSellwish().getAmount());
                    mergedDisposition.setSafetyStockvalue(mergedDisposition.getSafetyStockvalue() + disposition.getSafetyStockvalue());
                    mergedDisposition.setWarehouseStock(mergedDisposition.getWarehouseStock() + disposition.getWarehouseStock());
                    mergedDisposition.getFinalNewProduction().setAmount(mergedDisposition.getFinalNewProduction().getAmount() + disposition.getFinalNewProduction().getAmount());
                    mergedDisposition.setProductionOrdersInWork(disposition.getProductionOrdersInWork());
                    mergedDisposition.setProductionOrdersInWaitingQueue(disposition.getProductionOrderInWaitingQueue());
                    iterator.remove();
                }
            }
            if (!contains) {
                mergedDispositions.add(disposition);
                iterator.remove();
            }
        }

        this.dispositions = mergedDispositions;
    }

    private int calculateDispositionAmount(int partNumber, Disposition disposition) {
        int amountSellwish = 0;
        int amountSafetyStockvalue = 0;
        int amountWarehouse = 0;
        int amountWaitinglist = 0;
        int amountOrdersInWork = 0;

        ProductionOrder sellWishAndDirect = new ProductionOrder();

        if (this.planningService.getProductionItemForProduct(partNumber) != null) {
            sellWishAndDirect.setProductNumber(partNumber);
            sellWishAndDirect.setAmount(this.planningService.getProductionItemForProduct(partNumber).getQuantity());
            sellWishAndDirect.setPeriod(this.planningService.getPeriod());
            sellWishAndDirect.setInWork(false);

            disposition.setSellwish(sellWishAndDirect);
            amountSellwish = disposition.getSellwish().getAmount();
        } else if (disposition.getParent() != null) {
            sellWishAndDirect.setProductNumber(partNumber);
            sellWishAndDirect.setAmount(disposition.getParent().getFinalNewProduction().getAmount()
                    + getWaitingQueueAmount(disposition.getParent()));
            sellWishAndDirect.setPeriod(this.planningService.getPeriod());
            sellWishAndDirect.setInWork(false);

            disposition.setSellwish(sellWishAndDirect);
            amountSellwish = disposition.getSellwish().getAmount();
        }

        if (this.planningService.getSafetystockItemForProduct(partNumber) != null) {
            int safetyStock = this.planningService.getSafetystockItemForProduct(partNumber).getQuantity();
            if (this.partsService.getManufacturingPartById(partNumber).getUsedInAllProducts()) {
                safetyStock = safetyStock / 3;
            }
            disposition.setSafetyStockvalue(safetyStock);
            amountSafetyStockvalue = disposition.getSafetyStockvalue();
        }

        if (this.warehouseService.getWarehouseArticle(partNumber) != null) {
            int warehouseStock = this.warehouseService.getWarehouseArticle(partNumber).getAmount();
            if (this.partsService.getManufacturingPartById(partNumber).getUsedInAllProducts()) {
                warehouseStock = warehouseStock / 3;
            }
            disposition.setWarehouseStock(warehouseStock);
            amountWarehouse = disposition.getWarehouseStock();
        }

        if (this.productionService.getOrdersWaitinglistForProduct(partNumber) != null) {
            disposition.setProductionOrdersInWaitingQueue(
                    this.productionService.getOrdersWaitinglistForProduct(partNumber));
            for (ProductionOrder order : disposition.getProductionOrderInWaitingQueue()) {
                if (order.getProductNumber() == partNumber)
                    amountWaitinglist += order.getAmount();
            }

            if (this.partsService.getManufacturingPartById(partNumber).getUsedInAllProducts()) {
                amountWaitinglist = amountWaitinglist / 3;
            }
        }

        if (this.productionService.getOrdersInWorkForProduct(partNumber) != null) {
            disposition.setProductionOrdersInWork(this.productionService.getOrdersInWorkForProduct(partNumber));
            for (ProductionOrder order : disposition.getProductionOrdersInWork()) {
                if (order.getProductNumber() == partNumber)
                    amountOrdersInWork += order.getAmount();
            }

            if (this.partsService.getManufacturingPartById(partNumber).getUsedInAllProducts()) {
                amountOrdersInWork = amountOrdersInWork / 3;
            }
        }

        int amount = amountSellwish + amountSafetyStockvalue - amountWarehouse - amountOrdersInWork - amountWaitinglist;

        if (amount < 0)
            amount = 0;

        ProductionOrder production = new ProductionOrder();
        production.setProductNumber(partNumber);
        production.setAmount(amount);
        production.setPeriod(this.planningService.getPeriod());
        production.setInWork(false);
        production.setWorkplaceId(this.workplaceService.getArbeitsplatzId(partNumber));

        disposition.setFinalNewProduction(production);

        return amount;
    }

    private int getWaitingQueueAmount(Disposition disposition) {
        int workInWaitingQueue = 0;
        for (ProductionOrder work : disposition.getProductionOrderInWaitingQueue()) {
            workInWaitingQueue += work.getAmount();
        }
        return workInWaitingQueue;
    }

    private Disposition getDispositionByPartNumber(int partNumber) {
        for (Disposition disposition : this.dispositions) {
            if (disposition.getPartNumber() == partNumber)
                return disposition;
        }
        return null;
    }

    public int getAmountOfBuyPartInCurrentProduction(int partNumber) {
        int amount = 0;

        Disposition dispoP1 = getDispositionByPartNumber(1);
        amount += this.partsNodeService.getAmountInTree(this.partsNodeService.getChildrenManufactoringNode(),
                partNumber) * dispoP1.getFinalNewProduction().getAmount();

        Disposition dispoP2 = getDispositionByPartNumber(2);
        amount += this.partsNodeService.getAmountInTree(this.partsNodeService.getWomanManufactoringNode(),
                partNumber) * dispoP2.getFinalNewProduction().getAmount();

        Disposition dispoP3 = getDispositionByPartNumber(3);
        amount += this.partsNodeService.getAmountInTree(this.partsNodeService.getManManufactoringNode(),
                partNumber) * dispoP3.getFinalNewProduction().getAmount();

        return amount;
    }

    public ArrayList<Disposition> getDispositions() {
        return this.dispositions;
    }

}
