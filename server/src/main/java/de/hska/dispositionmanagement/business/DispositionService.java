package de.hska.dispositionmanagement.business;

import de.hska.dispositionmanagement.domain.Disposition;
import de.hska.filemanagement.domain.JsonFile;
import de.hska.partsmanagement.business.PartsNodeService;
import de.hska.partsmanagement.business.PartsService;
import de.hska.partsmanagement.domain.PartNode;
import de.hska.planningmangement.business.PlanningService;
import de.hska.planningmangement.domain.PlanningPosition;
import de.hska.productionmanagement.business.ProductionService;
import de.hska.warehousemanagement.business.WarehouseService;
import de.hska.workplacemanagement.business.WorkplaceService;
import de.hska.workplacemanagement.domain.ProductionOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;

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

	public void initialize(JsonFile jsonFile) throws ParseException {
		dispositionP1 = new ArrayList<Disposition>();
		dispositionP2 = new ArrayList<Disposition>();
		dispositionP3 = new ArrayList<Disposition>();
		dispositions = new ArrayList<Disposition>();

		this.dispoRecursively(this.partsNodeService.getChildrenManufactoringNode(), new Disposition());
		this.dispoRecursively(this.partsNodeService.getWomanManufactoringNode(), new Disposition());
		this.dispoRecursively(this.partsNodeService.getManManufactoringNode(), new Disposition());
		this.productionService.deployRemainingProductionOrders(dispositions);
		this.setProductionOfDisposition();
	}

	public void dispoRecursively(PartNode node, Disposition parent) {

		Disposition disposition = new Disposition();
		disposition.setPartNumber(node.getPartNumber());

		if (node.getPartNumber() != 1 || node.getPartNumber() != 2 || node.getPartNumber() != 3) {
			disposition.setParent(parent);
		}

		disposition.setProduction(planningService.getProductionItemForProduct(node.getPartNumber()));
		disposition.setSelldirect(planningService.getSelldirectItemForProduct(node.getPartNumber()));

		PlanningPosition safetyStockvaluePosition = planningService.getSafetystockItemForProduct(node.getPartNumber());
		disposition.setSafetyStockvalue(safetyStockvaluePosition.getQuantity());

		if (node.getPartNumber() == 1)
			this.dispositionP1.add(disposition);

		if (node.getPartNumber() == 2)
			this.dispositionP2.add(disposition);

		if (node.getPartNumber() == 3)
			this.dispositionP3.add(disposition);

		this.dispositions.add(disposition);

		for (int i = 0; i < node.getParts().size(); i++) {
			if (node.getParts().get(i).getParts() != null) {
				this.dispoRecursively(node.getParts().get(i), disposition);
			}
		}
	}

	public void setProductionOfDisposition() {

		ArrayList<ProductionOrder> productionOrders = new ArrayList<ProductionOrder>();
		ArrayList<ProductionOrder> productionOrdersWithUsedIn = new ArrayList<ProductionOrder>();

		for (int i = 0; i < this.dispositions.size(); i++) {
			this.dispositions.get(i).setProductionOrders(new ArrayList<ProductionOrder>());

			int amountProduction = this.dispositions.get(i).getProduction().getQuantity();
			int amountSafetyStockvalue = this.dispositions.get(i).getSafetyStockvalue();
			int amountWarehouse = this.warehouseService.getWarehouseArticle(this.dispositions.get(i).getPartNumber())
					.getAmount();
			int amountOrdersInWork = this.productionService
					.getOrdersInWorkForProduct(this.dispositions.get(i).getPartNumber()).getAmount();
			int amountWaitinglist = this.productionService
					.getWaitinglistForProduct(this.dispositions.get(i).getPartNumber()).getAmount();

			int amount = amountProduction + amountSafetyStockvalue - amountWarehouse + amountOrdersInWork
					+ amountWaitinglist;
			this.dispositions.get(i).setAmount(amount);

			if (this.dispositions.get(i).getAmount() <= 0)
				this.dispositions.get(i).setAmount(0);
			else {
				this.dispositions.get(i).getProductionOrders()
						.add(new ProductionOrder(this.dispositions.get(i).getPartNumber(),
								this.dispositions.get(i).getAmount(), this.planningService.getPeriod()));
			}
		}

		for (Disposition disposition : this.dispositions) {
			productionOrders.addAll(disposition.getProductionOrders());
		}

		for (ProductionOrder order : productionOrders) {
			if (productionOrdersWithUsedIn.isEmpty())
				productionOrdersWithUsedIn.add(order);

			for (ProductionOrder orderWithUsedIn : productionOrdersWithUsedIn) {
				if (order.getProductNumber() == orderWithUsedIn.getProductNumber() && this.partsService
						.getManufacturingPartById(orderWithUsedIn.getProductNumber()).getUsedInAllProducts()) {
					int amount = order.getAmount() + orderWithUsedIn.getAmount();
					orderWithUsedIn.setAmount(amount);
					break;
				}
			}
		}

		this.productionService.setProductionOrders(productionOrdersWithUsedIn);

		for (ProductionOrder order : productionOrdersWithUsedIn) {
			order.setWorkplaceId(this.workplaceService.getArbeitsplatzId(order.getProductNumber()));
		}

		this.workplaceService.reset();

		for (ProductionOrder order : productionOrders) {
			this.workplaceService.getProductionLineMap().get(Integer.valueOf(order.getProductNumber()))
					.addProductionOrder(order);
		}
		this.productionService.deployRemainingProductionOrders(this.dispositions);
	}

}
