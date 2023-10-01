package specificationconcept03;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class ResponseSpecBuilderTest03 {
	
	//use this method for response check
	public static ResponseSpecification get_Resp_Spec_200Ok() {
		
		ResponseSpecification resp_spec_200Ok= new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectHeader("Server", "cloudflare")
		.expectStatusCode(200)
		.build();
		
		return resp_spec_200Ok;	
	}
	
	public static ResponseSpecification get_Resp_Spec_200Ok_With_Body() {
		
		ResponseSpecification resp_spec_200_Ok= new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectHeader("Server","cloudflare")
		.expectStatusCode(200)
		.expectBody("$.size()", equalTo(10) )
		.expectBody("id", hasSize(10))
		.build();
		
		return resp_spec_200_Ok;
	}
	
public static ResponseSpecification get_Resp_Spec_401AuthFail() {
		
		ResponseSpecification resp_spec_401Authfail= new ResponseSpecBuilder()
		.expectHeader("Server", "cloudflare")
		.expectStatusCode(401)
		.build();
		
		return resp_spec_401Authfail;	
	}
	
	@Test
	public void get_User_Resp_200_spec_test(){
		
		RestAssured.baseURI="https://gorest.co.in";
		
		given().log().all()
		.header("Authorization", "Bearer d4176ed674fe764cb7b27dbf32d481b4fb0c8b4b0721a2aa12583d0d60c3d26b")
		.when().log().all()
		.get("/public/v2/users")
		.then()
		.assertThat()
		.spec(get_Resp_Spec_200Ok_With_Body());
	}
	
	@Test
	public void get_User_Resp_401_AuthFail_spec_test(){
		
		RestAssured.baseURI="https://gorest.co.in";
		
		given().log().all()
		.header("Authorization", "Bearer d4176ed674fe764cb7b27dbf32d481b4fb0c8b4b0721a2aa12583d0d60c3d26b9999")
		.when().log().all()
		.get("/public/v2/users")
		.then().log().all()
		.assertThat()
		.spec(get_Resp_Spec_401AuthFail());
	}
	
	
	

}
