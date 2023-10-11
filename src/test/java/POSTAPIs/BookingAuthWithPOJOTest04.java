package POSTAPIs;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.Credentials04;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BookingAuthWithPOJOTest04 {
	
	//POJO plane old Java Object
	//cannot be a parent or child class
	//cannot extend any other class
	//oops-Encapuslation
	//private class variables-- JSON Body
	//use public getter/Setter
	
	
	//Serilization-->Java object to other format:JSOn/text/pdf/xml,file,media
	//pojo to json serilization
	//json to pojo de-serilization
	
	
	
	
	@Test
	public void getBookingAuth_With_JSON_String_ExtractTokenTest() {
		
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		Credentials04 creds = new Credentials04("admin","password123");
		
						
		String token_value=	given().log().all()
			.header("Content-Type","application/json")
			.body(creds)
			
			.when().log().all()
				.post("/auth")
			.then().log().all()
				.assertThat()
				.statusCode(200)
				.extract()
				.path("token");
		
		System.out.println("token_value"+token_value);
		Assert.assertNotNull(token_value);
		
	}
	
	
	
	
	
	

}
