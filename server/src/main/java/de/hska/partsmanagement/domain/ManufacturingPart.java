package de.hska.partsmanagement.domain;

public class ManufacturingPart {

    private Integer number;
    private String description;
    private Double partsValue;
    private Integer stockAmount;
    private Boolean isUsedInAllProducts;

    public ManufacturingPart(Integer number, String description, Double partsValue, Integer stockAmount, Boolean isUsedInAllProducts) {
        this.number = number;
        this.description = description;
        this.partsValue = partsValue;
        this.stockAmount = stockAmount;
        this.isUsedInAllProducts = isUsedInAllProducts;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPartsValue() {
        return partsValue;
    }

    public void setPartsValue(Double partsValue) {
        this.partsValue = partsValue;
    }

    public Integer getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(Integer stockAmount) {
        this.stockAmount = stockAmount;
    }

    public Boolean getUsedInAllProducts() {
        return isUsedInAllProducts;
    }

    public void setUsedInAllProducts(Boolean usedInAllProducts) {
        isUsedInAllProducts = usedInAllProducts;
    }
}
