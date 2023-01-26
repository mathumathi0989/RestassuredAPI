package jiraAPI;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;


public class deleteIssue {

	
	@Test
	public void deleteIssued() {
		
		RestAssured.baseURI = "http://localhost:8081";
		
		String response = given().log().all().filter(cookieAuth.session)
		.when().delete("/rest/api/2/issue/"+createIssue.issue_id)
		.then().log().all().assertThat().statusCode(204).extract().asString();
		
		System.out.println(response);
		

		System.out.println("-------------------------------------------------------------");
		
	}
	
	
	
	
	
	
}
