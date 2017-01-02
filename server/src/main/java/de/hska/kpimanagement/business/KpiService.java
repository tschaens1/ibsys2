package de.hska.kpimanagement.business;

import de.hska.filemanagement.domain.JsonFile;
import de.hska.kpimanagement.domain.DirectSale;
import de.hska.kpimanagement.domain.KpiContainer;
import de.hska.kpimanagement.domain.KpiPercentContainer;
import de.hska.kpimanagement.domain.NormalSale;
import de.hska.periodmanagement.domain.Period;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Objects;

@Service
public class KpiService {

    private Period period;

    private KpiContainer capacity;
    private KpiContainer possibleCapacity;
    private KpiContainer productiveTime;
    private KpiPercentContainer efficiency;
    private KpiContainer sellwish;
    private KpiContainer salesquantity;
    private KpiContainer idleTime;
    private KpiContainer idleTimeCosts;
    private KpiContainer storeValue;
    private KpiContainer storageCosts;
    private KpiContainer defectiveAmount;
    private KpiContainer defectiveCosts;
    private KpiContainer marketplaceResult;
    private KpiContainer summary;

    private KpiPercentContainer relativePossibleNormalCapacity;
    private KpiPercentContainer deliveryReliability;

    private NormalSale normalSale;
    private DirectSale directSale;

    public void initialize(JsonFile jsonFile)
            throws ParseException {

        ConstructContainers();

        JSONObject json = new JSONObject(jsonFile.getContent());
        JSONObject result = json.getJSONObject("results").getJSONObject("result");

        this.capacity.setCurrent(convertStringToDouble(result.getJSONObject("general").getJSONObject("capacity").get("current").toString()));
        this.capacity.setAverage(convertStringToDouble(result.getJSONObject("general").getJSONObject("capacity").get("average").toString()));
        String all = result.getJSONObject("general").getJSONObject("capacity").get("all").toString();
        if (!Objects.equals(all, "-")) this.capacity.setSum(convertStringToDouble(all));

        this.possibleCapacity.setCurrent(convertStringToDouble(result.getJSONObject("general").getJSONObject("possiblecapacity").get("current").toString()));
        this.possibleCapacity.setAverage(convertStringToDouble(result.getJSONObject("general").getJSONObject("possiblecapacity").get("average").toString()));
        all = result.getJSONObject("general").getJSONObject("possiblecapacity").get("all").toString();
        if (!Objects.equals(all, "-")) this.possibleCapacity.setSum(convertStringToDouble(all));

        this.relativePossibleNormalCapacity.setCurrent(convertPercentStringToDouble(result.getJSONObject("general").getJSONObject("relpossiblenormalcapacity").get("current").toString()));
        this.relativePossibleNormalCapacity.setAverage(convertPercentStringToDouble(result.getJSONObject("general").getJSONObject("relpossiblenormalcapacity").get("average").toString()));

        this.productiveTime.setCurrent(convertStringToDouble(result.getJSONObject("general").getJSONObject("productivetime").get("current").toString()));
        this.productiveTime.setAverage(convertStringToDouble(result.getJSONObject("general").getJSONObject("productivetime").get("average").toString()));
        all = result.getJSONObject("general").getJSONObject("productivetime").get("all").toString();
        if (!Objects.equals(all, "-")) this.productiveTime.setSum(convertStringToDouble(all));

        this.efficiency.setCurrent(convertStringToDouble(result.getJSONObject("general").getJSONObject("effiency").get("current").toString()));
        this.efficiency.setAverage(convertStringToDouble(result.getJSONObject("general").getJSONObject("effiency").get("average").toString()));

        this.sellwish.setCurrent(convertStringToDouble(result.getJSONObject("general").getJSONObject("sellwish").get("current").toString()));
        this.sellwish.setAverage(convertStringToDouble(result.getJSONObject("general").getJSONObject("sellwish").get("average").toString()));
        all = result.getJSONObject("general").getJSONObject("sellwish").get("all").toString();
        if (!Objects.equals(all, "-")) this.sellwish.setSum(convertStringToDouble(all));

        this.salesquantity.setCurrent(convertStringToDouble(result.getJSONObject("general").getJSONObject("salesquantity").get("current").toString()));
        this.salesquantity.setAverage(convertStringToDouble(result.getJSONObject("general").getJSONObject("salesquantity").get("average").toString()));
        all = result.getJSONObject("general").getJSONObject("salesquantity").get("all").toString();
        if (!Objects.equals(all, "-")) this.salesquantity.setSum(convertStringToDouble(all));

        this.deliveryReliability.setCurrent(convertPercentStringToDouble(result.getJSONObject("general").getJSONObject("deliveryreliability").get("current").toString()));
        this.deliveryReliability.setAverage(convertPercentStringToDouble(result.getJSONObject("general").getJSONObject("deliveryreliability").get("average").toString()));

        this.idleTime.setCurrent(convertStringToDouble(result.getJSONObject("general").getJSONObject("idletime").get("current").toString()));
        this.idleTime.setAverage(convertStringToDouble(result.getJSONObject("general").getJSONObject("idletime").get("average").toString()));
        all = result.getJSONObject("general").getJSONObject("idletime").get("all").toString();
        if (!Objects.equals(all, "-")) this.idleTime.setSum(convertStringToDouble(all));

        this.idleTimeCosts.setCurrent(convertStringToDouble(result.getJSONObject("general").getJSONObject("idletimecosts").get("current").toString()));
        this.idleTimeCosts.setAverage(convertStringToDouble(result.getJSONObject("general").getJSONObject("idletimecosts").get("average").toString()));
        all = result.getJSONObject("general").getJSONObject("idletimecosts").get("all").toString();
        if (!Objects.equals(all, "-")) this.idleTimeCosts.setSum(convertStringToDouble(all));

        this.storeValue.setCurrent(convertStringToDouble(result.getJSONObject("general").getJSONObject("storevalue").get("current").toString()));
        this.storeValue.setAverage(convertStringToDouble(result.getJSONObject("general").getJSONObject("storevalue").get("average").toString()));
        all = result.getJSONObject("general").getJSONObject("storevalue").get("all").toString();
        if (!Objects.equals(all, "-")) this.storeValue.setSum(convertStringToDouble(all));

        this.storageCosts.setCurrent(convertStringToDouble(result.getJSONObject("general").getJSONObject("storagecosts").get("current").toString()));
        this.storageCosts.setAverage(convertStringToDouble(result.getJSONObject("general").getJSONObject("storagecosts").get("average").toString()));
        all = result.getJSONObject("general").getJSONObject("storagecosts").get("all").toString();
        if (!Objects.equals(all, "-")) this.storageCosts.setSum(convertStringToDouble(all));

        this.defectiveAmount.setCurrent(convertStringToDouble(result.getJSONObject("defectivegoods").getJSONObject("quantity").get("current").toString()));
        this.defectiveAmount.setAverage(convertStringToDouble(result.getJSONObject("defectivegoods").getJSONObject("quantity").get("average").toString()));
        all = result.getJSONObject("defectivegoods").getJSONObject("quantity").get("all").toString();
        if (!Objects.equals(all, "-")) this.defectiveAmount.setSum(convertStringToDouble(all));

        this.defectiveCosts.setCurrent(convertStringToDouble(result.getJSONObject("defectivegoods").getJSONObject("costs").get("current").toString()));
        this.defectiveCosts.setAverage(convertStringToDouble(result.getJSONObject("defectivegoods").getJSONObject("costs").get("average").toString()));
        all = result.getJSONObject("defectivegoods").getJSONObject("costs").get("all").toString();
        if (!Objects.equals(all, "-")) this.defectiveCosts.setSum(convertStringToDouble(all));

        this.setNormalSale(result.getJSONObject("normalsale"));
        this.setDirectSale(result.getJSONObject("directsale"));

        this.marketplaceResult.setCurrent(convertStringToDouble(result.getJSONObject("marketplacesale").getJSONObject("profit").get("current").toString()));
        this.marketplaceResult.setAverage(convertStringToDouble(result.getJSONObject("marketplacesale").getJSONObject("profit").get("average").toString()));
        all = result.getJSONObject("marketplacesale").getJSONObject("profit").get("all").toString();
        if (!Objects.equals(all, "-")) this.marketplaceResult.setSum(convertStringToDouble(all));

        this.summary.setCurrent(convertStringToDouble(result.getJSONObject("summary").getJSONObject("profit").get("current").toString()));
        this.summary.setAverage(convertStringToDouble(result.getJSONObject("summary").getJSONObject("profit").get("average").toString()));
        all = result.getJSONObject("summary").getJSONObject("profit").get("all").toString();
        if (!Objects.equals(all, "-")) this.summary.setSum(convertStringToDouble(all));

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

    private void setNormalSale(JSONObject normalSale)
            throws ParseException {
        this.normalSale = new NormalSale();

        this.normalSale.getSalesPrice().setCurrent(convertStringToDouble(normalSale.getJSONObject("salesprice").get("current").toString()));
        this.normalSale.getSalesPrice().setAverage(convertStringToDouble(normalSale.getJSONObject("salesprice").get("average").toString()));

        this.normalSale.getProfit().setCurrent(convertStringToDouble(normalSale.getJSONObject("profit").get("current").toString()));
        this.normalSale.getProfit().setAverage(convertStringToDouble(normalSale.getJSONObject("profit").get("average").toString()));
        String all = normalSale.getJSONObject("profit").get("all").toString();
        if (!Objects.equals(all, "-")) this.normalSale.getProfit().setSum(convertStringToDouble(all));

        this.normalSale.getProfitPerUnit().setCurrent(convertStringToDouble(normalSale.getJSONObject("profitperunit").get("current").toString()));
        this.normalSale.getProfitPerUnit().setAverage(convertStringToDouble(normalSale.getJSONObject("profitperunit").get("average").toString()));

        this.normalSale.getProductionCost().setCurrent(this.normalSale.getProductionCost().getCurrent() - this.normalSale.getProfitPerUnit().getCurrent());
        this.normalSale.getProductionCost().setAverage(this.normalSale.getProductionCost().getAverage() - this.normalSale.getProfitPerUnit().getAverage());
    }

    private void setDirectSale(JSONObject directSale)
            throws ParseException {
        this.directSale = new DirectSale();

        this.directSale.getProfit().setCurrent(convertStringToDouble(directSale.getJSONObject("profit").get("current").toString()));
        this.directSale.getProfit().setAverage(convertStringToDouble(directSale.getJSONObject("profit").get("average").toString()));
        String all = directSale.getJSONObject("profit").get("all").toString();
        if (!Objects.equals(all, "-")) this.directSale.getProfit().setSum(convertStringToDouble(all));

        this.directSale.getContractPenalty().setCurrent(convertStringToDouble(directSale.getJSONObject("contractpenalty").get("current").toString()));
        this.directSale.getContractPenalty().setAverage(convertStringToDouble(directSale.getJSONObject("contractpenalty").get("average").toString()));
        all = directSale.getJSONObject("contractpenalty").get("all").toString();
        if (!Objects.equals(all, "-")) this.directSale.getContractPenalty().setSum(convertStringToDouble(all));
    }

    public double convertStringToDouble(String value)
            throws ParseException {
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
        Number number = format.parse(value);
        return number.doubleValue();
    }

    public double convertPercentStringToDouble(String value)
            throws ParseException {
        String withoutPercent = value.replace("%", "");
        return convertStringToDouble(withoutPercent);
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public KpiContainer getCapacity() {
        return capacity;
    }

    public void setCapacity(KpiContainer capacity) {
        this.capacity = capacity;
    }

    public KpiContainer getPossibleCapacity() {
        return possibleCapacity;
    }

    public void setPossibleCapacity(KpiContainer possibleCapacity) {
        this.possibleCapacity = possibleCapacity;
    }

    public KpiPercentContainer getRelativePossibleNormalCapacity() {
        return relativePossibleNormalCapacity;
    }

    public void setRelativePossibleNormalCapacity(KpiPercentContainer relativePossibleNormalCapacity) {
        this.relativePossibleNormalCapacity = relativePossibleNormalCapacity;
    }

    public KpiContainer getProductiveTime() {
        return productiveTime;
    }

    public void setProductiveTime(KpiContainer productiveTime) {
        this.productiveTime = productiveTime;
    }

    public KpiPercentContainer getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(KpiPercentContainer efficiency) {
        this.efficiency = efficiency;
    }

    public KpiContainer getSellwish() {
        return sellwish;
    }

    public void setSellwish(KpiContainer sellwish) {
        this.sellwish = sellwish;
    }

    public KpiContainer getSalesquantity() {
        return salesquantity;
    }

    public void setSalesquantity(KpiContainer salesquantity) {
        this.salesquantity = salesquantity;
    }

    public KpiPercentContainer getDeliveryReliability() {
        return deliveryReliability;
    }

    public void setDeliveryReliability(KpiPercentContainer deliveryReliability) {
        this.deliveryReliability = deliveryReliability;
    }

    public KpiContainer getIdleTime() {
        return idleTime;
    }

    public void setIdleTime(KpiContainer idleTime) {
        this.idleTime = idleTime;
    }

    public KpiContainer getIdleTimeCosts() {
        return idleTimeCosts;
    }

    public void setIdleTimeCosts(KpiContainer idleTimeCosts) {
        this.idleTimeCosts = idleTimeCosts;
    }

    public KpiContainer getStoreValue() {
        return storeValue;
    }

    public void setStoreValue(KpiContainer storeValue) {
        this.storeValue = storeValue;
    }

    public KpiContainer getStorageCosts() {
        return storageCosts;
    }

    public void setStorageCosts(KpiContainer storageCosts) {
        this.storageCosts = storageCosts;
    }

    public KpiContainer getDefectiveAmount() {
        return defectiveAmount;
    }

    public void setDefectiveAmount(KpiContainer defectiveAmount) {
        this.defectiveAmount = defectiveAmount;
    }

    public KpiContainer getDefectiveCosts() {
        return defectiveCosts;
    }

    public void setDefectiveCosts(KpiContainer defectiveCosts) {
        this.defectiveCosts = defectiveCosts;
    }

    public KpiContainer getMarketplaceResult() {
        return marketplaceResult;
    }

    public void setMarketplaceResult(KpiContainer marketplaceResult) {
        this.marketplaceResult = marketplaceResult;
    }

    public KpiContainer getSummary() {
        return summary;
    }

    public void setSummary(KpiContainer summary) {
        this.summary = summary;
    }

    public NormalSale getNormalSale() {
        return normalSale;
    }

    public void setNormalSale(NormalSale normalSale) {
        this.normalSale = normalSale;
    }

    public DirectSale getDirectSale() {
        return directSale;
    }

    public void setDirectSale(DirectSale directSale) {
        this.directSale = directSale;
    }
}
