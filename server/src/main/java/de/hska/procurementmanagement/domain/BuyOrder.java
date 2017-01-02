package de.hska.procurementmanagement.domain;

public class BuyOrder {

    private Integer buyPartId;
    private BuyMode buyMode;
    private Integer amount;
    private Integer period;
    private Double costs;

    public BuyOrder(Integer buyPartId, BuyMode buyMode, Integer amount, Integer period, Double costs) {
        this.buyPartId = buyPartId;
        this.buyMode = buyMode;
        this.amount = amount;
        this.period = period;
        this.costs = costs;
    }

    public Integer getBuyPartId() {
        return buyPartId;
    }

    public void setBuyPartId(Integer buyPartId) {
        this.buyPartId = buyPartId;
    }

    public BuyMode getBuyMode() {
        return buyMode;
    }

    public void setBuyMode(BuyMode buyMode) {
        this.buyMode = buyMode;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Double getCosts() {
        return costs;
    }

    public void setCosts(Double costs) {
        this.costs = costs;
    }
}
