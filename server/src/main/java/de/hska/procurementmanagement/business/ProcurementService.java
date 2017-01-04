package de.hska.procurementmanagement.business;

import de.hska.filemanagement.domain.JsonFile;
import de.hska.partsmanagement.business.PartsService;
import de.hska.partsmanagement.domain.BuyPart;
import de.hska.procurementmanagement.domain.BuyMode;
import de.hska.procurementmanagement.domain.BuyOrder;
import de.hska.procurementmanagement.domain.IncomingBuyOrder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProcurementService {

    @Autowired
    private PartsService partsService;

    // Orders to make in this period
    private ArrayList<BuyOrder> newBuyOrders;

    // Orders that are coming in the future (pending)
    private ArrayList<BuyOrder> pendingBuyOrders;

    // Orders that arrive today
    private ArrayList<IncomingBuyOrder> incomingBuyOrders;

    private JSONObject resultsJson;

    public void initialize(JsonFile jsonFile)
            throws ParseException {
        this.newBuyOrders = new ArrayList<>();
        this.pendingBuyOrders = new ArrayList<>();
        this.incomingBuyOrders = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(jsonFile.getContent());
        this.resultsJson = jsonObject.getJSONObject("results");
        setPendingBuyOrders();
        setIncomingBuyOrders();
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
    }

    private void setPendingBuyOrders()
            throws ParseException {
        JSONObject inwardstockmovementJson = resultsJson.getJSONObject("futureinwardstockmovement");
        JSONArray orderArray = inwardstockmovementJson.getJSONArray("order");

        if (orderArray.length() == 0) {
            return;
        }

        for (int i = 0; i < orderArray.length(); i++) {
            JSONObject objectInArray = orderArray.getJSONObject(i);
            Integer article = Integer.parseInt(objectInArray.get("article").toString());
            BuyMode mode = convertIntegerToBuyMode(Integer.parseInt(objectInArray.get("mode").toString()));
            Integer amount = Integer.parseInt(objectInArray.get("amount").toString());
            Integer period = Integer.parseInt(objectInArray.get("orderperiod").toString());

            BuyPart buyPart = this.partsService.getBuyPartById(article);
            Double totalCosts = this.getBuyCosts(buyPart, mode, amount);
            Double procurementCosts = mode == BuyMode.Fast ? buyPart.getProcurementCosts() * 10 : buyPart.getProcurementCosts();
            Double materialCosts = totalCosts - procurementCosts;
            Double costsPerPiece = (Math.round(totalCosts / amount * 100)) / 100.0;
            this.pendingBuyOrders.add(new BuyOrder(article, mode, amount, period, totalCosts, materialCosts,
                    procurementCosts, costsPerPiece));
        }
    }

    public void generateNewBuyOrder(BuyPart part, BuyMode buyMode, Integer amount, Integer period) {
        Double costs = this.getBuyCosts(part, buyMode, amount);

        BuyOrder newOrder = new BuyOrder(part.getNumber(), buyMode, amount, period, costs);
        newBuyOrders.add(newOrder);
    }

    public ArrayList<IncomingBuyOrder> getIncomingBuyOrdersForPart(Integer partNumber) {
        return this.incomingBuyOrders
                .stream()
                .filter(incomingBuyOrder
                        -> Objects.equals(incomingBuyOrder.getBuyPartId(), partNumber))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<BuyOrder> getPendingBuyOrdersForPart(Integer partNumber) {
        return this.pendingBuyOrders
                .stream()
                .filter(pendingBuyOrder
                        -> Objects.equals(pendingBuyOrder.getBuyPartId(), partNumber))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private Double getBuyCosts(BuyPart part, BuyMode buyMode, Integer amount) {
        Double materialCosts = 0.0;
        Double procurementCosts = 0.0;

        if (amount >= part.getDiscountAmount() && buyMode != BuyMode.Fast) {
            materialCosts += 0.9 * amount * part.getPrice();
        } else {
            materialCosts += amount * part.getPrice();
        }

        if (buyMode == BuyMode.Fast) {
            procurementCosts += 10 * part.getProcurementCosts();
        } else {
            procurementCosts += part.getProcurementCosts();
        }

        return materialCosts + procurementCosts;
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
        if (totalDay % 5 == 0) {
            return 5;
        } else {
            return totalDay % 5;
        }
    }

    private Integer getArrivePeriodFromTimeStamp(Integer timestamp) {
        Integer totalDay = timestamp / 24 / 60;
        Double periodRounded = Math.ceil(totalDay / 5.0);
        return periodRounded.intValue();
    }
}