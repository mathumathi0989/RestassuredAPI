package jiraAPI;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

public class cookieAuth {

	public static SessionFilter session ;
	
	@Test
	public void session() {
		
		
		RestAssured.baseURI = "http://localhost:8081";
		
		 session = new SessionFilter();
		
		String response = given().log().all().header("content-type", "application/json").body("{ \"username\": \"mathumathi.b\", \"password\": \"Success@1\" }")
		.filter(session)
		.when().post("/rest/auth/1/session")
		.then().log().all()
		.statusCode(200).extract().asString();
		
		System.out.println(response);
		System.out.println("-------------------------------------------------------------------------");

		
	}
	
	
	
	
}
