package de.hska.inputmanagement.business;

import java.util.List;

import de.hska.filemanagement.domain.JsonFile;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import de.hska.periodmanagement.domain.Period;

@Service
public class InputService {
	// TODO: Load Results.xml from database and throw if not exists
	// TODO: Do correct calculation

	public void initialize(JsonFile jsonFile) {
		generatePlanningJson(jsonFile);
	}

	private void generatePlanningJson(JsonFile jsonFile) {
		try {

			JSONObject jsonObject = new JSONObject(jsonFile.getContent());

			JSONObject resultsJSON = jsonObject.getJSONObject("results");
			int group = resultsJSON.getInt("group");
			int game = resultsJSON.getInt("game");
			int counter = resultsJSON.getInt("period");

			JSONObject inputJSON = jsonObject.getJSONObject("input");

			JSONObject sellwishJSON = inputJSON.getJSONObject("sellwish");

			JSONArray sellwishItemsJSONArray = sellwishJSON.getJSONArray("items");
			for (int i = 0, size = sellwishItemsJSONArray.length(); i < size; i++) {
				JSONObject objectInArray = sellwishItemsJSONArray.getJSONObject(i);
				objectInArray.get("article");
				objectInArray.get("quantity");
			}

			JSONObject productionJSON = inputJSON.getJSONObject("production");
			JSONArray productionItemsJSONArray = productionJSON.getJSONArray("items");
			for (int i = 0, size = productionItemsJSONArray.length(); i < size; i++) {
				JSONObject objectInArray = productionItemsJSONArray.getJSONObject(i);
				objectInArray.get("article");
				objectInArray.get("quantity");
			}

			JSONObject selldirectJSON = inputJSON.getJSONObject("selldirect");
			JSONArray selldirectItemsJSONArray = selldirectJSON.getJSONArray("items");
			for (int i = 0, size = selldirectItemsJSONArray.length(); i < size; i++) {
				JSONObject objectInArray = selldirectItemsJSONArray.getJSONObject(i);
				objectInArray.get("article");
				objectInArray.get("quantity");
			}

			JSONObject safetystockJSON = inputJSON.getJSONObject("safetystock");
			JSONArray safetystockItemsJSONArray = safetystockJSON.getJSONArray("items");
			for (int i = 0, size = safetystockItemsJSONArray.length(); i < size; i++) {
				JSONObject objectInArray = safetystockItemsJSONArray.getJSONObject(i);
				objectInArray.get("article");
				objectInArray.get("quantity");
			}

			JSONObject forecastsJSON = inputJSON.getJSONObject("forecasts");
			JSONObject forecastOneJSON = forecastsJSON.getJSONObject("forecast_one");
			JSONArray forecastOneItemsJSONArray = forecastOneJSON.getJSONArray("items");
			for (int i = 0, size = forecastOneItemsJSONArray.length(); i < size; i++) {
				JSONObject objectInArray = forecastOneItemsJSONArray.getJSONObject(i);
				objectInArray.get("article");
				objectInArray.get("quantity");
			}
			JSONObject forecastTwoJSON = forecastsJSON.getJSONObject("forecast_two");
			JSONArray forecastTwoItemsJSONArray = forecastTwoJSON.getJSONArray("items");
			for (int i = 0, size = forecastTwoItemsJSONArray.length(); i < size; i++) {
				JSONObject objectInArray = forecastTwoItemsJSONArray.getJSONObject(i);
				objectInArray.get("article");
				objectInArray.get("quantity");
			}

			JSONObject forecastThreeJSON = forecastsJSON.getJSONObject("forecast_three");
			JSONArray forecastThreeItemsJSONArray = forecastThreeJSON.getJSONArray("items");
			for (int i = 0, size = forecastThreeItemsJSONArray.length(); i < size; i++) {
				JSONObject objectInArray = forecastThreeItemsJSONArray.getJSONObject(i);
				objectInArray.get("article");
				objectInArray.get("quantity");
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public JSONObject generateInputJson(Period period) {

		JSONObject inputJson = new JSONObject();

		final JSONObject qualitycontrolJson = new JSONObject();
		qualitycontrolJson.put("type", "no");
		qualitycontrolJson.put("losequantity", 0);
		qualitycontrolJson.put("delay", 0);

		// TODO: Get correct values from calculation
		JSONObject sellwishJson = new JSONObject();

		JSONObject sellwishItem1Json = new JSONObject();
		sellwishItem1Json.put("article", 1);
		sellwishItem1Json.put("quantity", 200);

		JSONObject sellwishItem2Json = new JSONObject();
		sellwishItem2Json.put("article", 2);
		sellwishItem2Json.put("quantity", 100);

		JSONObject sellwishItem3Json = new JSONObject();
		sellwishItem3Json.put("article", 3);
		sellwishItem3Json.put("quantity", 50);

		JSONArray sellwishItemList = new JSONArray();
		sellwishItemList.put(sellwishItem1Json);
		sellwishItemList.put(sellwishItem2Json);
		sellwishItemList.put(sellwishItem3Json);

		// TODO: Get correct values from calculation
		JSONObject selldirectJson = new JSONObject();

		JSONObject sellItem1Json = new JSONObject();
		sellItem1Json.put("article", 1);
		sellItem1Json.put("quantity", 200);
		sellItem1Json.put("price", 120.3);
		sellItem1Json.put("penalty", 10.0);

		JSONObject sellItem2Json = new JSONObject();
		sellItem2Json.put("article", 2);
		sellItem2Json.put("quantity", 0);
		sellItem2Json.put("price", 0.0);
		sellItem2Json.put("penalty", 0.0);

		JSONObject sellItem3Json = new JSONObject();
		sellItem3Json.put("article", 2);
		sellItem3Json.put("quantity", 0);
		sellItem3Json.put("price", 0.0);
		sellItem3Json.put("penalty", 0.0);

		JSONArray selldirectItemList = new JSONArray();
		selldirectItemList.put(sellItem1Json);
		selldirectItemList.put(sellItem2Json);
		selldirectItemList.put(sellItem3Json);

		// TODO: Get correct values from calculation
		JSONObject orderlistJson = new JSONObject();

		JSONObject order1Json = new JSONObject();
		order1Json.put("article", 40);
		order1Json.put("quantity", 2200);
		order1Json.put("modus", 5);

		JSONObject order2Json = new JSONObject();
		order2Json.put("article", 15);
		order2Json.put("quantity", 1750);
		order2Json.put("modus", 5);

		JSONObject order3Json = new JSONObject();
		order3Json.put("article", 13);
		order3Json.put("quantity", 2000);
		order3Json.put("modus", 5);

		JSONArray orderItemList = new JSONArray();
		orderItemList.put(order1Json);
		orderItemList.put(order2Json);
		orderItemList.put(order3Json);

		// TODO: Get correct values from calculation
		JSONObject productionJson = new JSONObject();

		JSONObject production1JSON = new JSONObject();
		production1JSON.put("article", 16);
		production1JSON.put("quantity", 200);

		JSONObject production2JSON = new JSONObject();
		production2JSON.put("article", 26);
		production2JSON.put("quantity", 300);

		JSONObject production3JSON = new JSONObject();
		production3JSON.put("article", 27);
		production3JSON.put("quantity", 400);

		JSONObject production4JSON = new JSONObject();
		production4JSON.put("article", 28);
		production4JSON.put("quantity", 500);

		JSONObject production5JSON = new JSONObject();
		production5JSON.put("article", 29);
		production5JSON.put("quantity", 600);

		JSONObject production6JSON = new JSONObject();
		production6JSON.put("article", 30);
		production6JSON.put("quantity", 700);

		JSONArray productionlistItems = new JSONArray();
		productionlistItems.put(production1JSON);
		productionlistItems.put(production2JSON);
		productionlistItems.put(production3JSON);
		productionlistItems.put(production4JSON);
		productionlistItems.put(production5JSON);
		productionlistItems.put(production6JSON);

		// TODO: Get correct values from calculation
		JSONObject workingtimelistJson = new JSONObject();

		JSONObject workingtime1JSON = new JSONObject();
		workingtime1JSON.put("station", 1);
		workingtime1JSON.put("shift", 1);
		workingtime1JSON.put("overtime", 15);

		JSONObject workingtime2JSON = new JSONObject();
		workingtime2JSON.put("station", 1);
		workingtime2JSON.put("shift", 2);
		workingtime2JSON.put("overtime", 0);

		JSONObject workingtime3JSON = new JSONObject();
		workingtime3JSON.put("station", 1);
		workingtime3JSON.put("shift", 3);
		workingtime3JSON.put("overtime", 70);

		JSONObject workingtime4JSON = new JSONObject();
		workingtime4JSON.put("station", 1);
		workingtime4JSON.put("shift", 1);
		workingtime4JSON.put("overtime", 15);

		JSONObject workingtime5JSON = new JSONObject();
		workingtime5JSON.put("station", 1);
		workingtime5JSON.put("shift", 2);
		workingtime5JSON.put("overtime", 0);

		JSONObject workingtime6JSON = new JSONObject();
		workingtime6JSON.put("station", 1);
		workingtime6JSON.put("shift", 3);
		workingtime6JSON.put("overtime", 20);

		JSONArray workingtimelistItems = new JSONArray();
		workingtimelistItems.put(workingtime1JSON);
		workingtimelistItems.put(workingtime2JSON);
		workingtimelistItems.put(workingtime3JSON);
		workingtimelistItems.put(workingtime4JSON);
		workingtimelistItems.put(workingtime5JSON);
		workingtimelistItems.put(workingtime6JSON);

		inputJson.put("qualitycontrol", qualitycontrolJson);
		inputJson.put("sellwish", sellwishItemList);
		inputJson.put("selldirect", selldirectItemList);
		inputJson.put("orderlist", orderItemList);
		inputJson.put("productionlist", productionlistItems);
		inputJson.put("workingtimelist", workingtimelistItems);

		JSONObject inputWrapper = new JSONObject();
		inputWrapper.put("input", inputJson);

		return inputWrapper;
	}

	public JSONObject generateSellwishJson(List<Object> sellwishes) {
		// TODO: Fetch correct values from calculation and return json block
		return new JSONObject();
	}
}
