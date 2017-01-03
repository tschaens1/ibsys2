package de.hska.procurementmanagement.business;

import de.hska.filemanagement.domain.JsonFile;
import de.hska.procurementmanagement.domain.BuyMode;
import de.hska.procurementmanagement.domain.BuyOrder;
import de.hska.procurementmanagement.domain.IncomingBuyOrder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

@Service
public class ProcurementService {

    // Orders to make in this period
    private ArrayList<BuyOrder> newBuyOrders;

    // Orders that are coming in the future (pending)
    private ArrayList<BuyOrder> pendingBuyOrders = new ArrayList<>();

    // Orders that arrive today
    private ArrayList<IncomingBuyOrder> incomingBuyOrders = new ArrayList<>();

    private JSONObject resultsJson;
    private Integer currentPeriod;

    public void initialize(JsonFile jsonFile)
            throws ParseException {
        this.newBuyOrders = new ArrayList<>();
        this.pendingBuyOrders = new ArrayList<>();
        this.incomingBuyOrders = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(jsonFile.getContent());
        this.resultsJson = jsonObject.getJSONObject("results");
        this.currentPeriod = resultsJson.getInt("period");

        setPendingBuyOrders();
        setIncomingBuyOrders();
        setNewBuyOrders();
    }

    private void setIncomingBuyOrders()
            throws ParseException {
        JSONObject inwardstockmovementJson = resultsJson.getJSONObject("inwardstockmovement");
        JSONArray orderArray = inwardstockmovementJson.getJSONArray("order");

        if (orderArray.length() == 0) {
            return;
        }

        for (int i = 0; i < orderArray.length(); i++) {
            JSONObject objectInArray = orderArray.getJSONObject(i);
            Integer number = Integer.parseInt(objectInArray.get("id").toString());
            Integer article = Integer.parseInt(objectInArray.get("article").toString());
            BuyMode mode = convertIntegerToBuyMode(Integer.parseInt(objectInArray.get("mode").toString()));
            Integer amount = Integer.parseInt(objectInArray.get("amount").toString());
            Integer period = Integer.parseInt(objectInArray.get("orderperiod").toString());
            Double materialcosts = convertStringToDouble(objectInArray.get("materialcosts").toString());
            Double ordercosts = convertStringToDouble(objectInArray.get("ordercosts").toString());
            Double entirecosts = convertStringToDouble(objectInArray.get("entirecosts").toString());
            Double piececosts = convertStringToDouble(objectInArray.get("piececosts").toString());

            Integer time = Integer.parseInt(objectInArray.get("time").toString());
            Integer arriveDay = getArriveDayFromTimeStamp(time);
            Integer arrivePeriod = getArrivePeriodFromTimeStamp(time);

            this.incomingBuyOrders.add(new IncomingBuyOrder(number, article, mode, amount, period, materialcosts,
                    ordercosts, entirecosts, piececosts, arrivePeriod, arriveDay));
        }

        System.out.println(Arrays.deepToString(incomingBuyOrders.toArray()));

    }

    private void setPendingBuyOrders() {
    }

    private void setNewBuyOrders() {
        // TODO: Do calculation
    }

    private Double convertStringToDouble(String value)
            throws ParseException {
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
        Number number = format.parse(value);
        return number.doubleValue();
    }

    private BuyMode convertIntegerToBuyMode(Integer buyMode) {
        if (buyMode == BuyMode.Normal.getValue()) {
            return BuyMode.Normal;
        } else {
            return BuyMode.Fast;
        }
    }

    private Integer getArriveDayFromTimeStamp(Integer timestamp) {
        Integer totalDay = timestamp / 24 / 60;
        return totalDay % 5;
    }

    private Integer getArrivePeriodFromTimeStamp(Integer timestamp) {
        // TODO: Check period when day 0
        Integer totalDay = timestamp / 24 / 60;
        Double period = Math.ceil(totalDay / 5.0);
        System.out.println(totalDay/5.0 + " became " + period + " and as int " + period.intValue());
        return period.intValue();
    }
}
