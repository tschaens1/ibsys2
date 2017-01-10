package de.hska.capacitymanagement.business;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hska.dispositionmanagement.business.DispositionService;
import de.hska.dispositionmanagement.domain.Disposition;
import de.hska.partsmanagement.business.PartsService;
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

	@Autowired
	private DispositionService dispositionService;

	@Autowired
	private PartsService partsService;

	public void initialize() {
		this.deployExistingCapacities();
		this.getCalculation();
	}

	public void deployExistingCapacities() {
		ArrayList<ProductionOrder> tmpMergedContent = new ArrayList<ProductionOrder>();

		for (ProductionOrder productionOrderInWaitinglist : productionService.getProductionOrdersInWaitinglist()) {
			for (ProductionOrder orderInWork : this.productionService.getProductionOrdersInWork()) {
				ProductionOrder order = new ProductionOrder();
				if (orderInWork.getOrderId() == productionOrderInWaitinglist.getOrderId()) {
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
				if (tmpOrder.getOrderId() == productionOrderInWaitinglist.getOrderId()) {
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
				if (tmpOrder.getOrderId() == productionOrderInWork.getOrderId()) {
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

		ArrayList<ProductionOrder> dispositionOrder = new ArrayList<ProductionOrder>();
		for (Disposition disposition : dispositionService.getDisposition()) {
			if (this.partsService.getManufacturingPartById(disposition.getProduction().getProductNumber())
					.getUsedInAllProducts()) {
				boolean contains = false;
				for (ProductionOrder order : dispositionOrder) {
					if (disposition.getProduction().getProductNumber() == order.getProductNumber())
						contains = true;
				}
				if (!contains)
					dispositionOrder.add(disposition.getProduction());

				for (ProductionOrder order : dispositionOrder) {
					if (disposition.getProduction().getProductNumber() == order.getProductNumber()) {
						if (contains)
							order.setAmount(order.getAmount() + disposition.getProduction().getAmount());
						else
							order.setAmount(disposition.getProduction().getAmount());
					}
				}
			} else {
				dispositionOrder.add(disposition.getProduction());
			}
		}

		tmpMergedContent.addAll(dispositionOrder);

		System.out.println("Production orders --------------> <-------------");
		for (ProductionOrder order : tmpMergedContent) {
			System.out.println(order.toString());
			final ProductionLine productionLine = this.workplaceService.getProductionLineMap()
					.get(order.getProductNumber());
			productionLine.addProductionOrder(order);
		}
	}

	public void getCalculation() {
		for (Workplace workplace : this.workplaceService.getAllWorkPlaces()) {
			System.out.println("Workplace: " + workplace.getName() + " ------------------------");
			System.out.println(" -- Working Time: " + workplace.getWorkingTime());
		}
	}
}
