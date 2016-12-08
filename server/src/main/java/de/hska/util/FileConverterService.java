package de.hska.util;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;


@Service
public class FileConverterService {

    private static final Integer PRETTY_INDENT_FACTOR = 4;

    public JSONObject convertXmlToJson(String content) {
        try {
            //final String escapedXml = StringEscapeUtils.escapeXml(content);
            JSONObject jsonObject = XML.toJSONObject(content);
            return jsonObject;
        } catch (JSONException ex) {
            System.out.println("Error while converting xml to JSON: " + ex.getStackTrace().toString());
            return null;
        }
    }
}
