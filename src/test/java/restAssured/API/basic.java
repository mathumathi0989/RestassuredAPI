package restAssured.API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.payload;
import files.resusableMethods;

public class basic {
	public static String placeID;
	public static JsonPath jpath;
	
	public static void main(String[] args) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		addpla();
		updatepla();
		getpla();
	}
	
	
		public static String addpla() {
		//addPlace
		String addresponse = given().log().all().queryParam("key", "qaclick123").body(payload.addplace())
		.when().post("/maps/api/place/add/json")
		.then().log().all()
		.assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server","Apache/2.4.41 (Ubuntu)").extract().response().asString();
	
		jpath = resusableMethods.rawToJSON(addresponse);
		 placeID = jpath.getString("place_id");
		
		System.out.println("Place ID for addPlace is "+placeID);
		System.out.println("--------------------------------------------");
		return placeID;
		
	}

		public static void updatepla() {
			//updatePlace
			String updaRespo = given().log().all().queryParam("key", "qaclick123").body(payload.updateplace())
			.when().put("/maps/api/place/update/json").then().log().all().extract().response().asString();
			jpath = resusableMethods.rawToJSON(updaRespo);
			String updateMess = jpath.getString("msg");
			
			if(updateMess.contentEquals("Address successfully updated")) {
				System.out.println("Address updated !!!!!!");
				System.out.println("--------------------------------------------");
			}
			else {
				System.out.println("Address not yet updated");
				System.out.println("--------------------------------------------");
			}
		}
		public static void getpla() {
			//getplace
			
			String getRespo = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeID)
			.when().get("/maps/api/place/get/json").then().log().all().extract().response().asString();
			
			jpath = resusableMethods.rawToJSON(getRespo);
			String getMess = jpath.getString("address");
			
			if(getMess.contentEquals(payload.updatedaddress)) {
				System.out.println("Verified - Address updated !!!!!!");
				System.out.println("--------------------------------------------");
			}
			else {
				System.out.println("Address not yet updated and verified");
				System.out.println("--------------------------------------------");
				
			
			}
			Assert.assertEquals(getMess, payload.updatedaddress, "Address verified by TESTNG");
			
		}
	
}
