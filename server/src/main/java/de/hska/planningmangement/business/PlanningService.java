package de.hska.planningmangement.business;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import de.hska.filemanagement.domain.JsonFile;
import de.hska.planningmangement.domain.PlanningPosition;

@Service
public class PlanningService {

	private int period;
	private List<PlanningPosition> sellwishItems = new ArrayList<>();
	private List<PlanningPosition> productionItems = new ArrayList<>();
	private List<PlanningPosition> selldirectItems = new ArrayList<>();
	private List<PlanningPosition> safetystockItems = new ArrayList<>();
	private List<PlanningPosition> forecastOne = new ArrayList<>();
	private List<PlanningPosition> forecastTwo = new ArrayList<>();
	private List<PlanningPosition> forecastThree = new ArrayList<>();

	public void initialize(JsonFile jsonFile) throws ParseException {

		ConstructContainers();

		JSONObject jsonObject = new JSONObject(jsonFile.getContent());

		JSONObject resultsJSON = jsonObject.getJSONObject("results");
		this.period = resultsJSON.getInt("period");

		JSONObject inputJSON = jsonObject.getJSONObject("input");

		this.setSellwishItems(inputJSON.getJSONObject("sellwish"));
		this.setProductionItems(inputJSON.getJSONObject("production"));
		this.setDirectSaleItems(inputJSON.getJSONObject("selldirect"));
		this.setSafetyStockItems(inputJSON.getJSONObject("safetystock"));
		this.setForecasts(inputJSON.getJSONObject("forecasts"));
	}

	private void ConstructContainers() {
		this.sellwishItems = new ArrayList<>();
		this.productionItems = new ArrayList<>();
		this.selldirectItems = new ArrayList<>();
		this.safetystockItems = new ArrayList<>();
		this.forecastOne = new ArrayList<>();
		this.forecastTwo = new ArrayList<>();
		this.forecastThree = new ArrayList<>();
	}

	private void setSellwishItems(JSONObject jsonObject) {
		JSONArray sellwishItemsJSONArray = jsonObject.getJSONArray("items");
		PlanningPosition sellwishItem;
		for (int i = 0, size = sellwishItemsJSONArray.length(); i < size; i++) {
			JSONObject objectInArray = sellwishItemsJSONArray.getJSONObject(i);
			int article = Integer.parseInt(objectInArray.get("article").toString());
			int quantity = Integer.parseInt(objectInArray.get("quantity").toString());
			sellwishItem = new PlanningPosition(article, quantity, 0, 0);
			this.sellwishItems.add(sellwishItem);
		}
	}

	private void setProductionItems(JSONObject jsonObject) {
		JSONArray productionItemsJSONArray = jsonObject.getJSONArray("items");
		PlanningPosition productionItem;
		for (int i = 0, size = productionItemsJSONArray.length(); i < size; i++) {
			JSONObject objectInArray = productionItemsJSONArray.getJSONObject(i);
			int article = Integer.parseInt(objectInArray.get("article").toString());
			int quantity = Integer.parseInt(objectInArray.get("quantity").toString());
			productionItem = new PlanningPosition(article, quantity, 0, 0);
			this.productionItems.add(productionItem);
		}
	}

	private void setDirectSaleItems(JSONObject jsonObject) {
		JSONArray selldirectItemsJSONArray = jsonObject.getJSONArray("items");
		PlanningPosition selldirectItem;
		for (int i = 0, size = selldirectItemsJSONArray.length(); i < size; i++) {
			JSONObject objectInArray = selldirectItemsJSONArray.getJSONObject(i);
			int article = Integer.parseInt(objectInArray.get("article").toString());
			int quantity = Integer.parseInt(objectInArray.get("quantity").toString());
			int price = Integer.parseInt(objectInArray.get("price").toString());
			int penalty = Integer.parseInt(objectInArray.get("penalty").toString());
			selldirectItem = new PlanningPosition(article, quantity, price, penalty);
			this.selldirectItems.add(selldirectItem);
		}
	}

	private void setSafetyStockItems(JSONObject jsonObject) {
		JSONArray safetystockItemsJSONArray = jsonObject.getJSONArray("items");
		PlanningPosition safetyStockItem;
		for (int i = 0, size = safetystockItemsJSONArray.length(); i < size; i++) {
			JSONObject objectInArray = safetystockItemsJSONArray.getJSONObject(i);
			int article = Integer.parseInt(objectInArray.get("article").toString());
			int quantity = Integer.parseInt(objectInArray.get("quantity").toString());
			safetyStockItem = new PlanningPosition(article, quantity, 0, 0);
			this.safetystockItems.add(safetyStockItem);
		}
	}

	private void setForecasts(JSONObject jsonObject) {
		JSONObject forecastOneJSON = jsonObject.getJSONObject("forecast_one");
		JSONArray forecastOneItemsJSONArray = forecastOneJSON.getJSONArray("items");
		PlanningPosition forecast;

		for (int i = 0, size = forecastOneItemsJSONArray.length(); i < size; i++) {
			JSONObject objectInArray = forecastOneItemsJSONArray.getJSONObject(i);
			int article = Integer.parseInt(objectInArray.get("article").toString());
			int quantity = Integer.parseInt(objectInArray.get("quantity").toString());
			forecast = new PlanningPosition(article, quantity, 0, 0);
			this.forecastOne.add(forecast);
		}

		JSONObject forecastTwoJSON = jsonObject.getJSONObject("forecast_two");
		JSONArray forecastTwoItemsJSONArray = forecastTwoJSON.getJSONArray("items");
		for (int i = 0, size = forecastTwoItemsJSONArray.length(); i < size; i++) {
			JSONObject objectInArray = forecastTwoItemsJSONArray.getJSONObject(i);
			int article = Integer.parseInt(objectInArray.get("article").toString());
			int quantity = Integer.parseInt(objectInArray.get("quantity").toString());
			forecast = new PlanningPosition(article, quantity, 0, 0);
			this.forecastTwo.add(forecast);
		}

		JSONObject forecastThreeJSON = jsonObject.getJSONObject("forecast_three");
		JSONArray forecastThreeItemsJSONArray = forecastThreeJSON.getJSONArray("items");
		for (int i = 0, size = forecastThreeItemsJSONArray.length(); i < size; i++) {
			JSONObject objectInArray = forecastThreeItemsJSONArray.getJSONObject(i);
			int article = Integer.parseInt(objectInArray.get("article").toString());
			int quantity = Integer.parseInt(objectInArray.get("quantity").toString());
			forecast = new PlanningPosition(article, quantity, 0, 0);
			this.forecastThree.add(forecast);
		}
	}

	public int getPeriod() {
		return this.period;
	}

	public List<PlanningPosition> getSellwishItems() {
		return this.sellwishItems;
	}

	public PlanningPosition getSellwishItemForProduct(int partNumber) {
		for (PlanningPosition position : this.sellwishItems) {
			if (position.getArticle() == partNumber)
				return position;
		}
		return null;
	}

	public List<PlanningPosition> getProductionItems() {
		return this.productionItems;
	}

	public PlanningPosition getProductionItemForProduct(int partNumber) {
		for (PlanningPosition position : this.productionItems) {
			if (position.getArticle() == partNumber)
				return position;
		}
		return null;
	}

	public List<PlanningPosition> getSelldirectItems() {
		return this.selldirectItems;
	}

	public PlanningPosition getSelldirectItemForProduct(int partNumber) {
		for (PlanningPosition position : this.selldirectItems) {
			if (position.getArticle() == partNumber)
				return position;
		}
		return null;
	}

	public List<PlanningPosition> getSafetystockItems() {
		return this.safetystockItems;
	}

	public PlanningPosition getSafetystockItemForProduct(int partNumber) {
		for (PlanningPosition position : this.safetystockItems) {
			if (position.getArticle() == partNumber)
				return position;
		}
		return null;
	}

	public List<PlanningPosition> getForecastOne() {
		return this.forecastOne;
	}

	public List<PlanningPosition> getForecastTwo() {
		return this.forecastTwo;
	}

	public List<PlanningPosition> getForecastThree() {
		return this.forecastThree;
	}
}
