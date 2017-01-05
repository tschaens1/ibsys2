package de.hska.warehousemanagement.domain;

public class WarehouseArticle {

	private int partNumber;
	private int amount;
	private double percentage;
	private double price;
	private double stockvalue;

	public WarehouseArticle(int partNumber, int amount, double percentage, double price, double stockvalue) {
		this.partNumber = partNumber;
		this.amount = amount;
		this.percentage = percentage;
		this.price = price;
		this.stockvalue = stockvalue;
	}

	public int getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(int partNumber) {
		this.partNumber = partNumber;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getStockvalue() {
		return stockvalue;
	}

	public void setStockvalue(double stockvalue) {
		this.stockvalue = stockvalue;
	}

}
