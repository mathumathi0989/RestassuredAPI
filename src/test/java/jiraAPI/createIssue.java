package jiraAPI;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
public class createIssue {

	public static String issue_id;
	
	@Test
	public static String issueCreation() {
		
		 RestAssured.baseURI = "http://localhost:8081";
		
		String response =  given().log().all().header("content-type", "application/json").queryParam("updateHistory", "false")
		 .body("{\r\n"
		 		+ "    \"fields\": {\r\n"
		 		+ "        \"project\": {\r\n"
		 		+ "            \"key\": \"RES\"\r\n"
		 		+ "        },\r\n"
		 		+ "        \"summary\": \" stAssured \",\r\n"
		 		+ "        \"description\": \"sample Rest assured issue\",\r\n"
		 		+ "        \"issuetype\": {\r\n"
		 		+ "            \"name\": \"Bug\"\r\n"
		 		+ "        }\r\n"
		 		+ "       \r\n"
		 		+ "    }\r\n"
		 		+ "}")
			.filter(cookieAuth.session)
			.when().post("/rest/api/2/issue")
			.then().log().all()
			.statusCode(201).extract().asString();
		
		JsonPath jpath = new JsonPath(response);
	issue_id = jpath.get("id");
		System.out.println(issue_id);
		System.out.println("-------------------------------------------------------------------------");

		return issue_id;
		
	}
	
	
	
	
}
