package PUTAPIs07;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateUserTest {
	
	
	//create user--post -->UserId
	//update user--put-->userID--
	public static String getRandomEmailId() {
		return "apiAuto"+System.currentTimeMillis()+"@mail.com";
	}
	@Test
	public void updateUsertTest() {
				
		RestAssured.baseURI="https://gorest.co.in";
		
		User user = new User("Prabesh", getRandomEmailId(), "active", "male");	
		
		Response response=RestAssured.given()
				.contentType(ContentType.JSON)
				.header("Authorization", "Bearer d4176ed674fe764cb7b27dbf32d481b4fb0c8b4b0721a2aa12583d0d60c3d26b")
				.body(user)
				.when()
					.post("/public/v2/users/");	
		
				Integer user_id=response.jsonPath().get("id");		
				System.out.println("user_id="+user_id);
				
				System.out.println("================================");
				
				user.setName("Prabesh12");
				user.setStatus("inactive");
				
				RestAssured.given().log().all()
						.contentType(ContentType.JSON)
						.header("Authorization", "Bearer d4176ed674fe764cb7b27dbf32d481b4fb0c8b4b0721a2aa12583d0d60c3d26b")
						.body(user)
						.when().log().all()
						.put("/public/v2/users/"+user_id) //patch will also work
						.then().log().all()
						.assertThat()
						.statusCode(200)
						.and()
						.body("id", equalTo(user_id))
						.and()
						.body("name", equalTo(user.getName()))
						.and()
						.body("status", equalTo(user.getStatus()));
							
	
	}
	
	@Test
	public void updateUsertWithBuilderPatternTest() {
				
		RestAssured.baseURI="https://gorest.co.in";
		
		User user=new User.UserBuilder()
		.name("Sella")
		.email(getRandomEmailId())
		.status("active")
		.gender("Female")
		.build();
		
		
		Response response=RestAssured.given()
				.contentType(ContentType.JSON)
				.header("Authorization", "Bearer d4176ed674fe764cb7b27dbf32d481b4fb0c8b4b0721a2aa12583d0d60c3d26b")
				.body(user)
				.when()
					.post("/public/v2/users/");	
		
				Integer user_id=response.jsonPath().get("id");		
				System.out.println("user_id="+user_id);
				
				System.out.println("================================");
				
				user.setName("Shreeya21");
				user.setStatus("inactive");
				
				RestAssured.given().log().all()
						.contentType(ContentType.JSON)
						.header("Authorization", "Bearer d4176ed674fe764cb7b27dbf32d481b4fb0c8b4b0721a2aa12583d0d60c3d26b")
						.body(user)
						.when().log().all()
						.put("/public/v2/users/"+user_id)
						.then().log().all()
						.assertThat()
						.statusCode(200)
						.and()
						.body("id", equalTo(user_id))
						.and()
						.body("name", equalTo(user.getName()))
						.and()
						.body("status", equalTo(user.getStatus()));
							
	
	}
	
	
	
	

}
