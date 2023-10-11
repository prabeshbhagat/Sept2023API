package JsonPathValidatorTest05;
import com.jayway.jsonpath.JsonPath;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

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
		System.out.println(countryList.size());
		System.out.println(countryList);
		
		int totalCircuits=JsonPath.read(jsonResponse, "$.MRData.CircuitTable.Circuits.length()");
		System.out.println("totalCircuits="+totalCircuits);
		
	}
	
	@Test
	public void getProductTest() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		Response response=given()
			.when()
				.get("/products");
		
		String jsonResponse=response.asString();
		
		List<Float> rateLessThanThree=JsonPath.read(jsonResponse, "$[?(@.rating.rate<=3)].rating.rate");
		System.out.println(rateLessThanThree);
		System.out.println(rateLessThanThree.size());
		
		
		List<Map<String,Object>> jeweleryList=
				JsonPath.read(jsonResponse, "$.[?(@.category=='jewelery')][\"title\",\"price\"]");
		System.out.println(jeweleryList);
		
		for(Map<String,Object> product:jeweleryList) {
			String title=(String) product.get("title");
			Object price=product.get("price");
			System.out.println(title);
			System.out.println(price);
			System.out.println("---------------");
		}
	
	}
}
