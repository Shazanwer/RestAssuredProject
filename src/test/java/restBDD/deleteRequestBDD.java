package restBDD;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class deleteRequestBDD {
	
	@Test
	public void test1() {		
		RestAssured.given().baseUri("http://localhost:3000/employees")						  						   
						   .delete("/11")
						   .then()
						   .log()
						   .body()
						   .statusCode(200);		
	}
}
