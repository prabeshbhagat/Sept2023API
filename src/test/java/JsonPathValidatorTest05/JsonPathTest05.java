package JsonPathValidatorTest05;
import com.jayway.jsonpath.JsonPath;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;


import java.util.List;
import java.util.Map;

public class JsonPathTest05 {
	
	
	@Test
	public void voidGetCircuitDataWithYearTest() {
		
		RestAssured.baseURI="https://ergast.com";
		
		Response response=given().log().all()
			.when().log().all()
				.get("/api/f1/2017/circuits.json");
		
		String jsonResponse=response.asString();
		System.out.println(jsonResponse);
		
		List<String> countryList=JsonPath.read(jsonResponse, "$..Circuits..country");
		for(String s:countryList) {
			System.out.println(s);
		}
		//System.out.println(countryList.size());
		//System.out.println(countryList);
		
		int totalCircuits=JsonPath.read(jsonResponse, "$.MRData.CircuitTable.Circuits.length()");
		System.out.println("totalCircuits="+totalCircuits);
	}
	
	
	@Test
	public void getCircuitTest() {		
		RestAssured.baseURI = "https://ergast.com";	
		Response response=given()
			.when()
				.get("/api/f1/2019/circuits.json");
		
		String jsonResponse=response.asString();

		
		List<Map<String,Object>> jeweleryList=
				JsonPath.read(jsonResponse, "$..Circuits[*].[\"circuitId\",\"circuitName\"]");
		System.out.println("*****************");
		System.out.println(jeweleryList);
		System.out.println("*****************");
//		for(Map<String,Object> product:jeweleryList) {
//			String title=(String) product.get("locality");
//			Object price=product.get("long");
//			System.out.println(title);
//			System.out.println(price);
//			System.out.println("---------------");
//		}

	}
	
	@Test
	public void getProductTest() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		Response response=given()
			.when()
				.get("/products");
		
		String jsonResponse=response.asString();
		JsonPath.read(jsonResponse, "$[?(@.rating.rate<=3)].rating.rate");
		
		List<Float> rateLessThanThree=JsonPath.read(jsonResponse, "$[?(@.rating.rate<=3)].rating.rate");
		//System.out.println(rateLessThanThree);
		//System.out.println(rateLessThanThree.size());
		
		//With two Key attributes.
		List<Map<String,Object>> jeweleryList=
				JsonPath.read(jsonResponse, "$.[?(@.category=='jewelery')][\"title\",\"price\"]");
		System.out.println("*****************");
		System.out.println(jeweleryList);
		System.out.println("*****************");
		for(Map<String,Object> product:jeweleryList) {
			String title=(String) product.get("title");
			Object price=product.get("price");
			System.out.println(title);
			System.out.println(price);
			System.out.println("---------------");
		}
		
		
		//With three Key attributes.
				List<Map<String,Object>> jeweleryListWithID=
						JsonPath.read(jsonResponse, "$.[?(@.category=='jewelery')][\"price\",\"id\",\"title\"]");
				System.out.println("=================================");
				System.out.println(jeweleryListWithID);
				System.out.println("=================================");
				for(Map<String,Object> product:jeweleryListWithID) {
					Object price=product.get("price");
					Object id=product.get("id");
					Object title=product.get("title");
					System.out.println("############");
					System.out.println(price);
					System.out.println(id);
					System.out.println(title);
					System.out.println("############");
				}
			
	
	}
	

	//$[*].id
	@Test
	public void getPetStoreTest() {
		RestAssured.baseURI = "https://petstore.swagger.io";
		Response response=given()
			.when().log().all()
				.get("v2/pet/findByStatus?status=available");
		String jsonResponse=response.asString();
		System.out.println(jsonResponse);
		
		//$[*].name
		List<String> names=JsonPath.read(jsonResponse, "$[*].name");
		System.out.println(names);
	}
}
