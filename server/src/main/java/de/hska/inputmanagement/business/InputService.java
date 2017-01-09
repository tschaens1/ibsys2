package de.hska.inputmanagement.business;

import java.util.List;

import de.hska.procurementmanagement.business.ProcurementService;
import de.hska.procurementmanagement.domain.BuyOrder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hska.dispositionmanagement.business.DispositionService;
import de.hska.dispositionmanagement.domain.Disposition;
import de.hska.filemanagement.domain.JsonFile;
import de.hska.periodmanagement.domain.Period;
import de.hska.planningmangement.business.PlanningService;
import de.hska.planningmangement.domain.PlanningPosition;

@Service
public class InputService {

	@Autowired
	private DispositionService dispositionService;

	@Autowired
	private PlanningService planningService;

	@Autowired
	private ProcurementService procurementService;

	public JsonFile generateInputJson(Period period) {

		JSONObject inputJson = new JSONObject();

		final JSONObject qualitycontrolJson = new JSONObject();
		qualitycontrolJson.put("type", "no");
		qualitycontrolJson.put("losequantity", 0);
		qualitycontrolJson.put("delay", 0);

		JSONArray sellwishItemList = new JSONArray();
		for (PlanningPosition planningPosition : this.planningService.getSellwishItems()) {
			JSONObject sellwishItemJson = new JSONObject();
			sellwishItemJson.put("article", planningPosition.getArticle());
			sellwishItemJson.put("quantity", planningPosition.getQuantity());
			sellwishItemList.put(sellwishItemJson);
		}

		JSONArray selldirectItemList = new JSONArray();
		for (PlanningPosition planningPosition : this.planningService.getSelldirectItems()) {
			JSONObject selldirectItemJson = new JSONObject();
			selldirectItemJson.put("article", planningPosition.getArticle());
			selldirectItemJson.put("quantity", planningPosition.getQuantity());
			if(planningPosition.getQuantity() > 0)
				selldirectItemList.put(selldirectItemJson);
		}

		JSONArray productionlistItems = new JSONArray();
		for (Disposition disposition : dispositionService.getDisposition()) {
			JSONObject productionJSON = new JSONObject();
			productionJSON.put("article", disposition.getPartNumber());
			productionJSON.put("quantity", disposition.getProduction().getAmount());
			productionlistItems.put(productionJSON);
		}

		JSONArray orderlistItems = new JSONArray();
		for (BuyOrder buyOrder : procurementService.getNewBuyOrders()) {
			JSONObject buyOrderJSON = new JSONObject();
			buyOrderJSON.put("article", buyOrder.getBuyPartId());
			buyOrderJSON.put("quantity", buyOrder.getAmount());
			buyOrderJSON.put("modus", buyOrder.getBuyMode().getValue());
			orderlistItems.put(buyOrderJSON);
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
		inputJson.put("orderlist", orderlistItems);
		inputJson.put("productionlist", productionlistItems);
		inputJson.put("workingtimelist", workingtimelistItems);

		JSONObject inputWrapper = new JSONObject();
		inputWrapper.put("input", inputJson);

		JsonFile input = new JsonFile();
		input.setContent(inputWrapper.toString());
		return input;
	}

	public JSONObject generateSellwishJson(List<Object> sellwishes) {
		// TODO: Fetch correct values from calculation and return json block
		return new JSONObject();
	}
}
