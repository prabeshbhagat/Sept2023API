package GETAPIs;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetAPIsWithPathParams02 {
	
	@Test
	public void getCircuitDatWithPathParamYearTest02() {
		
		RestAssured.baseURI="https://ergast.com";
		given().log().all()
		.pathParam("year", "2017")
			.when().log().all()
				.get("api/f1/{year}/circuits.json")
					.then()
						.assertThat()
							.statusCode(200)
								.and()
									.body("MRData.CircuitTable.season", equalTo("2017"))
										.and()
											.body("MRData.CircuitTable.Circuits", hasSize(20));	
	}



	@DataProvider
	public Object[][] getCircuitYearData() {
		return new Object[][] {
			{"2019",21},
			{"1996",16},
			{"2023",22},
			{"2000",17}
		};
	}
	
	
	
	@Test(dataProvider="getCircuitYearData")
	public void getCircuitDatWithPathParam_Year_DataProviderTest(String seasonYear,int totalCircuits) {
	
	RestAssured.baseURI="https://ergast.com";
	given().log().all()
	.pathParam("year", seasonYear)
		.when().log().all()
			.get("api/f1/{year}/circuits.json")
				.then()
					.assertThat()
						.statusCode(200)
							.and()
								.body("MRData.CircuitTable.season", equalTo(seasonYear))
									.and()
										.body("MRData.CircuitTable.Circuits", hasSize(totalCircuits));	
}
	
	
	@Test(dataProvider="getCircuitYearData")
	public void getAllCircuitIdsDataWithPathParam_Year_DataProviderTest(String seasonYear,int totalCircuits) {
	
	RestAssured.baseURI="https://ergast.com";
	Response response=given().log().all()
	.pathParam("year", seasonYear)
		.when().log().all()
			.get("api/f1/{year}/circuits.json");
	
		JsonPath js	=response.jsonPath();
		List<String> circuitIds= js.getList("MRData.CircuitTable.Circuits.circuitId");
		
		for(int i=0;i<circuitIds.size();i++) {
			System.out.println("circuitIds---"+"i="+i+circuitIds.get(i));
		}
		System.out.println("seasonYear================="+seasonYear);
}
	
	
	
	
	
	
	
	
	
	
	
}
