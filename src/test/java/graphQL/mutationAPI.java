package graphQL;

import static io.restassured.RestAssured.*;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class mutationAPI {

	
	
	public static void main(String[] args) {
		
		//Mutation -- Creating graphQL
		String response = given().log().all().header("Content-Type", "application/json")
		.body("{\"query\":\"mutation\\n($locationname: String!, $charactername: String!) {\\n  createLocation(location :{name:$locationname,type:\\\"lat\\\",dimension:\\\"564\\\"})\\n\\n  {\\n    id\\n  }\\n  \\n  createCharacter(character: {name:$charactername, type: \\\"long\\\", status:\\\"alive\\\", species:\\\"Human\\\", gender:\\\"male\\\", image: \\\"jpeg\\\", originId: 155, locationId:1200 } )\\n    \\n    {\\n      id\\n    }\\n    \\n    \\n    \\n}\\n\",\"variables\":{\"locationname\":\"mathumathi\",\"charactername\":\"gurdev\"}}")
		.when().post("https://rahulshettyacademy.com/gq/graphql")
		.then().log().all().extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		String LocationID = js.getString("data.createLocation.id");
		System.out.println(LocationID);
		
		String CharacterID = js.getString("data.createCharacter.id");
		System.out.println(CharacterID);
		
		System.out.println("----------------------------------------------------------------------------------------------");
		//Query -- Executing graphQL
		
		String respo = given().log().all().header("Content-Type", "application/json")
		.body("{\"query\":\"query ($locationId: Int!, $characterId: Int!) {\\n  character(characterId: $characterId) {\\n      id\\n    name\\n    type\\n    status\\n  }\\n  location(locationId: $locationId) {\\n      id\\n    name\\n    type\\n  }\\n  episodes(filters: {name: \\\"Rahul\\\"}) {\\n    info {\\n      count\\n    }\\n  }\\n}\\n\",\"variables\":{\"locationId\":"+LocationID+",\"characterId\":"+CharacterID+"}}")
		.when().post("https://rahulshettyacademy.com/gq/graphql")
		.then().log().all().extract().response().asString();
		
		JsonPath jsa = new JsonPath(respo);
		String charID = jsa.getString("data.character.id");
		Assert.assertEquals(CharacterID, charID, "Character ID are same");
		String locID = jsa.getString("data.location.id");
		Assert.assertEquals(LocationID, locID, "Location ID are same");
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
}
