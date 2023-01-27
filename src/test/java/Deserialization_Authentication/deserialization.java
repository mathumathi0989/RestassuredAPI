package Deserialization_Authentication;

import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.restassured.parsing.Parser;

public class deserialization {

	public static void main(String[] args) {
		
		//oAuth_AuthorizationCode.main(null);
		
	//System.out.println(oAuth_AuthorizationCode.main(null));
	//To get the courses list using POJO classes
	
	getCoursePOJO gc = given().queryParam("access_token", oAuth_AuthorizationCode.main(null)).expect().defaultParser(Parser.JSON)
	.when().get("https://rahulshettyacademy.com/getCourse.php")
	.as(getCoursePOJO.class);
	
	System.out.println(gc.getInstructor());
	System.out.println(gc.getLinkedIn());
	int autoSize = gc.getCourses().getWebAutomation().size();
	int apiSize = gc.getCourses().getApi().size();
	
	ArrayList<String> actualList = new ArrayList<String>();
	for(int i =0; i<autoSize; i++) {
	String gcourseTitle = gc.getCourses().getWebAutomation().get(i).getCourseTitle();
	actualList.add(gcourseTitle);
	if(gcourseTitle.contentEquals("Protractor")) {
		String gcourseprice = gc.getCourses().getWebAutomation().get(i).getPrice();
		System.out.println("Course price of " +gcourseTitle +" is " + gcourseprice);
	}
	}
	
	for(int i =0; i<apiSize; i++) {
		String gcourseTitle = gc.getCourses().getApi().get(i).getCourseTitle();
		System.out.println(gcourseTitle);
		if(gcourseTitle.contentEquals("SoapUI Webservices testing")) {
			String gcourseprice = gc.getCourses().getWebAutomation().get(i).getPrice();
			System.out.println("Course price of " +gcourseTitle +" is " + gcourseprice);
		}
		}
	
	String[] e = {"Selenium Webdriver Java","Cypress","Protractor"};
	List<String> expectedList = Arrays.asList(e);
	
	Assert.assertTrue(actualList.equals(expectedList),"Web automation list are matching");
	 
	}
	
}
