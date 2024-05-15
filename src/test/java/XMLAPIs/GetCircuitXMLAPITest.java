package XMLAPIs;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.config.XmlPathConfig;
import io.restassured.response.Response;

public class GetCircuitXMLAPITest {
		
	@Test
	public void xmLTest() {
		
		RestAssured.baseURI="https://ergast.com";
		
		Response response= RestAssured.given()
		.when()
		.get("/api/f1/2017/circuits.xml");
		
		String responseBody=response.body().asString();
		
		XmlPath xmlpath= new XmlPath(responseBody);
		List<String> circuitName=xmlpath.getList("MRData.CircuitTable.Circuit.CircuitName");
		for(String e:circuitName) {
			System.out.println(e);
		}
		
		List<String> circuitID =xmlpath.getList("MRData.CircuitTable.Circuit.@circuitId");
		for(String e:circuitID) {
			System.out.println(e);
		}
		
		System.out.println("================================");
		//fetch the locality where circuitid =americas
		String locality=xmlpath.get("**.findAll {it.@circuitId=='americas'}.Location.Locality").toString();
		System.out.println(locality);
		
		System.out.println("================================");
		//fetch the latitude & logitude values
		String latVal= xmlpath.get("**.findAll{it.@circuitId=='americas'}.Location.@lat").toString();
		String longvalue=xmlpath.get("**.findAll{it.@circuitId=='americas'}.Location.@long").toString();
		System.out.println(latVal);
		System.out.println(longvalue);
		
		
		//fetch the locality where circuitid =americas or circuitid =baharin 
		String locality1=xmlpath.get("**.findAll{it.@circuitId=='americas' || it.@circuitId=='albert_park'}.Location.Locality").toString();
		System.out.println(locality1);
		
		
		
		
		
		
	}

}
