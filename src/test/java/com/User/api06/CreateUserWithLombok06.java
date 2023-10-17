package com.User.api06;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserWithLombok06 {
	
	public static String getRandomEmailId() {
		return "apiAuto"+System.currentTimeMillis()+"@mail.com";
		//return "apiAuto"+UUID.randomUUID()+"mail.com";
	}
	
	@Test
	public void createUserTest() {
		
		
		RestAssured.baseURI="https://gorest.co.in";
		User06 user= new User06("Prabesh", getRandomEmailId(), "male", "active");
		
		
		//post call pojo to json
		Response response=RestAssured.given()
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer d4176ed674fe764cb7b27dbf32d481b4fb0c8b4b0721a2aa12583d0d60c3d26b")
		.body(user) //serilization
		.when()
		.post("/public/v2/users/");
		
		int User_id=response.jsonPath().get("id");
		System.out.println("User_id="+User_id);
		Assert.assertNotNull(User_id);
		
		System.out.println("==================");
		
		
		Response getResponse=RestAssured.given().log().all()
				.contentType(ContentType.JSON)
				.header("Authorization", "Bearer d4176ed674fe764cb7b27dbf32d481b4fb0c8b4b0721a2aa12583d0d60c3d26b")
				.when().log().all()
				.get("/public/v2/users/"+User_id);
		
		System.out.println("++++++++++++++++++++++");
		//System.out.println(getResponse.getBody().asString());
		
		//Deserilize json to Pojo
		ObjectMapper map= new ObjectMapper();
		try {
			User06 userRes=map.readValue(getResponse.getBody().asString(), User06.class);  //deserilize 
			System.out.println(userRes.getId() + ":" + userRes.getEmail() + ": " + userRes.getStatus() + ": " + userRes.getGender());
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void createUserWithBuilderPattern_Test() {
		
		
		RestAssured.baseURI="https://gorest.co.in";
		User06 user = new User06.UserBuilder()
				.name("Naveen")
				.email(getRandomEmailId())
				.status("active")
				.gender("male")
				.build();
	
		
		
		//post call pojo to json
		Response response=RestAssured.given()
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer d4176ed674fe764cb7b27dbf32d481b4fb0c8b4b0721a2aa12583d0d60c3d26b")
		.body(user) //serilization
		.when()
		.post("/public/v2/users/");
		
		int User_id=response.jsonPath().get("id");
		System.out.println("User_id="+User_id);
		Assert.assertNotNull(User_id);
		
		System.out.println("==================");
		
		
		Response getResponse=RestAssured.given().log().all()
				.contentType(ContentType.JSON)
				.header("Authorization", "Bearer d4176ed674fe764cb7b27dbf32d481b4fb0c8b4b0721a2aa12583d0d60c3d26b")
				.when().log().all()
				.get("/public/v2/users/"+User_id);
		
		System.out.println("++++++++++++++++++++++");
		//System.out.println(getResponse.getBody().asString());
		
		//Deserilize json to Pojo
		ObjectMapper map= new ObjectMapper();
		try {
			User06 userRes=map.readValue(getResponse.getBody().asString(), User06.class);  //deserilize 
			System.out.println(userRes.getId() + ":" + userRes.getEmail() + ": " + userRes.getStatus() + ": " + userRes.getGender());
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	

}
