package de.hska.inputmanagement.business;

import de.hska.capacitymanagement.business.CapacityService;
import de.hska.capacitymanagement.domain.Capacity;
import de.hska.dispositionmanagement.business.DispositionService;
import de.hska.dispositionmanagement.domain.Disposition;
import de.hska.filemanagement.domain.JsonFile;
import de.hska.planningmangement.business.PlanningService;
import de.hska.planningmangement.domain.PlanningPosition;
import de.hska.procurementmanagement.business.ProcurementService;
import de.hska.procurementmanagement.domain.BuyOrder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public JsonFile generateInputJson() {

		JSONObject inputJson = new JSONObject();

		final JSONObject qualitycontrolItem = new JSONObject();
		qualitycontrolItem.put("type", "no");
		qualitycontrolItem.put("losequantity", 0);
		qualitycontrolItem.put("delay", 0);

		JSONArray sellwishItemList = new JSONArray();
		for (PlanningPosition planningPosition : this.planningService.getSellwishItems()) {
			JSONObject sellwishItem = new JSONObject();
			sellwishItem.put("article", planningPosition.getArticle());
			sellwishItem.put("quantity", planningPosition.getQuantity());
			sellwishItemList.put(sellwishItem);
		}

		JSONArray selldirectItemList = new JSONArray();
		for (PlanningPosition planningPosition : this.planningService.getSelldirectItems()) {
			JSONObject selldirectItem = new JSONObject();
			selldirectItem.put("article", planningPosition.getArticle());
			selldirectItem.put("quantity", planningPosition.getQuantity());
			if (planningPosition.getQuantity() > 0)
				selldirectItemList.put(selldirectItem);
		}

		JSONArray productionItemList = new JSONArray();
		for (Disposition disposition : dispositionService.getDispositions()) {
			JSONObject productionItem = new JSONObject();
			productionItem.put("article", disposition.getPartNumber());
			productionItem.put("quantity", disposition.getFinalNewProduction().getAmount());
			productionItemList.put(productionItem);
		}

		JSONArray orderItemList = new JSONArray();
		for (BuyOrder buyOrder : procurementService.getNewBuyOrders()) {
			JSONObject orderItem = new JSONObject();
			orderItem.put("article", buyOrder.getBuyPartId());
			orderItem.put("quantity", buyOrder.getAmount());
			orderItem.put("modus", buyOrder.getBuyMode().getValue());
			orderItemList.put(orderItem);
		}

		JSONArray capacityItemList = new JSONArray();
		for (Capacity capacity : capacityService.getCapacities()) {
			JSONObject capacityItem = new JSONObject();
			capacityItem.put("station", capacity.getStation());
			capacityItem.put("shift", capacity.getShift());
			capacityItem.put("overtime", capacity.getOvertime());
			capacityItemList.put(capacityItem);
		}

		inputJson.put("qualitycontrol", qualitycontrolItem);
		inputJson.put("sellwish", sellwishItemList);
		inputJson.put("selldirect", selldirectItemList);
		inputJson.put("orderlist", orderItemList);
		inputJson.put("productionlist", productionItemList);
		inputJson.put("workingtimelist", capacityItemList);

		JSONObject inputItem = new JSONObject();
		inputItem.put("input", inputJson);

		JsonFile inputFile = new JsonFile();
		inputFile.setContent(inputItem.toString());
		return inputFile;
	}
}
