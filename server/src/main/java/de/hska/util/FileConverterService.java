package de.hska.util;

import java.net.URLDecoder;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;

@Service
public class FileConverterService {

	@SuppressWarnings("deprecation")
	public JSONObject convertXmlToJson(String content) {
		try {
			final String escapedXml = URLDecoder.decode(content);
			String replacedContent = escapedXml.replace("%", "");
			JSONObject jsonObject = XML.toJSONObject(replacedContent);
			return jsonObject;
		} catch (JSONException ex) {
			System.out.println("Error while converting xml to JSON: " + ex.getMessage());
			return null;
		}
	}
}
