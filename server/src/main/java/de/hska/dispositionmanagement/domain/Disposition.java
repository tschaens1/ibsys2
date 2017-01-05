package de.hska.dispositionmanagement.domain;

import java.util.ArrayList;

import de.hska.workplacemanagement.domain.ProductionOrder;

public class Disposition {

	private int partNumber;
	private int amount;

	private ProductionOrder production;
	private ProductionOrder sellwish;
	private ProductionOrder orderOnMachine;
	private int safetyStockvalue;
	private int warehouseStock;

	private ArrayList<ProductionOrder> productionOrderInWaitingQueue;
	private ArrayList<ProductionOrder> productionOrders;

	private Disposition parent;

	public Disposition() {

	}

	public Disposition(int partNumber, int safetyStockvalue, int warehouseStock, int amount, ProductionOrder production,
			ProductionOrder sellwish, Disposition parent, ProductionOrder orderOnMachine,
			ArrayList<ProductionOrder> productionOrderInWaitingQueue, ArrayList<ProductionOrder> productionOrders) {
		this.partNumber = partNumber;
		this.safetyStockvalue = safetyStockvalue;
		this.warehouseStock = warehouseStock;
		this.amount = amount;
		this.production = production;
		this.sellwish = sellwish;
		this.parent = parent;
		this.orderOnMachine = orderOnMachine;
		this.productionOrderInWaitingQueue = productionOrderInWaitingQueue;
		this.productionOrders = productionOrders;
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

	public ProductionOrder getProduction() {
		return production;
	}

	public void setProduction(ProductionOrder production) {
		this.production = production;
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

	public ArrayList<ProductionOrder> getProductionOrders() {
		return productionOrders;
	}

	public void setProductionOrders(ArrayList<ProductionOrder> productionOrders) {
		this.productionOrders = productionOrders;
	}

	public ProductionOrder getOrderOnMachine() {
		return orderOnMachine;
	}

	public void setOrderOnMachine(ProductionOrder orderOnMachine) {
		this.orderOnMachine = orderOnMachine;
	}

	public ArrayList<ProductionOrder> getProductionOrderInWaitingQueue() {
		return productionOrderInWaitingQueue;
	}

	public void setProductionOrderInWaitingQueue(ArrayList<ProductionOrder> productionOrderInWaitingQueue) {
		this.productionOrderInWaitingQueue = productionOrderInWaitingQueue;
	}

	public ArrayList<ProductionOrder> getOrders() {
		ArrayList<ProductionOrder> orders = new ArrayList<ProductionOrder>();
		orders.add(this.production);
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
		return "Disposition [partNumber=" + partNumber + ", amount=" + amount + ", production=" + production
				+ ", sellwish=" + sellwish + ", orderOnMachine=" + orderOnMachine + ", safetyStockvalue="
				+ safetyStockvalue + ", warehouseStock=" + warehouseStock + ", productionOrderInWaitingQueue="
				+ productionOrderInWaitingQueue + ", productionOrders=" + productionOrders + ", parent=" + parent + "]";
	}

}
