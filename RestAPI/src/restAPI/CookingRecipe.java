package restAPI;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
public class CookingRecipe {
	
	@BeforeTest
	public void setBaseURI(){
		RestAssured.baseURI = "https://myCookingDiary.com";
	}

		//1. Log in with valid user name & password
		@Test
		public void validLogin()
		{
		Map<String, Object> credentials =new HashMap<>();
		credentials.put("username", "USer123");
		credentials.put("password", "pass123");
		given().contentType("application/json")
		.body(credentials).
		when().post("/login").
		then().assertThat().statusCode(200);		
		}
		
		//2. Log in with invalid user name & password
		@Test
		public void invalidLogin(){
			Map<String, Object> credentials =new HashMap<>();
			credentials.put("username", "UserXX");
			credentials.put("password", "passXX");
			given().contentType("application/json"). body(credentials).
			when().post("/login").
			then().assertThat().statusCode(401);
			
		}
		
		//3.GET request and validate recipe Indicator(type)
		@Test
		public void indicator(){
			String response = given().queryParam("key", "recipes").queryParam("id", "1").
			when().get("/users/recipe").
			then().assertThat().statusCode(200).extract().response().asString();
			
			JsonPath js = new JsonPath(response);
			String indicator = js.getString("Type");
			Assert.assertEquals(indicator, "VEG");	
		}
		
		//4. Create(POST) recipe
		@Test
		public void createRecipe(){
			Map<String, Object> recipeDetails =new HashMap<>();
			recipeDetails.put("name", "Soup");
			recipeDetails.put("Date","22/09/2020");
			recipeDetails.put("Time","12:00:00");
			recipeDetails.put("Type","VEG");
			recipeDetails.put("Count","3");
			recipeDetails.put("Ingredients","Tomato, Carrot,Spinach,Beans,Salt");
			recipeDetails.put("Instructions","Boil water, add ingredients, cook for 5 mins");
		String message =	given().contentType("JSON").body(recipeDetails).
			when().post("/users/recipe/add").
			then().assertThat().statusCode(201).extract().response().asString();
		
		JsonPath js = new JsonPath(message);
		String mesg = js.getString("Message");
			Assert.assertEquals(mesg, "Recipe added Successfully");
		}
		
		//5. Update(PUT) recipe
		@Test
		public void updateRecipe(){
			Map<String, Object> recipeDetails =new HashMap<>();
			recipeDetails.put("name", "FriedRice");
			recipeDetails.put("Date","22/09/2020");
			recipeDetails.put("Time","12:00:00");
			recipeDetails.put("Type","VEG");
			recipeDetails.put("Count","2");
			recipeDetails.put("Ingredients","Vegetables,Rice,oil,spices");
			recipeDetails.put("Instructions","add oil, fry vegetables,add water,add rice,cook for 20 mins");
		    given().queryParam("id","2").contentType("JSON").body(recipeDetails).
			when().put("/users/recipe").
			then().assertThat().statusCode(200);
		
		}
		
		//6. Delete Recipe
		@Test
		public void deleteRecipe(){
			given().queryParam("id","1").contentType("JSON").
			when().delete("/users/recipe").
			then().assertThat().statusCode(200);
		}

}
