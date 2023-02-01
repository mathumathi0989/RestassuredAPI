package API_E2E;

import static io.restassured.RestAssured.*;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;


public class specBuilder {
	
	public static RequestSpecification base;
	public static RequestSpecification crbase;
	
	public static RequestSpecification requestspec() {
	 return base =  new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").build();
	
	}
	
	public static RequestSpecification createReqSpec() {
		 return crbase =  new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").addHeader("Authorization", eCommerceE2E.token).build();
			
			
	}
	
	
}
