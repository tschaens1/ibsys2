package de.hska.dispositionmanagement.domain;

import de.hska.workplacemanagement.domain.ProductionOrder;

import java.util.ArrayList;

public class Disposition {

    private int partNumber;
    private int amount;

    private ProductionOrder sellwish;
    private int safetyStockvalue;
    private int warehouseStock;
    private ProductionOrder orderOnMachine;
    private ProductionOrder finalNewProduction;

    private ArrayList<ProductionOrder> productionOrderInWaitingQueue;
    private ArrayList<ProductionOrder> productionOrdersInWork;

    private Disposition parent;

    public Disposition() {

    }

    public Disposition(int partNumber, int safetyStockvalue, int warehouseStock, int amount, ProductionOrder finalNewProduction,
                       ProductionOrder sellwish, Disposition parent, ProductionOrder orderOnMachine,
                       ArrayList<ProductionOrder> productionOrderInWaitingQueue, ArrayList<ProductionOrder> productionOrdersInWork) {
        this.partNumber = partNumber;
        this.safetyStockvalue = safetyStockvalue;
        this.warehouseStock = warehouseStock;
        this.amount = amount;
        this.finalNewProduction = finalNewProduction;
        this.sellwish = sellwish;
        this.parent = parent;
        this.orderOnMachine = orderOnMachine;
        this.productionOrderInWaitingQueue = productionOrderInWaitingQueue;
        this.productionOrdersInWork = productionOrdersInWork;
    }

    public int getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(int partNumber) {
        this.partNumber = partNumber;
    }

    public int getSafetyStockvalue() {
        return safetyStockvalue;
    }

    public void setSafetyStockvalue(int safetyStockvalue) {
        this.safetyStockvalue = safetyStockvalue;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ProductionOrder getFinalNewProduction() {
        return finalNewProduction;
    }

    public void setFinalNewProduction(ProductionOrder finalNewProduction) {
        this.finalNewProduction = finalNewProduction;
    }

    public ProductionOrder getSellwish() {
        return sellwish;
    }

    public void setSellwish(ProductionOrder sellwish) {
        this.sellwish = sellwish;
    }

    public Disposition getParent() {
        return parent;
    }

    public void setParent(Disposition parent) {
        this.parent = parent;
    }

    public ArrayList<ProductionOrder> getProductionOrdersInWork() {
        return productionOrdersInWork;
    }

    public void setProductionOrdersInWork(ArrayList<ProductionOrder> productionOrdersInWork) {
        this.productionOrdersInWork = productionOrdersInWork;
    }

    public void setOrderOnMachine(ProductionOrder orderOnMachine) {
        this.orderOnMachine = orderOnMachine;
    }

    public ArrayList<ProductionOrder> getProductionOrderInWaitingQueue() {
        return productionOrderInWaitingQueue;
    }

    public void setProductionOrdersInWaitingQueue(ArrayList<ProductionOrder> productionOrdersInWaitingQueue) {
        this.productionOrderInWaitingQueue = productionOrdersInWaitingQueue;
    }

    public ArrayList<ProductionOrder> getOrders() {
        ArrayList<ProductionOrder> orders = new ArrayList<ProductionOrder>();
        orders.add(this.finalNewProduction);
        orders.add(this.sellwish);
        return orders;
    }

    public int getWarehouseStock() {
        return warehouseStock;
    }

    public void setWarehouseStock(int warehouseStock) {
        this.warehouseStock = warehouseStock;
    }

    @Override
    public String toString() {
        return "Disposition [partNumber=" + partNumber + ", amount=" + amount + ", finalNewProduction=" + finalNewProduction
                + ", sellwish=" + sellwish + ", orderOnMachine=" + orderOnMachine + ", safetyStockvalue="
                + safetyStockvalue + ", warehouseStock=" + warehouseStock + ", productionOrderInWaitingQueue="
                + productionOrderInWaitingQueue + ", productionOrdersInWork=" + productionOrdersInWork + ", parent=" + parent + "]";
    }

}
