package testCases;

import org.testng.Assert;
import org.testng.annotations.*;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

public class TC_VideoGameAPI {

	@Test(priority=1)
	public void test_getAllVideoGames()
	{

	 given()

	 .when()
	   .get("http://localhost:8080/app/videogames")

	  .then()
	   .statusCode(200);

	}

	@Test(priority=2)
	public void test_addNewVideoGame()
	{
	  HashMap data = new HashMap();
	  data.put("id", "100");
	  data.put("name", "Spider-Man");
	  data.put("releaseDate", "2022-07-25T06:23:27.309Z");
	  data.put("reviewScore",  "5");
	  data.put("category",  "Adventure");
	  data.put("rating",  "Universal");
	  
	  Response res = 
	  given()
	        .contentType("application/json")
	        .body(data)
	  .when()
		.post("http://localhost:8080/app/videogames")
	  
	  .then()
	    .statusCode(200)
	    .log().body()
	    .extract().response();


	  String jsonString = res.asString();
	  Assert.assertEquals(jsonString.contains("Record Added Successfully"), true);

	}

}
