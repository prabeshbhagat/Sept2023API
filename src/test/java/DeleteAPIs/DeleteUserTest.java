package DeleteAPIs;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import PUTAPIs07.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;

public class DeleteUserTest {

	// 1. create User--201--user id
	// 2. delete user -204 -user id
	// 3. get a user -404 user id

	public static String getRandomEmailId() {
		return "apiAuto" + System.currentTimeMillis() + "@mail.com";
	}

	@Test
	public void deleteUser() {

		RestAssured.baseURI = "https://gorest.co.in";

		User user = new User("Prabesh", getRandomEmailId(), "active", "male");

		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.header("Authorization", "Bearer d4176ed674fe764cb7b27dbf32d481b4fb0c8b4b0721a2aa12583d0d60c3d26b")
				.body(user)
				.when()
				.post("/public/v2/users/");

		Integer user_id = response.jsonPath().get("id");
		System.out.println("user_id=" + user_id);
		
		response.prettyPrint();

		System.out.println("============Delete The  user====================");
		
		RestAssured.given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer d4176ed674fe764cb7b27dbf32d481b4fb0c8b4b0721a2aa12583d0d60c3d26b")
			.when().log().all()
			.delete("/public/v2/users/"+user_id)
			.then().log().all()
			.assertThat()
			.statusCode(204);
		
		System.out.println("============Fetch The  user====================");
		
		RestAssured.given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer d4176ed674fe764cb7b27dbf32d481b4fb0c8b4b0721a2aa12583d0d60c3d26b")
			.when().log().all()
			.get("/public/v2/users/"+user_id)
			.then().log().all()
			.assertThat()
			.statusCode(404)
			.and()
			.body("message", equalTo("Resource not found"));

	}

}
