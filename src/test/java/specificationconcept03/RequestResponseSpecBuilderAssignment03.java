package specificationconcept03;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RequestResponseSpecBuilderAssignment03 {

	public static RequestSpecification user_req_spec() {

		RequestSpecification requestSpec = new RequestSpecBuilder().setBaseUri("https://gorest.co.in")
				.setContentType(ContentType.JSON)
				.addHeader("Authorization", "Bearer d4176ed674fe764cb7b27dbf32d481b4fb0c8b4b0721a2aa12583d0d60c3d26b")
				.build();
		return requestSpec;
	}

	public static ResponseSpecification get_Resp_Spec_200Ok() {

		ResponseSpecification resp_spec_200Ok = new ResponseSpecBuilder().expectContentType(ContentType.JSON)
				.expectHeader("Server", "cloudflare").expectStatusCode(200).build();

		return resp_spec_200Ok;
	}
	

	public static ResponseSpecification get_Resp_Spec_200OkWithBody() {

		ResponseSpecification resp_spec_200Ok = new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON)
				.expectHeader("Server", "cloudflare")
				.expectStatusCode(200)
				.expectBody("$.size",equalTo(10))
				.expectBody("id",hasSize(10))
				.build();
		

		return resp_spec_200Ok;
	}
	
	
	public static ResponseSpecification get_Resp_Spec_401AuthFail() {
		
		ResponseSpecification resp_spec_401Authfail= new ResponseSpecBuilder()
		.expectHeader("Server", "cloudflare")
		.expectStatusCode(401)
		.build();
		
		return resp_spec_401Authfail;	
	}
	
	@Test
	public void get_User_Resp_200_spec_test() {
		
		
		given().log().all()
			.spec(user_req_spec())
					.get("/public/v2/users")
						.then()
							.assertThat()
								.spec(get_Resp_Spec_200Ok());
				
	}
	
	@Test
	public void get_User_Resp_200_spec_with_body_test() {
		RestAssured.given().log().all()
					.spec(user_req_spec())
					.get("/public/v2/users")
					.then()
					.assertThat()
					.spec(get_Resp_Spec_200OkWithBody());
				
	}
	
	
	@Test
	public void get_User_Resp_401_AuthFail_spec_test(){
		
		RestAssured.given().log().all()
		.spec(user_req_spec())
		.get("/public/v2/users")
		.then().log().all()
		.assertThat()
		.spec(get_Resp_Spec_401AuthFail());
	}

}
