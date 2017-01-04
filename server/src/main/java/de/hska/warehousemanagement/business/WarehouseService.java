package de.hska.warehousemanagement.business;

import java.text.ParseException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import de.hska.filemanagement.domain.JsonFile;
import de.hska.warehousemanagement.domain.WarehouseArticle;

@Service
public class WarehouseService {

	private int period;
	private ArrayList<WarehouseArticle> warehouseArticles;

	public void initialize(JsonFile jsonFile) throws ParseException {

		ConstructContainers();

		JSONObject jsonResultsObject = new JSONObject(jsonFile.getContent());
		JSONObject resultsJSON = jsonResultsObject.getJSONObject("results");
		this.period = resultsJSON.getInt("period");

		JSONObject jsonWatinglistworkstationObject = resultsJSON.getJSONObject("warehousestock");
		JSONArray warehousestockArticleJSONArray = jsonWatinglistworkstationObject.getJSONArray("article");
		this.setWarehouseArticles(warehousestockArticleJSONArray);
	}

	private void ConstructContainers() {
		this.warehouseArticles = new ArrayList<WarehouseArticle>();
	}

	private void setWarehouseArticles(JSONArray warehousestockArticleJSONArray) {
		for (int i = 0; i < warehousestockArticleJSONArray.length(); i++) {
			JSONObject objectInArray = warehousestockArticleJSONArray.getJSONObject(i);

			int partNumber = Integer.parseInt(objectInArray.get("id").toString());
			int amount = Integer.parseInt(objectInArray.get("amount").toString());
			double percentage = objectInArray.getDouble("pct");
			double price = objectInArray.getDouble("price");
			double stockvalue = objectInArray.getDouble("stockvalue");
			this.warehouseArticles.add(new WarehouseArticle(partNumber, amount, percentage, price, stockvalue));
		}
	}

	public ArrayList<WarehouseArticle> getWarehouseArticles() {
		return this.warehouseArticles;
	}

	public WarehouseArticle getWarehouseArticle(int partNumber) {
		for (WarehouseArticle article : this.warehouseArticles) {
			if (article.getPartNumber() == partNumber)
				return article;
		}
		return null;
	}

	public int getPeriod() {
		return this.period;
	}
}
