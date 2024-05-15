package GETAPIs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GETAPIRequestTestWithoutBDD01 {
	
	RequestSpecification request;
	
	@BeforeTest
	public void setup() {
		// Request Creation Part
		RestAssured.baseURI = "https://gorest.co.in";
		request = RestAssured.given();
		request.header("Authorization", "Bearer d4176ed674fe764cb7b27dbf32d481b4fb0c8b4b0721a2aa12583d0d60c3d26b");
	}

	@Test
	public void getUsersAPITest() {

		// Request Creation Part
//		RestAssured.baseURI = "https://gorest.co.in";
//		RequestSpecification request = RestAssured.given();
//		request.header("Authorization", "Bearer d4176ed674fe764cb7b27dbf32d481b4fb0c8b4b0721a2aa12583d0d60c3d26b");
		Response response = request.get("/public/v2/users");

		// ====================
		// Response Part
		// status code
		int statusCode = response.statusCode();
		System.out.println("status_Code=" + statusCode);

		// verification point
		Assert.assertEquals(statusCode, 200);

		// status mesg
		String ststusMesg = response.statusLine();
		System.out.println(ststusMesg);

		// fetch the response body
		response.prettyPrint();

		// fetch a specfic header
		String content_Type = response.header("Content-Type");
		System.out.println(content_Type);

		// fetch all headers
		List<Header> headersList = response.headers().asList();
		int size=headersList.size();
		System.out.println("==>"+size);
		
		for (Header h : headersList) {
			System.out.println(h.getName() + " : " + h.getValue());
		}

	}

	@Test
	public void getUsersAPIQueryParameterAPITest() {

		// Request Creation Part
//		RestAssured.baseURI = "https://gorest.co.in";
//		RequestSpecification request = RestAssured.given();
		request.queryParam("name","Desai");
		request.queryParam("status","active");
//		request.header("Authorization", "Bearer d4176ed674fe764cb7b27dbf32d481b4fb0c8b4b0721a2aa12583d0d60c3d26b");
		Response response = request.get("/public/v2/users");

		// ====================
		// Response Part
		// status code
		int statusCode = response.statusCode();
		System.out.println("status_Code=" + statusCode);

		// verification point
		Assert.assertEquals(statusCode, 200);

		// status mesg
		String ststusMesg = response.statusLine();
		System.out.println(ststusMesg);

		// fetch the response body
		response.prettyPrint();

	}
	
	@Test
	public void getUsersAPIQueryParameterWithHashMapAPITest() {

		// Request Creation Part
		RestAssured.baseURI = "https://gorest.co.in";
		RequestSpecification request = RestAssured.given();
		Map<String,String> queryParamsMap=new HashMap<String,String>();
		queryParamsMap.put("name", "Desai");
		queryParamsMap.put("gender", "male");
		request.queryParams(queryParamsMap);
//		
		request.header("Authorization", "Bearer d4176ed674fe764cb7b27dbf32d481b4fb0c8b4b0721a2aa12583d0d60c3d26b");
		Response response = request.get("/public/v2/users");

		// ====================
		// Response Part
		// status code
		int statusCode = response.statusCode();
		System.out.println("status_Code=" + statusCode);

		// verification point
		Assert.assertEquals(statusCode, 200);

		// status mesg
		String statusMesg = response.statusLine();
		System.out.println(statusMesg);

		// fetch the response body
		response.prettyPrint();

	}

}
