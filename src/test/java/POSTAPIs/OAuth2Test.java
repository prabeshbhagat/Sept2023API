package POSTAPIs;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class OAuth2Test {
	
	static String token_id;
	
	@BeforeMethod
	public void getAccessToken() {
		
		RestAssured.baseURI="https://test.api.amadeus.com";
		
		 token_id=given().log().all()
				.header("Content-Type", "application/x-www-form-urlencoded")
				.formParam("grant_type", "client_credentials")
				.formParam("client_id", "5KAP1LGPjN7EybUWBSGgFADSqB5LfBeE")
				.formParam("client_secret", "Nf49H0qmE1JT2aYn")
			.when()
				.post("/v1/security/oauth2/token")
			.then()
				.assertThat()
					.statusCode(200)
				.extract()
					.path("access_token");
			
			System.out.println("token_id=="+token_id);
		
	}
	
	@Test
	public void getFlighTInfoTest() {
		
		RestAssured.baseURI="https://test.api.amadeus.com";
		
//		String token_id=given().log().all()
//			.header("Content-Type", "application/x-www-form-urlencoded")
//			.formParam("grant_type", "client_credentials")
//			.formParam("client_id", "5KAP1LGPjN7EybUWBSGgFADSqB5LfBeE")
//			.formParam("client_secret", "Nf49H0qmE1JT2aYn")
//		.when()
//			.post("/v1/security/oauth2/token")
//		.then()
//			.assertThat()
//				.statusCode(200)
//			.extract()
//				.path("access_token");
//		
//		System.out.println("token_id=="+token_id);
		
		
		
		//2.get_Flight_Info:GET
		Response flisghtDataresponse=given().log().all()
			.header("Authorization", "Bearer "+token_id)
			.queryParam("origin", "PAR")
			.queryParam("maxPrice", "200")
		.when().log().all()
			.get("/v1/shopping/flight-destinations")
			.then().log().all()
			.assertThat()
				.statusCode(200)
				.and()
				.extract().response();
		
		  JsonPath js=flisghtDataresponse.jsonPath();
		  String type=js.get("data[0].type");
		  System.out.println("type"+type);
		  String origin=js.get("data[0].origin");
		  System.out.println("origin"+origin);
	
	}
	

}
