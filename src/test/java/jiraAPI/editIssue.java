package jiraAPI;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class editIssue {
	public static String expectedSummary = "Mathu How are you ";
	@Test
	public void editIssued() {
		
		RestAssured.baseURI =  "http://localhost:8081";
		
		
		
		String response = given().log().all().header("content-type", "application/json")
		.body("{\"update\":{},\"fields\":{\"summary\":\"" +expectedSummary+ "\"}}")
		.filter(cookieAuth.session).when().put("/rest/api/2/issue/"+createIssue.issue_id)
		.then().log().all().assertThat().statusCode(204).extract().toString();
		
		System.out.println(response);
		
		
		System.out.println("-------------------------------------------------------------");
	}
	
}
