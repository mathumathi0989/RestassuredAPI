package Deserialization_Authentication;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.webdriver.WebDriverCreator;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class oAuth_AuthorizationCode {

	public static String accessToken;
	
	public static String main(String[] args) {
		//OAuth 2.0 Authorization: 
		//Grant types are "Authorization code, Client credentials, password credentials, etc.."
		//Authorization code ==> if human/user involves between two apps (ie. bookmyshow -> gooogle sign in -> bookmyshow)
		//Client credentials ==> No human intervention; any client website -> their own twitter reviews
		//Password credentials ==> need username and password 

		WebDriverManager.chromedriver().setup();
		
		/*
		ChromeOptions options = new ChromeOptions();
       // options.addArguments("--headless");     
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1400,800");  
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("mathumqa@gmail.com");
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(Keys.ENTER);
		//driver.findElement(By.xpath("(//span[@class='VfPpkd-vQzf8d'])[2]")).click();
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Gurdev@2021");
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(Keys.ENTER);
		String url = driver.getCurrentUrl();
		*/
		
		//To get code from Auth URL	
		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AWtgzh4eZV_xoIqe9s0hwAeeUEfpvzhVsC3p-7dmiPfglNqebBB6EPTiexiDLVJGJ9VDMg&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
		System.out.println(url);		
		String partialurl = url.split("code=")[1];
		String code = partialurl.split("scope=")[0];
		
		
		//To get access token 
		
		String response = given().log().all().urlEncodingEnabled(false)
		.queryParam("code", code)
		.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.queryParam("grant_type", "authorization_code")
		.when().post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		JsonPath js = new JsonPath(response);
		String accessToken = js.get("access_token");
		System.out.println(accessToken);
		return accessToken;
		
		/*
		//To get courses list using OAuth 2.0 through grant type as "Authorization code"
		String coursesList = given().log().all().queryParam("access_token", accessToken)
		.when().get("https://rahulshettyacademy.com/getCourse.php")
		.then().log().all().statusCode(200).extract().asString();
		
		System.out.println(coursesList);
		*/
		
		
			
	}

}
