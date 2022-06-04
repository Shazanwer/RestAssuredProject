package restBDD;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class putRequestBDD {
	
	@Test
	public void test1() {		
		RestAssured.given().baseUri("http://localhost:3000/employees")						   						
						   .contentType(ContentType.JSON)
						   .accept(ContentType.JSON)
						   .when()
						   .body("{\r\n" + "    \"name\": \"Eclipse-PUT-BDD\",\r\n" + "    \"salary\": \"10000\"\r\n" + "}")						   
						   .put("/16")
						   .then()
						   .log()
						   .body()
						   .statusCode(200)
						   .body("name",Matchers.equalTo("Eclipse-PUT-BDD"));
		
	}

}
