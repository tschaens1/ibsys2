package de.hska.procurementmanagement.domain;

public class BuyOrder {

    private Integer buyPartId;
    private BuyMode buyMode;
    private Integer amount;
    private Integer orderPeriod;
    private Double totalCosts;

    private Double materialCosts;
    private Double procurementCosts;
    private Double costsPerPiece;

    private Boolean isTooLate;

    public BuyOrder(Integer buyPartId, BuyMode buyMode, Integer amount, Integer orderPeriod, Double totalCosts) {
        this.buyPartId = buyPartId;
        this.buyMode = buyMode;
        this.amount = amount;
        this.orderPeriod = orderPeriod;
        this.totalCosts = totalCosts;
    }

    public BuyOrder(Integer buyPartId, BuyMode buyMode, Integer amount, Integer orderPeriod,
                    Double totalCosts, Double materialCosts, Double procurementCosts, Double costsPerPiece) {
        this.buyPartId = buyPartId;
        this.buyMode = buyMode;
        this.amount = amount;
        this.orderPeriod = orderPeriod;
        this.totalCosts = totalCosts;
        this.materialCosts = materialCosts;
        this.procurementCosts = procurementCosts;
        this.costsPerPiece = costsPerPiece;
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

    public Integer getOrderPeriod() {
        return orderPeriod;
    }

    public void setOrderPeriod(Integer orderPeriod) {
        this.orderPeriod = orderPeriod;
    }

    public Double getTotalCosts() {
        return totalCosts;
    }

    public void setTotalCosts(Double totalCosts) {
        this.totalCosts = totalCosts;
    }

    public Double getMaterialCosts() {
        return materialCosts;
    }

    public void setMaterialCosts(Double materialCosts) {
        this.materialCosts = materialCosts;
    }

    public Double getProcurementCosts() {
        return procurementCosts;
    }

    public void setProcurementCosts(Double procurementCosts) {
        this.procurementCosts = procurementCosts;
    }

    public Double getCostsPerPiece() {
        return costsPerPiece;
    }

    public void setCostsPerPiece(Double costsPerPiece) {
        this.costsPerPiece = costsPerPiece;
    }

    public Boolean getTooLate() {
        return isTooLate;
    }

    public void setTooLate(Boolean tooLate) {
        isTooLate = tooLate;
    }

    @Override
    public String toString() {
        return "BuyOrder{" +
                "buyPartId=" + buyPartId +
                ", buyMode=" + buyMode +
                ", amount=" + amount +
                ", orderPeriod=" + orderPeriod +
                ", totalCosts=" + totalCosts +
                ", materialCosts=" + materialCosts +
                ", procurementCosts=" + procurementCosts +
                ", costsPerPiece=" + costsPerPiece +
                '}';
    }
}
