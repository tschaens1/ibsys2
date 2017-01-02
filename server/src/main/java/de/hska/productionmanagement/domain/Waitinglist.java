package de.hska.productionmanagement.domain;

import de.hska.workplacemanagement.domain.ProductionOrder;

import java.util.ArrayList;

public class Waitinglist {

    private ArrayList<ProductionOrder> orders;

    public ArrayList<ProductionOrder> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<ProductionOrder> orders) {
        this.orders = orders;
    }
}
