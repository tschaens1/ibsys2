package de.hska.dispositionmanagement.domain;

import java.util.ArrayList;

import de.hska.planningmangement.domain.PlanningPosition;
import de.hska.workplacemanagement.domain.ProductionOrder;

public class Disposition {

	private int partNumber;
	private int safetyStockvalue;
	private int amount;
	private PlanningPosition production;
	private PlanningPosition selldirect;
	private Disposition parent;
	private ProductionOrder orderOnMachine;
	private ArrayList<ProductionOrder> productionOrderInWaitingQueue;
	private ArrayList<ProductionOrder> productionOrders;

	public Disposition() {

	}

	public Disposition(int partNumber, int safetyStockvalue, int amount, PlanningPosition production,
			PlanningPosition selldirect, Disposition parent, ProductionOrder orderOnMachine,
			ArrayList<ProductionOrder> productionOrderInWaitingQueue, ArrayList<ProductionOrder> productionOrders) {
		this.partNumber = partNumber;
		this.safetyStockvalue = safetyStockvalue;
		this.amount = amount;
		this.production = production;
		this.selldirect = selldirect;
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

	public PlanningPosition getProduction() {
		return production;
	}

	public void setProduction(PlanningPosition production) {
		this.production = production;
	}

	public PlanningPosition getSelldirect() {
		return selldirect;
	}

	public void setSelldirect(PlanningPosition selldirect) {
		this.selldirect = selldirect;
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

	public ArrayList<PlanningPosition> getOrders() {
		ArrayList<PlanningPosition> orders = new ArrayList<PlanningPosition>();
		orders.add(this.production);
		orders.add(this.selldirect);
		return orders;
	}

}
