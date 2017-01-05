package de.hska.workplacemanagement.domain;

import java.util.*;

public class ProductionLine {

    private WorkplaceNode firstWorkplace;
    private Map<Integer, WorkplaceNode> workplaceMap;

    public ProductionLine(WorkplaceNode firstWorkplace) {
        this.firstWorkplace = firstWorkplace;
        this.workplaceMap = new HashMap<>();

        this.setWorkplaces();
    }

    private ArrayList<Workplace> getAllWorkplaces() {
        WorkplaceNode temp = this.firstWorkplace;
        ArrayList<Workplace> workplaces = new ArrayList<Workplace>();

        workplaces.add(temp.getWorkplace());
        while (temp.getFollower() != null) {
            workplaces.add(temp.getFollower().getWorkplace());
            temp = temp.getFollower();
        }

        return workplaces;
    }

    private void setWorkplaces() {
        WorkplaceNode temp = this.firstWorkplace;
        this.workplaceMap.put(temp.getWorkplace().getNumber(), temp);

        while (temp.getFollower() != null) {
            this.workplaceMap.put(temp.getFollower().getWorkplace().getNumber(), temp.getFollower());
            temp = temp.getFollower();
        }
    }

    public void reset() {
        WorkplaceNode temp = this.firstWorkplace;

        temp.getWorkplace().setWorkingTime(0);
        temp.getWorkplace().setProductionOrders(new ArrayList<>());

        while (temp.getFollower() != null) {
            temp.getFollower().getWorkplace().setWorkingTime(0);
            temp.getFollower().getWorkplace().setProductionOrders(new ArrayList<>());
            temp = temp.getFollower();
        }
    }

    public void addProductionOrder(ProductionOrder order) {
        WorkplaceNode temp = null;

        if (order.getWorkplaceId() != 0) {
            temp = this.workplaceMap.getOrDefault(order.getWorkplaceId(), null);
        } else {
            temp = this.firstWorkplace;
        }

        temp.getWorkplace().getProductionOrders().add(order);

        if (order.getInWork()) {
            temp.getWorkplace().setProductionOrderInWork(order);
            temp.getWorkplace().setWorkingTime(temp.getWorkplace().getWorkingTime() - temp.getWorkplace().getSetUpTime());
        }
        temp.getWorkplace().setWorkingTime(
                temp.getWorkplace().getProductionTime() * temp.getWorkplace().getUpcomingProductionCount()
                        + (temp.getWorkplace().getProductionOrders().size() + temp.getWorkplace().getOrdersFromOtherWorkplaces().size()) * temp.getWorkplace().getSetUpTime());

        while (temp.getFollower() != null) {
            ArrayList<ProductionOrder> allOrders = temp.getWorkplace().getOrdersFromOtherWorkplaces();
            allOrders.addAll(temp.getWorkplace().getProductionOrders());

            temp.getFollower().getWorkplace().setOrdersFromOtherWorkplaces(allOrders);
            temp.getFollower().getWorkplace().setWorkingTime(
                    temp.getFollower().getWorkplace().getWorkingTime() * temp.getFollower().getWorkplace().getUpcomingProductionCount()
                            + (temp.getFollower().getWorkplace().getProductionOrders().size() + temp.getFollower().getWorkplace().getOrdersFromOtherWorkplaces().size()) * temp.getFollower().getWorkplace().getSetUpTime()
            );

            temp = temp.getFollower();
        }
    }

    public WorkplaceNode getFirstWorkplace() {
        return firstWorkplace;
    }

    public void setFirstWorkplace(WorkplaceNode firstWorkplace) {
        this.firstWorkplace = firstWorkplace;
    }

    public Map<Integer, WorkplaceNode> getWorkplaceMap() {
        return workplaceMap;
    }

    public void setWorkplaceMap(Map<Integer, WorkplaceNode> workplaceMap) {
        this.workplaceMap = workplaceMap;
    }
}
