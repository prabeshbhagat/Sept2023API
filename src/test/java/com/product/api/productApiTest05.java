package com.product.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.Product05;
import pojo.Product05.Rating;
import pojo.ProductLombok05;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class productApiTest05 {
	
	@Test
	public void getProductTest_With_Pojo() {
		RestAssured.baseURI="https://fakestoreapi.com";
		
		Response response=given().log().all()
		.when().log().all()
		.get("/products");
	
		//Json to pojo mapping:Deserilization
		
		ObjectMapper mapper= new ObjectMapper();
		try {
			Product05 product[]=mapper.readValue(response.getBody().asString(), Product05[].class);
			
			for(Product05 p:product) {
				System.out.println("Id:"+p.getId());
				System.out.println("title:"+p.getTitle());
				System.out.println("price:"+p.getPrice());
				System.out.println("description:"+p.getDescription());
				System.out.println("Image:"+p.getImage());
				System.out.println("rate:"+p.getRating().getRate());
				System.out.println("count:"+p.getRating().getCount());
				System.out.println("=========================");
				
			}
		
			
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void getProductTest_With_Pojo_Lombok_BuilderPattern() {
		

	
}
	
	
	

}
