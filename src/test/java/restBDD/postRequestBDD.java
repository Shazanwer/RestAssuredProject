package restBDD;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class postRequestBDD {
	
	@Test
	public void test1() {		
		RestAssured.given().baseUri("http://localhost:3000/employees")						   						
						   .contentType(ContentType.JSON)
						   .accept(ContentType.JSON)
						   .when()
						   .body("{\r\n" + "    \"name\": \"Eclipse-BDD\",\r\n" + "    \"salary\": \"10000\"\r\n" + "}")						   
						   .post("/create")
						   .then()
						   .log()
						   .body()
						   .statusCode(201)
						   .body("name",Matchers.equalTo("Eclipse-BDD"));
		
	}
	
	@Test
	public void test2() {	
		Map<String, Object> MapObj = new HashMap<String, Object>();		
		MapObj.put("name", "Eclipse-BDD");
		MapObj.put("salary", "5000");
		RestAssured.given().baseUri("http://localhost:3000/employees")						   						
						   .contentType(ContentType.JSON)
						   .accept(ContentType.JSON)
//						   .when()
						   .body(MapObj)						   
						   .post("/create")
						   .then()
						   .log()
						   .body()
						   .statusCode(201);
		
	}

}
