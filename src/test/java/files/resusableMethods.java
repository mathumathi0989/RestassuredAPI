package files;

import io.restassured.path.json.JsonPath;

public class resusableMethods {

	public static JsonPath rawToJSON(String response) {
		
		JsonPath jpath = new JsonPath(response);
		return jpath;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
