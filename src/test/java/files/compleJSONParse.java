package files;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class compleJSONParse {

	public static 	JsonPath js;
	public static int purAmount;
	public static String coursePrice() {
		return "{\r\n"
				+ "\"dashboard\": {\r\n"
				+ "\"purchaseAmount\": 910,\r\n"
				+ "\"website\": \"rahulshettyacademy.com\"\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "\"courses\": [\r\n"
				+ "{\r\n"
				+ "\"title\": \"Selenium Python\",\r\n"
				+ "\"price\": 50,\r\n"
				+ "\"copies\": 6\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\"title\": \"Cypress\",\r\n"
				+ "\"price\": 40,\r\n"
				+ "\"copies\": 4\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\"title\": \"RPA\",\r\n"
				+ "\"price\": 45,\r\n"
				+ "\"copies\": 10\r\n"
				+ "}\r\n"
				+ "]\r\n"
				+ "}";
	}
	
@Test
	public static void compl() {
	js = new JsonPath(coursePrice());
		
		int NoOfCourses = js.getInt("courses.size");
		System.out.println("No. of courses in the API are " +NoOfCourses);
		
		 purAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase amount is "+purAmount);
		
		String title = js.get("courses[0].title");
		System.out.println("Title of the first course is "+title);
		
		for (int i=0; i<js.getInt("courses.size"); i++) {
			for(int j=i;j<=i;j++) {
		String courTitle = js.get("courses[" +i +"].title");
		int courPrice = js.getInt("courses[" +j +"].price");
		System.out.print(courTitle);
		System.out.println(" "+courPrice);
			}
		}
		
		System.out.println("---------------------------------------------");
		/*
		for (int i=0; i<js.getInt("courses.size"); i++) {

		String courTitle = js.get("courses[" +i +"].title");
		String courPrice  = js.get("courses[" +i +"].price").toString();
		System.out.print(courTitle);
		System.out.println(" "+courPrice);
			}
			*/
		for (int l=0; l<js.getInt("courses.size"); l++) {
			String tit = js.get("courses[" +l +"].title");
			if(tit.equalsIgnoreCase("RPA")) {
				int rpaCour = js.get("courses[" +l +"].copies");
		System.out.println("No. of copies RPA: "+rpaCour);
		break;
			}
		}
		System.out.println("---------------------------------------------");
		
		
	
	
		
		
		}
		
@Test
		public static void sumval() {
			js = new JsonPath(coursePrice());
			int k = 0;
			for (int i=0; i<js.getInt("courses.size"); i++) {
				int total = 0;
				
				for (int j=i; j<=i;j++) {
		
				int Price = js.getInt("courses[" +j +"].price");
				int copies = js.getInt("courses[" +j +"].copies");
				
				 total = Price * copies;
			
				}
				k =  total + k;
			}
			System.out.println("Sum of all course prices "+k);
			Assert.assertEquals(k, purAmount);
		if(k==purAmount) {
			System.out.println("prices are matching ");
		}
		else {
			System.out.println("prices are not matching. Do cross check");
		}
			
		}
		
	}
	

