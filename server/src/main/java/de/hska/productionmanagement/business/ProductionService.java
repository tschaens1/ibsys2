package de.hska.productionmanagement.business;

import de.hska.dispositionmanagement.domain.Disposition;
import de.hska.filemanagement.domain.JsonFile;
import de.hska.partsmanagement.business.PartsService;
import de.hska.planningmangement.business.PlanningService;
import de.hska.planningmangement.domain.PlanningPosition;
import de.hska.productionmanagement.domain.Waitinglist;
import de.hska.warehousemanagement.business.WarehouseService;
import de.hska.warehousemanagement.domain.WarehouseArticle;
import de.hska.workplacemanagement.business.WorkplaceService;
import de.hska.workplacemanagement.domain.ProductionOrder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;

@Service
public class ProductionService {

    @Autowired
    private PlanningService planningService;

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private WorkplaceService workplaceService;

    @Autowired
    private PartsService partsService;

    private int period;
    private Waitinglist waitinglist;
    private ArrayList<ProductionOrder> ordersinwork;
    private ArrayList<ProductionOrder> safetystockvalues;
    private ArrayList<ProductionOrder> stockvalues;
    private ArrayList<ProductionOrder> planning;
    private ArrayList<ProductionOrder> production;

    public void initialize(JsonFile jsonFile) throws ParseException, JSONException {
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

    private void ConstructContainers() {
        this.waitinglist = new Waitinglist();
        this.ordersinwork = new ArrayList<>();
        this.safetystockvalues = new ArrayList<>();
        this.stockvalues = new ArrayList<>();
        this.planning = new ArrayList<>();
        this.production = new ArrayList<>();

    }

    private void updateWaitinglist(JSONObject resultsJSON) {
        ArrayList<ProductionOrder> orders = new ArrayList<>();

        JSONObject jsonWaitinglistworkstationObject = resultsJSON.getJSONObject("waitinglistworkstations");
        JSONArray workplacesJSONArray = jsonWaitinglistworkstationObject.getJSONArray("workplace");

        for (int i = 0; i < workplacesJSONArray.length(); i++) {
            JSONObject objectInArray = workplacesJSONArray.getJSONObject(i);
            int workplace = Integer.parseInt(objectInArray.get("id").toString());

            if (objectInArray.has("waitinglist")) {
                Object item = objectInArray.get("waitinglist");
                if (item instanceof JSONArray) {
                    JSONArray waitinglistJSONArray = (JSONArray) item;
                    for (int a = 0; a < waitinglistJSONArray.length(); a++) {
                        JSONObject objectInWaitinglistArray = waitinglistJSONArray.getJSONObject(a);

                        int period = Integer.parseInt(objectInWaitinglistArray.get("period").toString());
                        int amount = Integer.parseInt(objectInWaitinglistArray.get("amount").toString());
                        int productNumber = Integer.parseInt(objectInWaitinglistArray.get("item").toString());
                        int order = Integer.parseInt(objectInWaitinglistArray.get("order").toString());
                        int timeNeed = Integer.parseInt(objectInWaitinglistArray.get("timeneed").toString());

                        orders.add(
                                new ProductionOrder(productNumber, amount, period, false, workplace, order, timeNeed));
                    }
                } else {
                    JSONObject waitinglistJSONObject = (JSONObject) item;

                    int period = Integer.parseInt(waitinglistJSONObject.get("period").toString());
                    int amount = Integer.parseInt(waitinglistJSONObject.get("amount").toString());
                    int productNumber = Integer.parseInt(waitinglistJSONObject.get("item").toString());
                    int order = Integer.parseInt(waitinglistJSONObject.get("order").toString());
                    int timeNeed = Integer.parseInt(waitinglistJSONObject.get("timeneed").toString());

                    orders.add(new ProductionOrder(productNumber, amount, period, false, workplace, order, timeNeed));
                }
            }
        }

        Object stockObject = resultsJSON.get("waitingliststock");
        JSONObject waitinglistStockObject = null;

        if (stockObject instanceof JSONObject) {
            waitinglistStockObject = (JSONObject) stockObject;
        }

        if (waitinglistStockObject != null) {
            Object missingPartJSONArray = waitinglistStockObject.get("missingpart");
            if (missingPartJSONArray instanceof JSONArray) {
                JSONArray missingPartArray = (JSONArray) missingPartJSONArray;

                for (int i = 0; i < missingPartArray.length(); i++) {
                    JSONObject objectInArray = missingPartArray.getJSONObject(i);
                    if (objectInArray.has("waitinglist")) {
                        Object item = objectInArray.get("waitinglist");
                        if (item instanceof JSONArray) {
                            JSONArray waitinglistStockJSONArray = (JSONArray) item;
                            for (int a = 0; a < waitinglistStockJSONArray.length(); a++) {
                                JSONObject objectInWaitinglistArray = waitinglistStockJSONArray.getJSONObject(a);

                                int period = Integer.parseInt(objectInWaitinglistArray.get("period").toString());
                                int amount = Integer.parseInt(objectInWaitinglistArray.get("amount").toString());
                                int productNumber = Integer.parseInt(objectInWaitinglistArray.get("item").toString());
                                int order = Integer.parseInt(objectInWaitinglistArray.get("order").toString());

                                orders.add(
                                        new ProductionOrder(productNumber, amount, period, false, null, order, 0));
                            }
                        } else {
                            JSONObject waitinglistJSONObject = (JSONObject) item;

                            int period = Integer.parseInt(waitinglistJSONObject.get("period").toString());
                            int amount = Integer.parseInt(waitinglistJSONObject.get("amount").toString());
                            int productNumber = Integer.parseInt(waitinglistJSONObject.get("item").toString());
                            int order = Integer.parseInt(waitinglistJSONObject.get("order").toString());

                            orders.add(new ProductionOrder(productNumber, amount, period, false, null, order, 0));
                        }
                    }
                }
            } else {
                JSONObject missingPartJSONObject = (JSONObject) missingPartJSONArray;
                if (missingPartJSONObject.has("waitinglist")) {
                    Object item = missingPartJSONObject.get("waitinglist");
                    if (item instanceof JSONArray) {
                        JSONArray waitinglistStockJSONArray = (JSONArray) item;
                        for (int a = 0; a < waitinglistStockJSONArray.length(); a++) {
                            JSONObject objectInWaitinglistArray = waitinglistStockJSONArray.getJSONObject(a);

                            int period = Integer.parseInt(objectInWaitinglistArray.get("period").toString());
                            int amount = Integer.parseInt(objectInWaitinglistArray.get("amount").toString());
                            int productNumber = Integer.parseInt(objectInWaitinglistArray.get("item").toString());
                            int order = Integer.parseInt(objectInWaitinglistArray.get("order").toString());

                            orders.add(
                                    new ProductionOrder(productNumber, amount, period, false, null, order, 0));
                        }
                    } else {
                        JSONObject waitinglistJSONObject = (JSONObject) item;

                        int period = Integer.parseInt(waitinglistJSONObject.get("period").toString());
                        int amount = Integer.parseInt(waitinglistJSONObject.get("amount").toString());
                        int productNumber = Integer.parseInt(waitinglistJSONObject.get("item").toString());
                        int order = Integer.parseInt(waitinglistJSONObject.get("order").toString());

                        orders.add(new ProductionOrder(productNumber, amount, period, false, null, order, 0));
                    }
                }
            }
        }

        this.waitinglist.setOrders(orders);
    }

    private void updateOrdersInWork(JSONObject jsonResultsObject) {
        Object jsonOrdersInWork = jsonResultsObject.get("ordersinwork");

        JSONObject jsonOrdersInWorkObject = null;

        if (jsonOrdersInWork instanceof JSONObject) {
            jsonOrdersInWorkObject = (JSONObject) jsonOrdersInWork;
        }

        if (jsonOrdersInWorkObject == null) {
            return;
        }

        JSONArray workplacesJSONArray = jsonOrdersInWorkObject.getJSONArray("workplace");

        for (int i = 0; i < workplacesJSONArray.length(); i++) {
            JSONObject jsonWorkplaceObject = workplacesJSONArray.getJSONObject(i);

            int period = Integer.parseInt(jsonWorkplaceObject.get("period").toString());
            int amount = Integer.parseInt(jsonWorkplaceObject.get("amount").toString());
            int productNumber = Integer.parseInt(jsonWorkplaceObject.get("item").toString());
            int order = Integer.parseInt(jsonWorkplaceObject.get("order").toString());
            int timeNeed = Integer.parseInt(jsonWorkplaceObject.get("timeneed").toString());
            int workplace = Integer.parseInt(jsonWorkplaceObject.get("id").toString());

            this.ordersinwork.add(new ProductionOrder(productNumber, amount, period, true, workplace, order, timeNeed));
        }
    }

    private void updateSafetyStockvalue() {
        for (PlanningPosition position : this.planningService.getSafetystockItems()) {
            this.safetystockvalues.add(
                    new ProductionOrder(position.getArticle(), position.getQuantity(), this.planningService.getPeriod(),
                            false, this.workplaceService.getWorkplaceIdByPart(position.getArticle())));
        }
    }

    private void updateStockvalue() {
        for (WarehouseArticle article : this.warehouseService.getWarehouseArticles()) {
            this.stockvalues.add(
                    new ProductionOrder(article.getPartNumber(), article.getAmount(), this.warehouseService.getPeriod(),
                            false, this.workplaceService.getWorkplaceIdByPart(article.getPartNumber())));
        }
    }

    private void updatePlanning() {
        for (PlanningPosition position : this.planningService.getProductionItems()) {
            this.planning.add(
                    new ProductionOrder(position.getArticle(), position.getQuantity(), this.planningService.getPeriod(),
                            false, this.workplaceService.getWorkplaceIdByPart(position.getArticle())));
        }
    }

    public ArrayList<ProductionOrder> getProductionOrdersInWaitinglist() {
        return this.waitinglist.getOrders();
    }

    public ArrayList<ProductionOrder> getProductionOrdersInWork() {
        return this.ordersinwork;
    }

    public ArrayList<ProductionOrder> getOrdersInWorkForProduct(int productNumber) {
        ArrayList<ProductionOrder> productionOrdersInWork = new ArrayList<>();
        for (ProductionOrder order : this.ordersinwork) {
            if (order.getProductNumber() == productNumber) {
                productionOrdersInWork.add(order);
            }
        }
        return productionOrdersInWork;
    }

    public ArrayList<ProductionOrder> getOrdersWaitinglistForProduct(int productNumber) {
        ArrayList<ProductionOrder> productionOrdersInWaitinglist = new ArrayList<>();
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
            disposition.setProductionOrdersInWaitingQueue(new ArrayList<>());
            for (ProductionOrder order : this.getProductionOrdersInWaitinglist()) {
                if (disposition.getPartNumber() == order.getProductNumber()) {
                    disposition.getProductionOrderInWaitingQueue().add(order);
                }
            }
        }
    }

    public ArrayList<ProductionOrder> getProduction() {
        return this.production;
    }

    public int getPeriod() {
        return period;
    }
}
