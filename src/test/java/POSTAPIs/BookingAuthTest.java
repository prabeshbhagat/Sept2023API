package POSTAPIs;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import io.restassured.RestAssured;

public class BookingAuthTest {
	
	
	@Test
	public void getBookingAuth_With_JSON_String_ExtractTokenTest() {
		
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		
		String token_value=given().log().all()
		.header("Content-Type","application/json")
		.body("{\n"
				+ "    \"username\" : \"admin\",\r\n"
				+ "    \"password\" : \"password123\"\n"
				+ "}")
			.when().log().all()
			.post("/auth")
				.then().log().all()
				.assertThat()
				.statusCode(200)
				.extract()
				.path("token");
		
		System.out.println(token_value);
		Assert.assertNotNull(token_value);
			
	}
	
	@Test
	public void getBookingAuth_With_JSON_File_String_ExtractTokenTest() {
		
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		
		String tokenValue=given().log().all()
		.header("Content-Type", "application/json")
		.body(new File("./src/test/resources/data/basicAuth.json\\"))
		.when().log().all()
		.post("/auth")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.extract()
		.path("token");
		
		System.out.println(tokenValue);
		
	
	}
	
//	//Sept2023API/src/test/resources/data/addAUser
	
	//post--add a user--user id--123--assert(201,body)
	//get -- get a user --/user/123--200--userid=123
	
	@Test
	public void addUserTest() {
		
		RestAssured.baseURI="https://gorest.co.in";
		//1.Add a user POST
		int user_id=given().log().all()
					.header("Content-Type", "application/json")
					.header("Authorization", "Bearer d4176ed674fe764cb7b27dbf32d481b4fb0c8b4b0721a2aa12583d0d60c3d26b")
					.body(new File("./src/test/resources/data/addAUser\\"))
			.when().log().all()
				.post("public/v2/users")
			.then().log().all()
				.assertThat()
				.statusCode(201)
				.and()
				.body("name", equalTo("Prabesh"))
				.extract()
				.path("id");
		
		System.out.println("User_id -->"+user_id);
		
		
		//2.get the same user and verify 
		given().log().all()
			.header("Content-Type", "application/json")
			.header("Authorization", "Bearer d4176ed674fe764cb7b27dbf32d481b4fb0c8b4b0721a2aa12583d0d60c3d26b")
		.when().log().all()
			.get("public/v2/users/"+user_id)
		.then().log().all()
			.assertThat()
			.statusCode(200)
			.and()
			.body("name", equalTo("Prabesh"))
			.and()
			.body("id", equalTo(user_id));
		
		
		
	}
	
	
	
	
	
	

}
