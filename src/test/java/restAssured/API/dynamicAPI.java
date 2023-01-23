package restAssured.API;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.payload;
import files.resusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class dynamicAPI {

	
	@Test(dataProvider = "dynamicPar")
	public void DynamicJSON(String name, String address) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String respo = given().log().all().queryParam("key", "qaclick123").body(payload.dynamicpay(name,address))
		.when().post("/maps/api/place/add/json").then().log().all().assertThat().statusCode(200).extract().asString();
		
		JsonPath js = resusableMethods.rawToJSON(respo);
		System.out.println(js.get("place_id"));
		System.out.println("-----------------------------------------------------------------");
		
	}
	@Test
	public void payloadFromLinks() throws IOException {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String respo = given().log().all().queryParam("key", "qaclick123").body(new String(Files.readAllBytes(Paths.get("C:\\Users\\mathu\\Downloads\\postAPI.json"))))
				.when().post("/maps/api/place/add/json").then().log().all().assertThat().statusCode(200).extract().asString();
				
				JsonPath js = resusableMethods.rawToJSON(respo);
				System.out.println(js.get("place_id"));
				System.out.println("-----------------------------------------------------------------");
				
	}
	
	
	@DataProvider(name = "dynamicPar")
	public Object[][] param(){
		return new Object[][] {{"mathu","4 ave E"},{"mathi", "6 ave sds"},{"gur", "67 abdn"}};
	}
	
	
}
