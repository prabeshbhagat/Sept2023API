package POSTAPIs;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.User04;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.UUID;

public class CreateUserWithPojoTest04 {
	
	//1.Directly supply the json string in the body
	//2.Supply the json file path
	//3.POJO java objects to JSon with the help of  jackson/rest assured
	
	
	public static String getRandomEmailId() {
		return "apiAuto"+System.currentTimeMillis()+"@mail.com";
		//return "apiAuto"+UUID.randomUUID()+"mail.com";
	}
	
	@Test
	public void adUserTest() {
		
		RestAssured.baseURI="https://gorest.co.in";
		User04 user= new User04("Prabesh",getRandomEmailId(),"Male","active");
		
		int user_ID=
		given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer d4176ed674fe764cb7b27dbf32d481b4fb0c8b4b0721a2aa12583d0d60c3d26b")
			.body(user)
		.when().log().all()
			.post("/public/v2/users")
		.then().log().all()
			.assertThat()
			.statusCode(201)
			.and()
			.body("name", equalTo(user.getName()))
			.and()
			.extract()
			.path("id");
		
		System.out.println("User Id is:"+user_ID);
		
		//2.get the same user and verify
		
		given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer d4176ed674fe764cb7b27dbf32d481b4fb0c8b4b0721a2aa12583d0d60c3d26b")
		.when()
			.get("/public/v2/users/"+user_ID)
		.then()
			.assertThat()
			.statusCode(200)
			.body("name", equalTo(user.getName()))
			.and()
			.body("status", equalTo(user.getStatus()))
			.and()
			.body("email", equalTo(user.getEmail()))
			.and()
			.body("id", equalTo(user_ID));
			
		
		
		
	}

	

}
