package API_E2E;

import static io.restassured.RestAssured.*;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;


public class specBuilder {
	
	public static RequestSpecification base;
	
	public static RequestSpecification requestspec() {
	 return base =  new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").build();
	
	}
	
	
}
