package reqRespSpec;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;
import files.resusableMethods;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import restAssured.API.basic;

public class reqres {
	public static String placeID;
	public static JsonPath jpath;
	public static RequestSpecification req;
	public static ResponseSpecification res;
	public static String updaAddress = "54 Ave E, Bayonne, NJ";
	
	
	public static RequestSpecification reqspec() {
		req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123").build();
		return req;
	}

public static ResponseSpecification resspec() {
		res = new ResponseSpecBuilder().expectStatusCode(200).build();
		return res;
	}

	
		@Test(priority=1)
		public static void addplace() {
		//addPlace
		String addresponse = given().spec(reqspec()).body(files.payload.addplace())
		.when().post("/maps/api/place/add/json")
		.then().extract().response().asString();
	
		jpath = resusableMethods.rawToJSON(addresponse);
		 placeID = jpath.getString("place_id");
		
		System.out.println("Place ID for addPlace is "+placeID);
		System.out.println("--------------------------------------------");
		
		
	}
		
		@Test(priority=2)
		public static void updatepla() {
			//updatePlace
			String updaRespo = given().spec(reqspec()).body("{\r\n"
					+ "\"place_id\":\""+placeID +"\",\r\n"
					+ "\"address\":\""+updaAddress+"\",\r\n"
					+ "\"key\":\"qaclick123\"\r\n"
					+ "}")
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
		
		@Test(priority=3)
		public static void getpla() {
			//getplace
			
			String getRespo = given().spec(reqspec()).queryParam("place_id", placeID)
			.when().get("/maps/api/place/get/json").then().extract().response().asString();
			
			jpath = resusableMethods.rawToJSON(getRespo);
			String getMess = jpath.getString("address");
			
			if(getMess.contentEquals(updaAddress)) {
				System.out.println("Verified - Address updated !!!!!!");
				System.out.println("--------------------------------------------");
			}
			else {
				System.out.println("Address not yet updated and verified");
				System.out.println("--------------------------------------------");
				
			
			}
			Assert.assertEquals(getMess, updaAddress, "Address verified by TESTNG");
		
			
		}
	
}
