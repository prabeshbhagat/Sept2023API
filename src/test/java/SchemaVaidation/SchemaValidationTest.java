package SchemaVaidation;
import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SchemaValidationTest {

	@Test
	public void createUserAPISchemaValidationTest() {

			
			RestAssured.baseURI="https://gorest.co.in";
			//1.Add a user POST
			given().log().all()
						.header("Content-Type", "application/json")
						.header("Authorization", "Bearer d4176ed674fe764cb7b27dbf32d481b4fb0c8b4b0721a2aa12583d0d60c3d26b")
						.body(new File("./src/test/resources/data/addAUser\\"))
				.when().log().all()
					.post("public/v2/users")
				.then().log().all()
					.assertThat()
					.statusCode(201)
					.body(matchesJsonSchemaInClasspath("createuserschema.json"));
			
			

	}
	
	
	@Test
	public void getUserAPISchemaValidationTest() {

			
			RestAssured.baseURI="https://gorest.co.in";
			//1.Add a user POST
			given().log().all()
						.header("Content-Type", "application/json")
						.header("Authorization", "Bearer d4176ed674fe764cb7b27dbf32d481b4fb0c8b4b0721a2aa12583d0d60c3d26b")
				.when().log().all()
					.get("public/v2/users")
				.then().log().all()
					.assertThat()
					.statusCode(200)
					.body(matchesJsonSchemaInClasspath("getuserschema.json"));
			
			

	}

}
