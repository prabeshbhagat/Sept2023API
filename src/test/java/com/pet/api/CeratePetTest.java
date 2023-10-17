package com.pet.api;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet.api.PetstoreLombok.Category;
import com.pet.api.PetstoreLombok.Tag;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CeratePetTest {
	
	Category category= new Category(1, "Bird");
	List<String> urlList= Arrays.asList("www.dog.com","www.dog1.com");
	Tag tag= new Tag(15,"Blue");
	Tag tag1= new Tag(16,"Red");
	List<Tag> tags=Arrays.asList(tag,tag1);
	PetstoreLombok pet= new PetstoreLombok(200, category, "Peacock", urlList, "Available", tags);
	
	
	@Test
	public void ceratePetTest(){
		
	RestAssured.baseURI="https://petstore.swagger.io";
	
	Response response=RestAssured.given()
			.contentType(ContentType.JSON)
			.body(pet)//serilize
			.when()
			.post("/v2/pet");
	
	System.out.println(response.statusCode());
	System.out.println(response.prettyPrint());
	
	
	//Deserilize
	ObjectMapper obj = new ObjectMapper();
	try {
		PetstoreLombok pets=obj.readValue(response.getBody().asString(), PetstoreLombok.class);
		System.out.println(pets.getId());
		System.out.println(pets.getCategory().getId());
		System.out.println(pets.getCategory().getName());
		System.out.println(pets.getName());
		System.out.println(pets.getPhotoUrls());
		System.out.println(pets.getTags().get(0).getId());
		System.out.println(pets.getTags().get(0).getName());
		System.out.println(pets.getTags().get(1).getId());
		System.out.println(pets.getTags().get(1).getName());
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	
		
	}

}
