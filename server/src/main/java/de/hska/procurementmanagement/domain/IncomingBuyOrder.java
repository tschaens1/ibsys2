package de.hska.procurementmanagement.domain;

public class IncomingBuyOrder {

    private Integer number;
    private Integer buyPartId;
    private BuyMode buyMode;
    private Integer amount;
    private Integer period;
    private Double materialCosts;
    private Double buyCosts;
    private Double totalCosts;
    private Double costsPerPiece;
    private Integer arrivePeriod;
    private Integer arriveDay;

    public IncomingBuyOrder(Integer number, Integer buyPartId, BuyMode buyMode, Integer amount,
                            Integer period, Double materialCosts, Double buyCosts, Double totalCosts,
                            Double costsPerPiece, Integer arrivePeriod, Integer arriveDay) {
        this.number = number;
        this.buyPartId = buyPartId;
        this.buyMode = buyMode;
        this.amount = amount;
        this.period = period;
        this.materialCosts = materialCosts;
        this.buyCosts = buyCosts;
        this.totalCosts = totalCosts;
        this.costsPerPiece = costsPerPiece;
        this.arrivePeriod = arrivePeriod;
        this.arriveDay = arriveDay;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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

    public Double getMaterialCosts() {
        return materialCosts;
    }

    public void setMaterialCosts(Double materialCosts) {
        this.materialCosts = materialCosts;
    }

    public Double getBuyCosts() {
        return buyCosts;
    }

    public void setBuyCosts(Double buyCosts) {
        this.buyCosts = buyCosts;
    }

    public Double getTotalCosts() {
        return totalCosts;
    }

    public void setTotalCosts(Double totalCosts) {
        this.totalCosts = totalCosts;
    }

    public Double getCostsPerPiece() {
        return costsPerPiece;
    }

    public void setCostsPerPiece(Double costsPerPiece) {
        this.costsPerPiece = costsPerPiece;
    }

    public Integer getArrivePeriod() {
        return arrivePeriod;
    }

    public void setArrivePeriod(Integer arrivePeriod) {
        this.arrivePeriod = arrivePeriod;
    }

    public Integer getArriveDay() {
        return arriveDay;
    }

    public void setArriveDay(Integer arriveDay) {
        this.arriveDay = arriveDay;
    }
}
