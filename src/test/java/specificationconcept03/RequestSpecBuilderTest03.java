package specificationconcept03;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecBuilderTest03 {
	
	@Test
	public void getUser_with_Request_Spec() {
		// 
		RequestSpecification requestSpec=new RequestSpecBuilder()
			.setBaseUri("https://gorest.co.in")
				.setContentType(ContentType.JSON)
					.addHeader("Authorization", "Bearer d4176ed674fe764cb7b27dbf32d481b4fb0c8b4b0721a2aa12583d0d60c3d26b")
						.build();
		
		RestAssured.given().log().all()
			.spec(requestSpec)
				.get("/public/v2/users")
					.then().log().all()
						.statusCode(200);
		
	}
	
	//building a utility of the above lines of code
	
	public static RequestSpecification user_req_spec() {
		
		RequestSpecification requestSpec=new RequestSpecBuilder()
				.setBaseUri("https://gorest.co.in")
					.setContentType(ContentType.JSON)
						.addHeader("Authorization", "Bearer d4176ed674fe764cb7b27dbf32d481b4fb0c8b4b0721a2aa12583d0d60c3d26b")
							.build();
		return requestSpec;		
	}
	
	@Test
	public void getUser_with_Request_Spec_withMethod() {

		RestAssured.given().log().all()
			.spec(user_req_spec())
				.get("/public/v2/users")
					.then().log().all()
						.statusCode(200);	
	}
	
	@Test
	public void getUser_with_Request_Spec_with_Pathparam() {

		RestAssured.given().log().all()
		.queryParam("name", "Ahalya")
		.queryParam("status", "active")
			.spec(user_req_spec())
				.get("/public/v2/users")
					.then().log().all()
						.statusCode(200);	
	}
	

}
