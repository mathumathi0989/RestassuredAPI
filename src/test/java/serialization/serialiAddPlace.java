package serialization;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
public class serialiAddPlace {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		addPlacePOJO ap = new addPlacePOJO();
		ap.setAccuracy(50);
		ap.setAddress("Avenue E, Bayonne");
		ap.setLanguage("English");
		ap.setName("Mathu");
		ap.setPhone_number("20123233422");
		ap.setWebsite("mathu.com");
		
		List<String> mylist = new ArrayList<String>();
		mylist.add("shoe park");
		mylist.add("shop");
		mylist.add("gurdev");
		ap.setTypes(mylist);
		
		locationPOJO loc = new locationPOJO();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		ap.setLocation(loc);
		
		
		String respo = given().log().all().queryParam("key", "qaclick123").headers("Content-Type", "application/json")
		.body(ap).when().post("/maps/api/place/add/json")
		.then().log().all().extract().asString();
		
		System.out.println("Response of the add place API are " + respo);
		
		JsonPath js = new JsonPath(respo);
		String placeID = js.get("place_id");
		System.out.println("Place ID of the map API is " +placeID);
		
	}

}
