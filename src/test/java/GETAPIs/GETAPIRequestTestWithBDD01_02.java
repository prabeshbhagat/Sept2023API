package GETAPIs;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

public class GETAPIRequestTestWithBDD01_02 {
	
	@Test
	public void getProductTest() {
		
		given().log().all()
			.when().log().all()
				.get("https://fakestoreapi.com/products")
					.then().log().all()
						.assertThat()
								.statusCode(200)
									.and()
										.contentType(ContentType.JSON)
											.and()
												.header("Connection", "keep-alive")
													.and()
														.body("$.size",equalTo(20) )
															.and()
																.body("id",is(notNullValue()))
																	.and()
																		.body("title", hasItem("Mens Cotton Jackets"));
													
						
	}
	
	@Test
	public void getUserAPITest02() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		given().log().all()
			.header("Authorization", "Bearer d4176ed674fe764cb7b27dbf32d481b4fb0c8b4b0721a2aa12583d0d60c3d26b")
				.when().log().all()
					.get("public/v2/users")
						.then().log().all()
							.assertThat()
								.statusCode(200)
									.and()
										.contentType(ContentType.JSON)
											.and()
												.body("$.size()", equalTo(10));
			
			
	}
	
	@Test
	public void getProductDataApiWithQueryParamTest02() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		given().log().all()
			.queryParam("limit", 5)
				.when().log().all()
					.get("/products")
						.then().log().all()
							.assertThat()
								.statusCode(200)
									.and()
										.contentType(ContentType.JSON);
		
	}
	
	@Test
	public void getProductDataApiWithQueryParamTest021() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		given().log().all()
			.queryParam("limit", 5)
				.when().log().all()
					.get("/products")
						.then().log().all()
							.assertThat()
								.statusCode(200)
									.and()
										.contentType(ContentType.JSON);
		
	}
	
	@Test
	public void getUsersAPIQueryParameterAPITest_HW02() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		given().log().all()
			.queryParam("name", "Desai")
				.queryParam("status", "active")
					.header("Authorization","Bearer d4176ed674fe764cb7b27dbf32d481b4fb0c8b4b0721a2aa12583d0d60c3d26b")
						.when().log().all()
								.get("/public/v2/users")
									.then().log().all()
										.assertThat()
											.statusCode(200)
												.and()
													.contentType(ContentType.JSON);
		
		
	}
	
	
	@Test
	public void getProductDataApiWithExtractBody02() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		Response response=given().log().all()
							.queryParam("limit", 5)
								.when().log().all()
									.get("/products");
		
		//Get The id of the first product
		JsonPath js= response.jsonPath();
		int getFirstProductId=js.getInt("[0].id");
		System.out.println("The 1st product Id is :"+getFirstProductId);
		
		//Get the title of the 1st product
		String getFistProductTitle=js.getString("[0].title");
		System.out.println("getFistProductTitle =" +getFistProductTitle);
		
		//Get the price of the 1st product
		Double getFistProductPrice=js.getDouble("[0].price");
		System.out.println("getFistProductPrice="+getFistProductPrice);
		
		//Get the count of the 1st product
		int count=js.getInt("[0].rating.count");
		System.out.println("count="+count);
			
	}
	
	@Test
	public void getProductDataApiWithExtractBodyWithJsonArray02() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		Response response=given().log().all()
							.queryParam("limit", 10)
								.when().log().all()
									.get("/products");
		
		//Get all The id of the all  product
		JsonPath js= response.jsonPath();
		List<Integer> idList=js.getList("id");
		System.out.println("Size fo the list "+idList.size());
		System.out.println(idList);
		
		
		//Get all The title of the all  product
		List<String> titleList=js.getList("title");
		System.out.println("Size fo the list "+titleList.size());
		System.out.println(titleList);
		
		List<Object> rateList=js.getList("rating.rate");
		System.out.println(rateList);
		
		
		List<Integer> countList=js.getList("rating.count");
		System.out.println(countList);
		
		//iterate and print all values
		for(int i=0;i<idList.size();i++) {
			int id=idList.get(i);
			String title=titleList.get(i);
			Object rate=rateList.get(i);
			int count=countList.get(i);
			
			System.out.println("index:"+i+"id="+id+"title="+title+"rate="+rate+"count="+count);
		}
			
	}
	
	
	@Test
	public void getUsersDataApiWithExtractBodyWithJSONArray02() {
		RestAssured.baseURI="https://gorest.co.in";
		
		Response response= given().log().all()
			.header("Authorization","Bearer d4176ed674fe764cb7b27dbf32d481b4fb0c8b4b0721a2aa12583d0d60c3d26b")
				.when().log().all()
				.get("/public/v2/users");
				
		
		JsonPath js=response.jsonPath();//returns json array
		List<Integer> allIds=js.getList("id");
		System.out.println(allIds);
		
		List<String> allEmail=js.getList("email");
		System.out.println(allEmail);
				
	}
	
	@Test
	public void getUserDataApiWithExtractBodyWithArray02() {
		RestAssured.baseURI="https://gorest.co.in";
		
		Response response= given().log().all()
			.header("Authorization","Bearer d4176ed674fe764cb7b27dbf32d481b4fb0c8b4b0721a2aa12583d0d60c3d26b")
				.when().log().all()
				.get("/public/v2/users/5188822");
				
		
		JsonPath js=response.jsonPath();
		int id=js.getInt("id");
		System.out.println(id);
				
	}
	//extract method is used in booking apis where we get the token by supplying un & pwd
	// extract the token from the response by supplying path
	
	@Test
	public void getUserDataApiWithExtractBodyWithJSONExtractMethod02() {
		RestAssured.baseURI="https://gorest.co.in";
		
		
		//1st Way
//		int id= given().log().all()
//			.header("Authorization","Bearer d4176ed674fe764cb7b27dbf32d481b4fb0c8b4b0721a2aa12583d0d60c3d26b")
//				.when().log().all()
//				.get("/public/v2/users/5188822")
//					.then()
//						.extract().path("id");
				
		
		//JsonPath js=response.jsonPath();
		//int id=js.getInt("id");
	//	System.out.println(id);
		
		//2nd Way
//		Response response= given().log().all()
//				.header("Authorization","Bearer d4176ed674fe764cb7b27dbf32d481b4fb0c8b4b0721a2aa12583d0d60c3d26b")
//					.when().log().all()
//					.get("/public/v2/users/5188822");
//		
//		
//		int id=response.then().extract().path("id");
//		String email=response.then().extract().path("email");
//		System.out.println("The Id is :"+id);
//		System.out.println("The email is :"+email);
		
		//3rd Way remove the duplicate lines then & extract
		Response response= given().log().all()
				.header("Authorization","Bearer d4176ed674fe764cb7b27dbf32d481b4fb0c8b4b0721a2aa12583d0d60c3d26b")
					.when().log().all()
					.get("/public/v2/users/5188822")
					.then().extract().response();
		
		
		int id=response.path("id");
		String email=response.path("email");
		System.out.println("The Id is :"+id);
		System.out.println("The email is :"+email);
		
		
		
		
		
		
	}
	

}
