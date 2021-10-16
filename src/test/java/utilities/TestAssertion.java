package utilities;

import org.json.JSONObject;

public class TestAssertion {
	
	public static boolean jsonHasKey(String json, String key)
	{
		JSONObject jsonobject = new JSONObject(json);
		return jsonobject.has(key);
	}
	
	public static String getJsonKeyValue(String json, String key)
	{
		JSONObject jsonobject = new JSONObject(json);
		return jsonobject.get(key).toString();
	}

}
