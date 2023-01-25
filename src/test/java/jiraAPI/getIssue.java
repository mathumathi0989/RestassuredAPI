package jiraAPI;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;


public class getIssue {

	@Test
	public void getIssued() {
		
		RestAssured.baseURI = "http://localhost:8081/";
		
		String response = given().log().all().filter(cookieAuth.session)
		.when().get("/rest/api/2/issue/"+createIssue.issueCreation())
		.then().log().all().assertThat().statusCode(200).extract().asString();
		
		
		System.out.println(response);
		System.out.println("-------------------------------------------------------------------------");
		
		
		
		
		
		
		
	}
	
	
	
	
	
}
