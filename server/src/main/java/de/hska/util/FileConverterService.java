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
			return XML.toJSONObject(replacedContent);
		} catch (JSONException ex) {
			return null;
		}
	}
}
