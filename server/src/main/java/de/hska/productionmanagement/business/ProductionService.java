package de.hska.productionmanagement.business;

import java.text.ParseException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hska.dispositionmanagement.domain.Disposition;
import de.hska.filemanagement.domain.JsonFile;
import de.hska.planningmangement.business.PlanningService;
import de.hska.planningmangement.domain.PlanningPosition;
import de.hska.productionmanagement.domain.Waitinglist;
import de.hska.warehousemanagement.business.WarehouseService;
import de.hska.warehousemanagement.domain.WarehouseArticle;
import de.hska.workplacemanagement.domain.ProductionOrder;

@Service
public class ProductionService {

	@Autowired
	private PlanningService planningService;

	@Autowired
	private WarehouseService warehouseService;

	private int period;
	private Waitinglist waitinglist;
	private ArrayList<ProductionOrder> ordersinwork;
	private ArrayList<ProductionOrder> safetystockvalues;
	private ArrayList<ProductionOrder> stockvalues;
	private ArrayList<ProductionOrder> planning;
	private ArrayList<ProductionOrder> production;

	public void initialize(JsonFile jsonFile) throws ParseException {
		ConstructContainers();

		JSONObject jsonResultsObject = new JSONObject(jsonFile.getContent());
		JSONObject resultsJSON = jsonResultsObject.getJSONObject("results");
		this.period = resultsJSON.getInt("period");

		this.updateWaitinglist(resultsJSON);
		this.updateOrdersInWork(resultsJSON);
		this.updateSafetyStockvalue();
		this.updateStockvalue();
		this.updatePlanning();
	}

	public void ConstructContainers() {
		this.waitinglist = new Waitinglist();
		this.ordersinwork = new ArrayList<ProductionOrder>();
		this.safetystockvalues = new ArrayList<ProductionOrder>();
		this.stockvalues = new ArrayList<ProductionOrder>();
		this.planning = new ArrayList<ProductionOrder>();
		this.production = new ArrayList<ProductionOrder>();

	}

	public void updateWaitinglist(JSONObject resultsJSON) {
		ArrayList<ProductionOrder> orders = new ArrayList<ProductionOrder>();

		JSONObject jsonWaitinglistworkstationObject = resultsJSON.getJSONObject("waitinglistworkstations");
		JSONArray workplacesJSONArray = jsonWaitinglistworkstationObject.getJSONArray("workplace");

		for (int i = 0; i < workplacesJSONArray.length(); i++) {
			JSONObject objectInArray = workplacesJSONArray.getJSONObject(i);
			if (objectInArray.has("waitinglist")) {
				Object item = objectInArray.get("waitinglist");
				if (item instanceof JSONArray) {
					JSONArray waitinglistJSONArray = (JSONArray) item;
					for (int a = 0; a < waitinglistJSONArray.length(); a++) {
						JSONObject objectInWaitinglistArray = waitinglistJSONArray.getJSONObject(a);

						int period = Integer.parseInt(objectInWaitinglistArray.get("period").toString());
						int amount = Integer.parseInt(objectInWaitinglistArray.get("amount").toString());
						int productNumber = Integer.parseInt(objectInWaitinglistArray.get("item").toString());

						orders.add(new ProductionOrder(productNumber, amount, period, false));
					}
				} else {
					JSONObject waitinglistJSONObject = (JSONObject) item;

					int period = Integer.parseInt(waitinglistJSONObject.get("period").toString());
					int amount = Integer.parseInt(waitinglistJSONObject.get("amount").toString());
					int productNumber = Integer.parseInt(waitinglistJSONObject.get("item").toString());

					orders.add(new ProductionOrder(productNumber, amount, period, false));
				}
				this.waitinglist.setOrders(orders);
			}
		}
	}

	public void updateOrdersInWork(JSONObject jsonResultsObject) {
		JSONObject jsonWatinglistworkstationObject = jsonResultsObject.getJSONObject("ordersinwork");
		JSONArray workplacesJSONArray = jsonWatinglistworkstationObject.getJSONArray("workplace");

		for (int i = 0; i < workplacesJSONArray.length(); i++) {
			JSONObject jsonWorkplaceObject = workplacesJSONArray.getJSONObject(i);

			int period = Integer.parseInt(jsonWorkplaceObject.get("period").toString());
			int amount = Integer.parseInt(jsonWorkplaceObject.get("amount").toString());
			int productNumber = Integer.parseInt(jsonWorkplaceObject.get("item").toString());

			this.ordersinwork.add(new ProductionOrder(productNumber, amount, period, true));
		}
	}

	public void updateSafetyStockvalue() {
		for (PlanningPosition position : this.planningService.getSafetystockItems()) {
			this.safetystockvalues.add(new ProductionOrder(position.getArticle(), position.getQuantity(),
					this.planningService.getPeriod(), false));
		}
	}

	public void updateStockvalue() {
		for (WarehouseArticle article : this.warehouseService.getWarehouseArticles()) {
			this.stockvalues.add(new ProductionOrder(article.getPartNumber(), article.getAmount(),
					this.warehouseService.getPeriod(), false));
		}
	}

	public void updatePlanning() {
		for (PlanningPosition position : this.planningService.getSellwishItems()) {
			this.planning.add(new ProductionOrder(position.getArticle(), position.getQuantity(),
					this.planningService.getPeriod(), false));
		}
	}

	public void setProductionOrders(ArrayList<ProductionOrder> productionOrders) {
		if (this.ordersinwork != null)
			this.production.addAll(this.ordersinwork);
		if (this.waitinglist.getOrders() != null)
			this.production.addAll(this.waitinglist.getOrders());
		if (productionOrders != null)
			this.production.addAll(productionOrders);
	}

	public ArrayList<ProductionOrder> getProductionOrdersInWork() {
		return this.ordersinwork;
	}

	public ArrayList<ProductionOrder> getProductionOrdersInWaitinglist() {
		return this.waitinglist.getOrders();
	}

	public ArrayList<ProductionOrder> getOrdersInWorkForProduct(int productNumber) {
		ArrayList<ProductionOrder> productionOrdersInWork = new ArrayList<ProductionOrder>();
		for (ProductionOrder order : this.ordersinwork) {
			if (order.getProductNumber() == productNumber) {
				productionOrdersInWork.add(order);
			}
		}
		return productionOrdersInWork;
	}

	public ArrayList<ProductionOrder> getOrdersWaitinglistForProduct(int productNumber) {
		ArrayList<ProductionOrder> productionOrdersInWaitinglist = new ArrayList<ProductionOrder>();
		for (ProductionOrder order : this.waitinglist.getOrders()) {
			if (order.getProductNumber() == productNumber) {
				productionOrdersInWaitinglist.add(order);
			}
		}
		return productionOrdersInWaitinglist;
	}

	public void deployRemainingProductionOrders(ArrayList<Disposition> dispositions) {
		for (ProductionOrder order : this.getProductionOrdersInWork()) {
			for (Disposition disposition : dispositions) {
				if (disposition.getPartNumber() == order.getProductNumber()) {
					disposition.setOrderOnMachine(order);
				}
			}
		}

		for (Disposition disposition : dispositions) {
			disposition.setProductionOrderInWaitingQueue(new ArrayList<>());
			for (ProductionOrder order : this.getProductionOrdersInWaitinglist()) {
				if (disposition.getPartNumber() == order.getProductNumber()) {
					disposition.getProductionOrderInWaitingQueue().add(order);
				}
			}
		}
	}

	public int getPeriod() {
		return period;
	}

}
