package de.hska.capacitymanagement.business;

import de.hska.capacitymanagement.domain.Capacity;
import de.hska.dispositionmanagement.business.DispositionService;
import de.hska.dispositionmanagement.domain.Disposition;
import de.hska.partsmanagement.business.PartsService;
import de.hska.productionmanagement.business.ProductionService;
import de.hska.workplacemanagement.business.WorkplaceService;
import de.hska.workplacemanagement.domain.ProductionLine;
import de.hska.workplacemanagement.domain.ProductionOrder;
import de.hska.workplacemanagement.domain.Workplace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

@Service
public class CapacityService {

    private final static int DAYS_PER_WEEK = 5;

    @Autowired
    private WorkplaceService workplaceService;

    @Autowired
    private ProductionService productionService;

    @Autowired
    private DispositionService dispositionService;

    @Autowired
    private PartsService partsService;

    private ArrayList<Capacity> capacities;

    public void initialize() {
        capacities = new ArrayList<>();
        this.deployExistingCapacities();
        this.getCalculation();
    }

    private void deployExistingCapacities() {
        ArrayList<ProductionOrder> tmpMergedContent = new ArrayList<>();

        for (ProductionOrder productionOrderInWaitinglist : productionService.getProductionOrdersInWaitinglist()) {
            for (ProductionOrder orderInWork : this.productionService.getProductionOrdersInWork()) {
                ProductionOrder order = new ProductionOrder();
                if (Objects.equals(orderInWork.getOrderId(), productionOrderInWaitinglist.getOrderId()) &&
                        Objects.equals(orderInWork.getWorkplaceId(), productionOrderInWaitinglist.getWorkplaceId())) {
                    order.setInWork(true);
                    order.setOrderId(orderInWork.getOrderId());
                    order.setAmount(productionOrderInWaitinglist.getAmount() + orderInWork.getAmount());
                    order.setPeriod(this.productionService.getPeriod());
                    order.setProductNumber(productionOrderInWaitinglist.getProductNumber());
                    order.setWorkplaceId(productionOrderInWaitinglist.getWorkplaceId());
                    tmpMergedContent.add(order);
                }
            }
        }

        for (ProductionOrder productionOrderInWaitinglist : productionService.getProductionOrdersInWaitinglist()) {
            boolean contains = false;
            for (ProductionOrder tmpOrder : tmpMergedContent) {
                if (Objects.equals(tmpOrder.getOrderId(), productionOrderInWaitinglist.getOrderId())&&
                        Objects.equals(tmpOrder.getWorkplaceId(), productionOrderInWaitinglist.getWorkplaceId())) {
                    contains = true;
                    break;
                }
            }
            if (!contains) {
                ProductionOrder order = new ProductionOrder();
                order.setInWork(false);
                order.setOrderId(productionOrderInWaitinglist.getOrderId());
                order.setAmount(productionOrderInWaitinglist.getAmount());
                order.setPeriod(this.productionService.getPeriod());
                order.setProductNumber(productionOrderInWaitinglist.getProductNumber());
                order.setWorkplaceId(productionOrderInWaitinglist.getWorkplaceId());
                tmpMergedContent.add(order);
            }
        }

        for (ProductionOrder productionOrderInWork : productionService.getProductionOrdersInWork()) {
            boolean contains = false;
            for (ProductionOrder tmpOrder : tmpMergedContent) {
                if (Objects.equals(tmpOrder.getOrderId(), productionOrderInWork.getOrderId()) &&
                        Objects.equals(tmpOrder.getWorkplaceId(), productionOrderInWork.getWorkplaceId())) {
                    contains = true;
                    break;
                }
            }
            if (!contains) {
                ProductionOrder order = new ProductionOrder();
                order.setInWork(true);
                order.setOrderId(productionOrderInWork.getOrderId());
                order.setAmount(productionOrderInWork.getAmount());
                order.setPeriod(this.productionService.getPeriod());
                order.setProductNumber(productionOrderInWork.getProductNumber());
                order.setWorkplaceId(productionOrderInWork.getWorkplaceId());
                tmpMergedContent.add(order);
            }
        }

        for (Disposition disposition : dispositionService.getDispositions()) {
            tmpMergedContent.add(disposition.getFinalNewProduction());
        }

        for (ProductionOrder order : tmpMergedContent) {
            System.out.println(order.toString());
            final ProductionLine productionLine = this.workplaceService.getProductionLineMap()
                    .get(order.getProductNumber());
            productionLine.addProductionOrder(order);
        }
    }

    private void getCalculation() {
        ArrayList<Workplace> workplaces = new ArrayList<>();
        for (Workplace workplace : this.workplaceService.getAllWorkPlaces()) {
            boolean contains = false;
            for (Workplace workplaceUnique : workplaces) {
                if (Objects.equals(workplace.getNumber(), workplaceUnique.getNumber()))
                    contains = true;
            }
            if (!contains) {
                workplaces.add(workplace);
            } else {
                workplaces.stream().filter(workplaceUnique -> Objects.equals(workplace.getNumber(), workplaceUnique.getNumber())).forEach(workplaceUnique -> workplaceUnique.setWorkingTime(workplaceUnique.getWorkingTime() + workplace.getWorkingTime()));
            }
        }
        for (Workplace workplace : workplaces) {
            this.calculateShifts(workplace);
        }
    }

    private void calculateShifts(Workplace workplace) {
        Capacity capacity = new Capacity();
        capacity.setStation(workplace.getNumber());

        int overtime;
        int shift;

        if (workplace.getWorkingTime() <= 2400) {
            shift = 1;
            overtime = 0;
        } else if (workplace.getWorkingTime() <= 3600) {
            shift = 1;
            overtime = (3600 - workplace.getWorkingTime()) / DAYS_PER_WEEK;
        } else if (workplace.getWorkingTime() <= 4800) {
            shift = 2;
            overtime = 0;
        } else if (workplace.getWorkingTime() <= 6000) {
            shift = 2;
            overtime = (6000 - workplace.getWorkingTime()) / DAYS_PER_WEEK;
        } else {
            shift = 3;
            overtime = 0;
        }

        capacity.setShift(shift);
        capacity.setOvertime(overtime);

        if (workplace.getWorkingTime() > 7200) {
            capacity.setTooMuchWork(true);
        }

        this.capacities.add(capacity);
        this.capacities.sort(Comparator.comparing(Capacity::getStation));
    }

    public ArrayList<Capacity> getCapacities() {
        return this.capacities;
    }
}
