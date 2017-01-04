package de.hska.warehousemanagement.business;

import de.hska.filemanagement.domain.JsonFile;
import de.hska.warehousemanagement.domain.WarehouseArticle;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;

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

    private void setWarehouseArticles(JSONArray warehousestockArticleJSONArray)
            throws ParseException {
        for (int i = 0; i < warehousestockArticleJSONArray.length(); i++) {
            JSONObject objectInArray = warehousestockArticleJSONArray.getJSONObject(i);

            int partNumber = Integer.parseInt(objectInArray.get("id").toString());
            int amount = Integer.parseInt(objectInArray.get("amount").toString());
            double percentage = convertStringToDouble(objectInArray.get("pct").toString());
            double price = convertStringToDouble(objectInArray.get("price").toString());
            double stockvalue = convertStringToDouble(objectInArray.get("stockvalue").toString());
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

    private Double convertStringToDouble(String value)
            throws ParseException {
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
        Number number = format.parse(value);
        return number.doubleValue();
    }

    public int getPeriod() {
        return this.period;
    }
}
