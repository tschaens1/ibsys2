package de.hska.kpimanagement.business;

import de.hska.filemanagement.domain.JsonFile;
import de.hska.kpimanagement.domain.DirectSale;
import de.hska.kpimanagement.domain.KpiContainer;
import de.hska.kpimanagement.domain.KpiPercentContainer;
import de.hska.kpimanagement.domain.NormalSale;
import de.hska.periodmanagement.domain.Period;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class KpiService {

    private Period period;

    private KpiContainer capacity;
    private KpiContainer possibleCapacity;
    private KpiPercentContainer relativePossibleNormalCapacity;
    private KpiContainer productiveTime;
    private KpiPercentContainer efficiency;
    private KpiContainer sellwish;
    private KpiContainer salesquantity;
    private KpiPercentContainer deliveryReliability;
    private KpiContainer idleTime;
    private KpiContainer idleTimeCosts;
    private KpiContainer storeValue;
    private KpiContainer storageCosts;
    private KpiContainer defectiveAmount;
    private KpiContainer defectiveCosts;
    private KpiContainer marketplaceResult;
    private KpiContainer summary;

    private NormalSale normalSale;
    private DirectSale directSale;

    public void initialize(JsonFile jsonFile){

        ConstructContainers();

        JSONObject json = new JSONObject(jsonFile.getContent());
        JSONObject result = json.getJSONObject("results").getJSONObject("result");

        this.capacity.setCurrent(result.getJSONObject("general").getJSONObject("capacity").getDouble("current"));
        this.capacity.setAverage(result.getJSONObject("general").getJSONObject("capacity").getDouble("average"));
        String all = result.getJSONObject("general").getJSONObject("capacity").get("all").toString();
        if (all != "-") this.capacity.setSum(Double.valueOf(all));

        this.possibleCapacity.setCurrent(result.getJSONObject("general").getJSONObject("possiblecapacity").getDouble("current"));
        this.possibleCapacity.setAverage(result.getJSONObject("general").getJSONObject("possiblecapacity").getDouble("average"));
        all = result.getJSONObject("general").getJSONObject("possiblecapacity").get("all").toString();
        if (all != "-") this.possibleCapacity.setSum(Double.valueOf(all));

        this.relativePossibleNormalCapacity.setCurrent(result.getJSONObject("general").getJSONObject("relativepossiblenormalcapacity").getString("current"));
        this.relativePossibleNormalCapacity.setAverage(result.getJSONObject("general").getJSONObject("relativepossiblenormalcapacity").getString("average"));

        this.productiveTime.setCurrent(result.getJSONObject("general").getJSONObject("productivetime").getDouble("current"));
        this.productiveTime.setAverage(result.getJSONObject("general").getJSONObject("productivetime").getDouble("average"));
        all = result.getJSONObject("general").getJSONObject("productivetime").get("all").toString();
        if (all != "-") this.productiveTime.setSum(Double.valueOf(all));

        this.efficiency.setCurrent(result.getJSONObject("general").getJSONObject("efficiency").getString("current"));
        this.efficiency.setAverage(result.getJSONObject("general").getJSONObject("efficiency").getString("average"));

        this.sellwish.setCurrent(result.getJSONObject("general").getJSONObject("sellwish").getDouble("current"));
        this.sellwish.setAverage(result.getJSONObject("general").getJSONObject("sellwish").getDouble("average"));
        all = result.getJSONObject("general").getJSONObject("sellwish").get("all").toString();
        if (all != "-") this.sellwish.setSum(Double.valueOf(all));

        this.salesquantity.setCurrent(result.getJSONObject("general").getJSONObject("salesquantity").getDouble("current"));
        this.salesquantity.setAverage(result.getJSONObject("general").getJSONObject("salesquantity").getDouble("average"));
        all = result.getJSONObject("general").getJSONObject("salesquantity").get("all").toString();
        if (all != "-") this.salesquantity.setSum(Double.valueOf(all));

        this.deliveryReliability.setCurrent(result.getJSONObject("general").getJSONObject("deliveryReliability").getString("current"));
        this.deliveryReliability.setAverage(result.getJSONObject("general").getJSONObject("deliveryReliability").getString("average"));

        this.idleTime.setCurrent(result.getJSONObject("general").getJSONObject("idletime").getDouble("current"));
        this.idleTime.setAverage(result.getJSONObject("general").getJSONObject("idletime").getDouble("average"));
        all = result.getJSONObject("general").getJSONObject("idleTime").get("all").toString();
        if (all != "-") this.idleTime.setSum(Double.valueOf(all));

        this.idleTimeCosts.setCurrent(result.getJSONObject("general").getJSONObject("idletimecosts").getDouble("current"));
        this.idleTimeCosts.setAverage(result.getJSONObject("general").getJSONObject("idletime").getDouble("average"));
        all = result.getJSONObject("general").getJSONObject("idleTime").get("all").toString();
        if (all != "-") this.idleTimeCosts.setSum(Double.valueOf(all));

        this.storeValue.setCurrent(result.getJSONObject("general").getJSONObject("storevalue").getDouble("current"));
        this.storeValue.setAverage(result.getJSONObject("general").getJSONObject("storevalue").getDouble("average"));
        all = result.getJSONObject("general").getJSONObject("storevalue").get("all").toString();
        if (all != "-") this.storeValue.setSum(Double.valueOf(all));

        this.storageCosts.setCurrent(result.getJSONObject("general").getJSONObject("storagecosts").getDouble("current"));
        this.storageCosts.setAverage(result.getJSONObject("general").getJSONObject("storagecosts").getDouble("average"));
        all = result.getJSONObject("general").getJSONObject("storagecosts").get("all").toString();
        if (all != "-") this.storageCosts.setSum(Double.valueOf(all));

        this.defectiveAmount.setCurrent(result.getJSONObject("general").getJSONObject("defectivegoods").getJSONObject("quantity").getDouble("current"));
        this.defectiveAmount.setAverage(result.getJSONObject("general").getJSONObject("defectivegoods").getJSONObject("quantity").getDouble("average"));
        all = result.getJSONObject("general").getJSONObject("defectivegoods").getJSONObject("quantity").get("all").toString();
        if (all != "-") this.defectiveAmount.setSum(Double.valueOf(all));

        this.defectiveCosts.setCurrent(result.getJSONObject("general").getJSONObject("defectivegoods").getJSONObject("costs").getDouble("current"));
        this.defectiveCosts.setAverage(result.getJSONObject("general").getJSONObject("defectivegoods").getJSONObject("costs").getDouble("average"));
        all = result.getJSONObject("general").getJSONObject("defectivegoods").getJSONObject("costs").get("all").toString();
        if (all != "-") this.defectiveCosts.setSum(Double.valueOf(all));

        this.setNormalSale(result.getJSONObject("normalsale"));
        this.setDirectSale(result.getJSONObject("directsale"));

        this.marketplaceResult.setCurrent(result.getJSONObject("general").getJSONObject("maketplacesale").getJSONObject("profit").getDouble("current"));
        this.marketplaceResult.setAverage(result.getJSONObject("general").getJSONObject("maketplacesale").getJSONObject("profit").getDouble("average"));
        all = result.getJSONObject("general").getJSONObject("maketplacesale").getJSONObject("profit").get("all").toString();
        if (all != "-") this.marketplaceResult.setSum(Double.valueOf(all));

        this.summary.setCurrent(result.getJSONObject("general").getJSONObject("summary").getJSONObject("profit").getDouble("current"));
        this.summary.setAverage(result.getJSONObject("general").getJSONObject("summary").getJSONObject("profit").getDouble("average"));
        all = result.getJSONObject("general").getJSONObject("summary").getJSONObject("profit").get("all").toString();
        if (all != "-") this.summary.setSum(Double.valueOf(all));

    }

    private void ConstructContainers() {
        capacity = new KpiContainer();
        possibleCapacity = new KpiContainer();
        relativePossibleNormalCapacity = new KpiPercentContainer();
        productiveTime = new KpiContainer();
        efficiency = new KpiPercentContainer();
        sellwish = new KpiContainer();
        salesquantity = new KpiContainer();
        deliveryReliability = new KpiPercentContainer();
        idleTime = new KpiContainer();
        idleTimeCosts = new KpiContainer();
        storeValue = new KpiContainer();
        storageCosts = new KpiContainer();
        defectiveAmount = new KpiContainer();
        defectiveCosts = new KpiContainer();
        marketplaceResult = new KpiContainer();
        summary = new KpiContainer();
    }

    private void setNormalSale(JSONObject normalSale){
        this.normalSale = new NormalSale();

        this.normalSale.getSalesPrice().setCurrent(normalSale.getJSONObject("salesprice").getDouble("current"));
        this.normalSale.getSalesPrice().setAverage(normalSale.getJSONObject("salesprice").getDouble("average"));

        this.normalSale.getProfit().setCurrent(normalSale.getJSONObject("profit").getDouble("current"));
        this.normalSale.getProfit().setAverage(normalSale.getJSONObject("profit").getDouble("average"));
        String all = normalSale.getJSONObject("profit").get("all").toString();
        if (all != "-") this.normalSale.getProfit().setSum(Double.valueOf(all));

        this.normalSale.getProfitPerUnit().setCurrent(normalSale.getJSONObject("profitperunit").getDouble("current"));
        this.normalSale.getProfitPerUnit().setAverage(normalSale.getJSONObject("profitperunit").getDouble("average"));

        this.normalSale.getProductionCost().setCurrent(this.normalSale.getProductionCost().getCurrent() - this.normalSale.getProfitPerUnit().getCurrent());
        this.normalSale.getProductionCost().setAverage(this.normalSale.getProductionCost().getAverage() - this.normalSale.getProfitPerUnit().getAverage());
    }

    private void setDirectSale(JSONObject directSale){
        this.directSale = new DirectSale();

        this.directSale.getProfit().setCurrent(directSale.getJSONObject("profit").getDouble("current"));
        this.directSale.getProfit().setAverage(directSale.getJSONObject("profit").getDouble("average"));
        String all = directSale.getJSONObject("profit").get("all").toString();
        if (all != "-") this.directSale.getProfit().setSum(Double.valueOf(all));

        this.directSale.getContractPenalty().setCurrent(directSale.getJSONObject("contractpenalty").getDouble("current"));
        this.directSale.getContractPenalty().setAverage(directSale.getJSONObject("contractpenalty").getDouble("average"));
        all = directSale.getJSONObject("contractpenalty").get("all").toString();
        if (all != "-") this.directSale.getContractPenalty().setSum(Double.valueOf(all));
    }
}
