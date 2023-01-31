package API_E2E;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class login {
	
	public static String token;
	public static String userID;
	
	@Test
	public void loginTC() {
	
	String loginRes =  given().spec(specBuilder.requestspec()).headers("Content-Type","application/json").log().all()
	 .body(payloads.loginPL())
	 .when().post("/api/ecom/auth/login")
	 .then().log().all().extract().response().asString();
	
	JsonPath js = new JsonPath(loginRes);
	token = js.get("token");
	System.out.println("Authorization token is " +token);
	
	userID = js.get("userId");
	System.out.println("User ID is "+userID);
	 
	String ActualMessage = js.get("message");
	String ExpectedMessage = "Login Successfully";
	Assert.assertEquals(ActualMessage, ExpectedMessage, "Logged in");
	
	}
	
	
	
	
	
	
	
	
}
