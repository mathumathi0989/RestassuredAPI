package jiraAPI;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
public class attachment {
	
//if we use https, then use "relaxedHTTPSValidation()" method after given 
	
	
	@Test
	public void attachmentd() {
	RestAssured.baseURI =  "http://localhost:8081";
	
	
	String response = given().log().all().header("X-Atlassian-Token", "no-check").filter(cookieAuth.session)
			.header("Content-Type", "multipart/form-data; boundary=<calculated when request is sent>")
			.multiPart("file", new File("C:\\Users\\mathu\\Postman\\files\\apidummy.txt"))
	.when().post("/rest/api/2/issue/"+createIssue.issue_id+ "/attachments")
	.then().log().all().assertThat().statusCode(200).extract().toString();
	
	System.out.println(response);
	
	System.out.println("-------------------------------------------------------------");
	
	
	}
	
	
	
}
