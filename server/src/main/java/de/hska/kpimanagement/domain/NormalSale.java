package de.hska.kpimanagement.domain;

public class NormalSale {
    private KpiContainer salesPrice;
    private KpiContainer productionCost;
    private KpiContainer profit;
    private KpiContainer profitPerUnit;

    public NormalSale() {
        this.salesPrice = new KpiContainer();
        this.productionCost = new KpiContainer();
        this.profit = new KpiContainer();
        this.profitPerUnit = new KpiContainer();
    }

    public KpiContainer getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(KpiContainer salesPrice) {
        this.salesPrice = salesPrice;
    }

    public KpiContainer getProductionCost() {
        return productionCost;
    }

    public void setProductionCost(KpiContainer productionCost) {
        this.productionCost = productionCost;
    }

    public KpiContainer getProfit() {
        return profit;
    }

    public void setProfit(KpiContainer profit) {
        this.profit = profit;
    }

    public KpiContainer getProfitPerUnit() {
        return profitPerUnit;
    }

    public void setProfitPerUnit(KpiContainer profitPerUnit) {
        this.profitPerUnit = profitPerUnit;
    }
}
