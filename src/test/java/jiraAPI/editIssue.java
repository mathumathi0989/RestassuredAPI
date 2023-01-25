package jiraAPI;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class editIssue {

	@Test
	public void editIssued() {
		
		RestAssured.baseURI =  "http://localhost:8081/";
		
		
		String response = given().log().all()
		.body("{\"update\":{},\"fields\":{\"summary\":\"Updated summary throug rest assured\"}}")
		.filter(cookieAuth.session).when().put("/rest/api/2/issue/"+createIssue.issue_id)
		.then().log().all().assertThat().statusCode(204).extract().toString();
		
		System.out.println(response);
		
		
	}
	
}
