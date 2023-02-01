package API_E2E;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class eCommerceE2E {
	
	public static String token;
	public static String userID;
	public static String productID;
	public static String ExpectpName = "iPhone 14";
	
	@Test(priority = 1)
	public void loginTC() {
	
	loginPLDeserialization loginRes =  given().spec(specBuilder.requestspec()).headers("Content-Type","application/json").body(payloads.loginPLS())
	.expect().defaultParser(Parser.JSON)
	 .when().post("/api/ecom/auth/login")
	 .as(loginPLDeserialization.class);
	
	
	token = loginRes.getToken();
	System.out.println("Authorization token is " +token);
	
	userID = loginRes.getUserId();
	System.out.println("User ID is "+userID);
	 
	String ActualMessage = loginRes.getMessage();
	String ExpectedMessage = "Login Successfully";
	Assert.assertEquals(ActualMessage, ExpectedMessage, "Logged in");
	
	}
	
	@Test(priority = 2)
	public void createProductTC() {
		createPLDeserialization	createRes = given().spec(specBuilder.createReqSpec())
		.param("productName", ExpectpName)
		.param("productAddedBy", userID)
		.param("productCategory", "Phone")
		.param("productSubCategory", "iOS")
		.param("productPrice", "115000")
		.param("productDescription", "iphone 12 latest model phone")
		.param("productFor", "Apple")
		.multiPart("productImage", new File("C:\\Users\\mathu\\Desktop\\dummy.jpg"))
		.when().post("/api/ecom/product/add-product")
		.as(createPLDeserialization.class);
		
		productID = createRes.getProductId();
		System.out.println("Product ID is " +productID);
		
		String ActualMessage = createRes.getMessage();
		System.out.println(ActualMessage);
		String ExpectedMessage = "Product Added Successfully";
		Assert.assertEquals(ActualMessage, ExpectedMessage, "Product added");
			
		
	}
	
	@Test(priority = 3)
	public void viewProductTC() {
		
		viewPLDeserialization viewRes = given().spec(specBuilder.createReqSpec())
		.when().get("/api/ecom/product/get-product-detail/"+productID+"")
		.as(viewPLDeserialization.class);
		
		String prodID = viewRes.getData().get_id();
		Assert.assertEquals(prodID, productID, "Product ID are showing correctly");
		String uID = viewRes.getData().getProductAddedBy();
		Assert.assertEquals(uID, userID, "User ID are showing correctly");
		String ActualpName = viewRes.getData().getProductName();
		Assert.assertEquals(ActualpName, ExpectpName, "Product name showing correctly");
		
		
		String ActualMessage = viewRes.getMessage();
		System.out.println(ActualMessage);
		String ExpectedMessage = "Product Details fetched Successfully";
		Assert.assertEquals(ActualMessage, ExpectedMessage, "Product fetched");
		
		
	}
	
	@Test(priority = 4)
	public void purchaseProductTC() {
	String purchaseRes = given().spec(specBuilder.createReqSpec()).contentType("application/json")
		.body(payloads.purchasePLS())	
		.when().post("/api/ecom/order/create-order")
		.then().extract().response().asString();
		
	JsonPath js = new JsonPath(purchaseRes);
	
	List<String> orderID =	js.get("orders");
	System.out.println("Order id is "+orderID);
	
	List<String> actualProdID = js.get("productOrderId");
	String formattedactualProdID = actualProdID.toString()
		    .replace("[", "")  //remove the right bracket
		    .replace("]", "")  //remove the left bracket
		    .trim(); 
	Assert.assertEquals(formattedactualProdID, productID, "Product ID is matching");
	
	
	String ActualMessage = js.get("message");
	System.out.println(ActualMessage);
	String ExpectedMessage = "Order Placed Successfully";
	Assert.assertEquals(ActualMessage, ExpectedMessage, "Order Placed");
		
	}
	
	@Test(priority = 5)
	public void deleteProductTC() {
		String delRes = given().spec(specBuilder.createReqSpec())
		.when().delete("/api/ecom/product/delete-product/"+productID+"")
		.then().extract().response().asString();
		
		JsonPath jsa = new JsonPath(delRes);
		String ActualMessage = jsa.get("message");
		System.out.println(ActualMessage);
		String ExpectedMessage = "Product Deleted Successfully";
		Assert.assertEquals(ActualMessage, ExpectedMessage, "Product deleted");
		
		
	}
	
	
	
	
	
	
	
}
