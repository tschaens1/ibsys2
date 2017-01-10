package de.hska.capacitymanagement.business;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hska.capacitymanagement.domain.Capacity;
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

	private final static int DAYS_PER_WEEK = 5;
	private final static int SHIFT_DURATION = 2400;

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

		ArrayList<Workplace> workplaces = new ArrayList<Workplace>();
		for (Workplace workplace : this.workplaceService.getAllWorkPlaces()) {
			boolean contains = false;
			for (Workplace workplaceUnique : workplaces) {
				if (workplace.getNumber() == workplaceUnique.getNumber())
					contains = true;
			}
			if (!contains) {
				workplaces.add(workplace);
			} else {
				for (Workplace workplaceUnique : workplaces) {
					if (workplace.getNumber() == workplaceUnique.getNumber())
						workplaceUnique.setWorkingTime(workplaceUnique.getWorkingTime() + workplace.getWorkingTime());
				}
			}
		}
		for (Workplace workplace : workplaces) {
			this.calculateShifts(workplace);
			System.out.print("Kapa: " + workplace.getWorkingTime());
		}

		for (Capacity capacity : this.capacities) {
			System.out.println(" Capacity: - Station - " + capacity.getStation() + " - Shift - " + capacity.getShift()
					+ " - Overtime - " + capacity.getOvertime());
		}
	}

	public void calculateShifts(Workplace workplace) {
		Capacity capacity = new Capacity();
		capacity.setStation(workplace.getNumber());

		int overtime = 0;
		int shift = 0;

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

		this.capacities.add(capacity);
	}

	public ArrayList<Capacity> getCapacities() {
		return this.capacities;
	}
}
