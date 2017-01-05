package de.hska.inputmanagement.business;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hska.dispositionmanagement.business.DispositionService;
import de.hska.dispositionmanagement.domain.Disposition;
import de.hska.periodmanagement.domain.Period;

@Service
public class InputService {

	@Autowired
	private DispositionService dispositionService;

	public String generateInputJson(Period period) {

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

		JSONArray productionlistItems = new JSONArray();

		for (Disposition disposition : dispositionService.getDisposition()) {
			JSONObject productionJSON = new JSONObject();
			productionJSON.put("article", disposition.getPartNumber());
			productionJSON.put("quantity", disposition.getProduction().getAmount());
			productionlistItems.put(productionJSON);
		}

		JSONObject workingtime1JSON = new JSONObject();
		workingtime1JSON.put("station", 1);
		workingtime1JSON.put("shift", 1);
		workingtime1JSON.put("overtime", 15);

		JSONObject workingtime2JSON = new JSONObject();
		workingtime2JSON.put("station", 2);
		workingtime2JSON.put("shift", 2);
		workingtime2JSON.put("overtime", 0);

		JSONObject workingtime3JSON = new JSONObject();
		workingtime3JSON.put("station", 3);
		workingtime3JSON.put("shift", 3);
		workingtime3JSON.put("overtime", 70);

		JSONObject workingtime4JSON = new JSONObject();
		workingtime4JSON.put("station", 4);
		workingtime4JSON.put("shift", 1);
		workingtime4JSON.put("overtime", 15);

		JSONObject workingtime5JSON = new JSONObject();
		workingtime5JSON.put("station", 5);
		workingtime5JSON.put("shift", 2);
		workingtime5JSON.put("overtime", 0);

		JSONObject workingtime6JSON = new JSONObject();
		workingtime6JSON.put("station", 6);
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

		return inputWrapper.toString();
	}

	public JSONObject generateSellwishJson(List<Object> sellwishes) {
		// TODO: Fetch correct values from calculation and return json block
		return new JSONObject();
	}
}
