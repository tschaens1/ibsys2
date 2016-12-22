package de.hska.chartmanagement.business;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class ChartService {

    public ChartService() {
    }

    public void initialize(JSONObject jsonObject) {
        System.out.println("JSON Object: " + jsonObject);
        JSONObject result = jsonObject.getJSONObject("result");
        System.out.println("Result: " + result.toString());
    }
}
