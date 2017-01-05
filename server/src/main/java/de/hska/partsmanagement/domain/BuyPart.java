package de.hska.partsmanagement.domain;

public class BuyPart {

	private Integer number;
	private String description;
	private Double price;
	private Double partsValue;
	private Double newPartsValue;
	private Integer stockAmount;
	private Boolean isUsedInAllProducts;
	private Integer discountAmount;
	private Double procurementCosts;
	private Double timeToRebuy;
	private Double rebuyDerivation;
	private Double riskFactor;

	public BuyPart(Integer number, String description, Double price, Integer stockAmount, Boolean isUsedInAllProducts,
			Integer discountAmount, Double procurementCosts, Double timeToRebuy, Double rebuyDerivation) {
		this.number = number;
		this.description = description;
		this.price = price;
		this.stockAmount = stockAmount;
		this.isUsedInAllProducts = isUsedInAllProducts;
		this.discountAmount = discountAmount;
		this.procurementCosts = procurementCosts;
		this.timeToRebuy = timeToRebuy;
		this.rebuyDerivation = rebuyDerivation;

		this.partsValue = price;
		this.newPartsValue = price;

		this.setRiskFactor();
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPartsValue() {
		return partsValue;
	}

	public void setPartsValue(Double partsValue) {
		this.partsValue = partsValue;
	}

	public Double getNewPartsValue() {
		return newPartsValue;
	}

	public void setNewPartsValue(Double newPartsValue) {
		this.newPartsValue = newPartsValue;
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

	public Integer getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Integer discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Double getProcurementCosts() {
		return procurementCosts;
	}

	public void setProcurementCosts(Double procurementCosts) {
		this.procurementCosts = procurementCosts;
	}

	public Double getTimeToRebuy() {
		return timeToRebuy;
	}

	public void setTimeToRebuy(Double timeToRebuy) {
		this.timeToRebuy = timeToRebuy;
	}

	public Double getRebuyDerivation() {
		return rebuyDerivation;
	}

	public void setRebuyDerivation(Double rebuyDerivation) {
		this.rebuyDerivation = rebuyDerivation;
	}

	public Double getRiskFactor() {
		return riskFactor;
	}

	private void setRiskFactor() {
		double relation = this.rebuyDerivation / this.timeToRebuy;
		if (relation > 0.2) {
			this.riskFactor = 1.00;
			return;
		} else if (relation > 0.15) {
			this.riskFactor = 0.50;
			return;
		} else {
			this.riskFactor = 0.25;
			return;
		}
	}
}
