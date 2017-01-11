package de.hska.workplacemanagement.domain;

import java.util.ArrayList;

public class Workplace {

	private String name;
	private Integer number;
	private Integer setUpTime;
	private Integer productionTime;
	private ArrayList<ProductionOrder> productionOrders;
	private ArrayList<ProductionOrder> ordersFromOtherWorkplaces;
	private ProductionOrder productionOrderInWork;
	private Integer outputProductId;
	private Integer workingTime;

	public Workplace(Integer number, Integer outputId, Integer productionTime, Integer setUpTime) {
		this.number = number;
		this.setUpTime = setUpTime;
		this.productionTime = productionTime;

		this.number = number;
		this.setUpTime = setUpTime;
		this.productionTime = productionTime;
		this.outputProductId = outputId;
		this.name = number + "_" + outputId;

		this.productionOrders = new ArrayList<ProductionOrder>();
		this.ordersFromOtherWorkplaces = new ArrayList<ProductionOrder>();
		this.workingTime = 0;
	}

	public Integer getUpcomingProductionCount() {
		Integer count = 0;

		for (int i = 0; i < this.productionOrders.size(); i++) {
			count += this.productionOrders.get(i).getAmount();
		}

		for (int i = 0; i < this.ordersFromOtherWorkplaces.size(); i++) {
			count += this.ordersFromOtherWorkplaces.get(i).getAmount();
		}

		if (this.productionOrderInWork != null) {
			count += this.productionOrderInWork.getAmount();
		}

		return count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getSetUpTime() {
		return setUpTime;
	}

	public void setSetUpTime(Integer setUpTime) {
		this.setUpTime = setUpTime;
	}

	public Integer getProductionTime() {
		return productionTime;
	}

	public void setProductionTime(Integer productionTime) {
		this.productionTime = productionTime;
	}

	public ArrayList<ProductionOrder> getProductionOrders() {
		return productionOrders;
	}

	public void setProductionOrders(ArrayList<ProductionOrder> productionOrders) {
		this.productionOrders = productionOrders;
	}

	public ArrayList<ProductionOrder> getOrdersFromOtherWorkplaces() {
		return ordersFromOtherWorkplaces;
	}

	public void setOrdersFromOtherWorkplaces(ArrayList<ProductionOrder> ordersFromOtherWorkplaces) {
		this.ordersFromOtherWorkplaces = ordersFromOtherWorkplaces;
	}

	public ProductionOrder getProductionOrderInWork() {
		return productionOrderInWork;
	}

	public void setProductionOrderInWork(ProductionOrder productionOrderInWork) {
		this.productionOrderInWork = productionOrderInWork;
	}

	public Integer getOutputProductId() {
		return outputProductId;
	}

	public void setOutputProductId(Integer outputProductId) {
		this.outputProductId = outputProductId;
	}

	public Integer getWorkingTime() {
		return workingTime;
	}

	public void setWorkingTime(Integer workingTime) {
		this.workingTime = workingTime;
	}

	@Override
	public String toString() {
		return "Workplace [name=" + name + ", number=" + number + ", setUpTime=" + setUpTime + ", productionTime="
				+ productionTime + ", productionOrders=" + productionOrders + ", ordersFromOtherWorkplaces="
				+ ordersFromOtherWorkplaces + ", productionOrderInWork=" + productionOrderInWork + ", outputProductId="
				+ outputProductId + ", workingTime=" + workingTime + "]";
	}

}
