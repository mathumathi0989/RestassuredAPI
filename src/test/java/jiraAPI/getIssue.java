package jiraAPI;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


public class getIssue {

	public static String actualSummary;
	@Test
	public void getIssued() {
		
		RestAssured.baseURI = "http://localhost:8081/";
		
		String response = given().log().all().filter(cookieAuth.session).queryParam("fields", "summary")
		.when().get("rest/api/2/issue/"+createIssue.issue_id)
		.then().log().all().assertThat().statusCode(200).extract().asString();
		
		
		System.out.println(response);
		
		JsonPath js = new JsonPath(response);
		actualSummary = js.get("fields.summary");
		System.out.println(actualSummary);
		System.out.println("-------------------------------------------------------------------------");
		
		Assert.assertEquals(editIssue.expectedSummary, actualSummary, "Summary details are matching");
		
		System.out.println("-------------------------------------------------------------------------");
		
		
		
		
		
		
		
	}
	
	
	
	
	
}
