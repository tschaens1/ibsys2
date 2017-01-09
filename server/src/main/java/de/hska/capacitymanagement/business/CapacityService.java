package de.hska.capacitymanagement.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hska.productionmanagement.business.ProductionService;
import de.hska.workplacemanagement.business.WorkplaceService;
import de.hska.workplacemanagement.domain.ProductionLine;
import de.hska.workplacemanagement.domain.ProductionOrder;
import de.hska.workplacemanagement.domain.Workplace;

@Service
public class CapacityService {

	@Autowired
	private WorkplaceService workplaceService;

	@Autowired
	private ProductionService productionService;

	public void initialize() {
		this.deployExistingCapacities();
		this.getCalculation();
	}

	public void deployExistingCapacities() {
		for (ProductionOrder productionOrderInWaitinglist : this.productionService.getProductionOrdersInWaitinglist()) {
			for (ProductionOrder orderInWork : this.productionService.getProductionOrdersInWork()) {
				ProductionOrder order = new ProductionOrder();
				if (orderInWork.getOrderId() == productionOrderInWaitinglist.getOrderId()) {
					order.setInWork(true);
					order.setAmount(productionOrderInWaitinglist.getAmount() + orderInWork.getAmount());
				} else {
					order.setInWork(false);
					order.setAmount(productionOrderInWaitinglist.getAmount());
				}
				order.setPeriod(this.productionService.getPeriod());
				order.setProductNumber(productionOrderInWaitinglist.getProductNumber());
				order.setWorkplaceId(productionOrderInWaitinglist.getWorkplaceId());

				final ProductionLine productionLine = this.workplaceService.getProductionLineMap()
						.get(productionOrderInWaitinglist.getProductNumber());
				productionLine.addProductionOrder(order);
			}
		}
	}

	public void getCalculation() {
		for (Workplace workplace : this.workplaceService.getAllWorkPlaces()) {
			System.out.println("Workplace: " + workplace.getNumber() + " ------------------------");
			System.out.println(" -- Working Time: " + workplace.getWorkingTime());
		}
	}
}
