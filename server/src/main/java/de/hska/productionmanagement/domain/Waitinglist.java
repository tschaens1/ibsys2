package de.hska.productionmanagement.domain;

import java.util.ArrayList;

import de.hska.workplacemanagement.domain.ProductionOrder;

public class Waitinglist {

	private ArrayList<ProductionOrder> orders;

	public Waitinglist() {
		orders = new ArrayList<ProductionOrder>();
	}

	public ArrayList<ProductionOrder> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<ProductionOrder> orders) {
		this.orders = orders;
	}
}
