package de.hska.dispositionmanagement.business;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hska.dispositionmanagement.domain.Disposition;
import de.hska.partsmanagement.business.PartsNodeService;
import de.hska.partsmanagement.business.PartsService;
import de.hska.partsmanagement.domain.PartNode;
import de.hska.planningmangement.business.PlanningService;
import de.hska.productionmanagement.business.ProductionService;
import de.hska.warehousemanagement.business.WarehouseService;
import de.hska.workplacemanagement.business.WorkplaceService;
import de.hska.workplacemanagement.domain.ProductionOrder;

@Service
public class DispositionService {

	@Autowired
	private PlanningService planningService;

	@Autowired
	private PartsNodeService partsNodeService;

	@Autowired
	private PartsService partsService;

	@Autowired
	private ProductionService productionService;

	@Autowired
	private WarehouseService warehouseService;

	@Autowired
	private WorkplaceService workplaceService;

	private ArrayList<Disposition> dispositionP1;
	private ArrayList<Disposition> dispositionP2;
	private ArrayList<Disposition> dispositionP3;
	private ArrayList<Disposition> dispositions;

	public void initialize() {
		ConstructContainers();

		this.dispoRecursively(this.partsNodeService.getChildrenManufactoringNode(), new Disposition());
		this.dispoRecursively(this.partsNodeService.getWomanManufactoringNode(), new Disposition());
		this.dispoRecursively(this.partsNodeService.getManManufactoringNode(), new Disposition());
		this.productionService.deployRemainingProductionOrders(dispositions);
	}

	private void ConstructContainers() {
		this.dispositionP1 = new ArrayList<>();
		this.dispositionP2 = new ArrayList<>();
		this.dispositionP3 = new ArrayList<>();
		this.dispositions = new ArrayList<>();
	}

	private void dispoRecursively(PartNode node, Disposition parent) {

		Disposition disposition = new Disposition();
		disposition.setPartNumber(node.getPartNumber());

		if (node.getPartNumber() != 1 || node.getPartNumber() != 2 || node.getPartNumber() != 3) {
			disposition.setParent(parent);
			disposition.setAmount(parent.getAmount());
		}

		disposition.setAmount(calculateDispositionAmount(node.getPartNumber(), disposition));

		if (node.getPartNumber() == 1)
			this.dispositionP1.add(disposition);

		if (node.getPartNumber() == 2)
			this.dispositionP2.add(disposition);

		if (node.getPartNumber() == 3)
			this.dispositionP3.add(disposition);

		this.dispositions.add(disposition);

		node.getParts()
				.stream()
				.filter(partNode -> partNode.getParts() != null)
				.forEach(partNode -> this.dispoRecursively(partNode, disposition));
	}

	private int calculateDispositionAmount(int partNumber, Disposition disposition) {
		int amountSellwish = 0;
		int amountSafetyStockvalue = 0;
		int amountWarehouse = 0;
		int amountWaitinglist = 0;
		int amountOrdersInWork = 0;

		ProductionOrder sellWishAndDirect = new ProductionOrder();

		if (this.planningService.getProductionItemForProduct(partNumber) != null) {
			sellWishAndDirect.setProductNumber(partNumber);
			sellWishAndDirect.setAmount(this.planningService.getProductionItemForProduct(partNumber).getQuantity());
			sellWishAndDirect.setPeriod(this.planningService.getPeriod());
			sellWishAndDirect.setInWork(false);

			disposition.setSellwish(sellWishAndDirect);
			amountSellwish = disposition.getSellwish().getAmount();
		} else if (disposition.getParent() != null) {
			sellWishAndDirect.setProductNumber(partNumber);
			sellWishAndDirect.setAmount(disposition.getParent().getProduction().getAmount()
					+ getWaitingQueueAmount(disposition.getParent()));
			sellWishAndDirect.setPeriod(this.planningService.getPeriod());
			sellWishAndDirect.setInWork(false);

			disposition.setSellwish(sellWishAndDirect);
			amountSellwish = disposition.getSellwish().getAmount();
		}

		if (this.planningService.getSafetystockItemForProduct(partNumber) != null) {
			disposition
					.setSafetyStockvalue(this.planningService.getSafetystockItemForProduct(partNumber).getQuantity());
			amountSafetyStockvalue = disposition.getSafetyStockvalue();
		}

		if (this.warehouseService.getWarehouseArticle(partNumber) != null) {
			int warehouseStock = this.warehouseService.getWarehouseArticle(partNumber).getAmount();
			if (this.partsService.getManufacturingPartById(partNumber).getUsedInAllProducts()) {
				warehouseStock = warehouseStock / 3;
			}
			disposition.setWarehouseStock(warehouseStock);
			amountWarehouse = disposition.getWarehouseStock();
		}

		if (this.productionService.getOrdersWaitinglistForProduct(partNumber) != null) {
			disposition.setProductionOrderInWaitingQueue(
					this.productionService.getOrdersWaitinglistForProduct(partNumber));
			for (ProductionOrder order : disposition.getProductionOrderInWaitingQueue()) {
				amountWaitinglist += order.getAmount();
			}
		}

		if (this.productionService.getOrdersInWorkForProduct(partNumber) != null) {
			disposition.setProductionOrders(this.productionService.getOrdersInWorkForProduct(partNumber));
			for (ProductionOrder order : disposition.getProductionOrders()) {
				amountOrdersInWork += order.getAmount();
			}
		}

		int amount = amountSellwish + amountSafetyStockvalue - amountWarehouse - amountOrdersInWork - amountWaitinglist;

		if (amount < 0)
			amount = 0;

		ProductionOrder production = new ProductionOrder();
		production.setProductNumber(partNumber);
		production.setAmount(amount);
		production.setPeriod(this.planningService.getPeriod());
		production.setInWork(false);
		production.setWorkplaceId(this.workplaceService.getArbeitsplatzId(partNumber));

		disposition.setProduction(production);

		return amount;
	}

	public int getWaitingQueueAmount(Disposition disposition) {
		int workInWaitingQueue = 0;
		for (ProductionOrder work : disposition.getProductionOrderInWaitingQueue()) {
			workInWaitingQueue += work.getAmount();
		}
		return workInWaitingQueue;
	}

	public Disposition getDispositionByPartNumber(int partNumber) {
		for (Disposition disposition : this.dispositions) {
			if (disposition.getPartNumber() == partNumber)
				return disposition;
		}
		return null;
	}

	public int getAmountOfBuyPartInCurrentProduction(int partNumber) {
		int amount = 0;

		Disposition dispoP1 = getDispositionByPartNumber(1);
		amount += this.partsNodeService.getAmountInTree(this.partsNodeService.getChildrenManufactoringNode(),
				partNumber) * dispoP1.getProduction().getAmount();

		Disposition dispoP2 = getDispositionByPartNumber(2);
		amount += this.partsNodeService.getAmountInTree(this.partsNodeService.getWomanManufactoringNode(),
				partNumber) * dispoP2.getProduction().getAmount();

		Disposition dispoP3 = getDispositionByPartNumber(3);
		amount += this.partsNodeService.getAmountInTree(this.partsNodeService.getManManufactoringNode(),
				partNumber) * dispoP3.getProduction().getAmount();

		return amount;
	}

	public ArrayList<Disposition> getDisposition() {
		return this.dispositions;
	}

}
