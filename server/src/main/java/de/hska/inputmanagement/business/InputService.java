package de.hska.inputmanagement.business;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hska.capacitymanagement.business.CapacityService;
import de.hska.capacitymanagement.domain.Capacity;
import de.hska.dispositionmanagement.business.DispositionService;
import de.hska.dispositionmanagement.domain.Disposition;
import de.hska.filemanagement.domain.JsonFile;
import de.hska.periodmanagement.domain.Period;
import de.hska.planningmangement.business.PlanningService;
import de.hska.planningmangement.domain.PlanningPosition;
import de.hska.procurementmanagement.business.ProcurementService;
import de.hska.procurementmanagement.domain.BuyOrder;

@Service
public class InputService {

	@Autowired
	private DispositionService dispositionService;

	@Autowired
	private PlanningService planningService;

	@Autowired
	private ProcurementService procurementService;

	@Autowired
	private CapacityService capacityService;

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
			if (planningPosition.getQuantity() > 0)
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

		JSONArray capacitiesItems = new JSONArray();
		for (Capacity capacity : capacityService.getCapacities()) {
			JSONObject capacityJSON = new JSONObject();
			capacityJSON.put("station", capacity.getStation());
			capacityJSON.put("shift", capacity.getShift());
			capacityJSON.put("overtime", capacity.getOvertime());
			capacitiesItems.put(capacityJSON);
		}

		inputJson.put("qualitycontrol", qualitycontrolJson);
		inputJson.put("sellwish", sellwishItemList);
		inputJson.put("selldirect", selldirectItemList);
		inputJson.put("orderlist", orderlistItems);
		inputJson.put("productionlist", productionlistItems);
		inputJson.put("workingtimelist", capacitiesItems);

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
