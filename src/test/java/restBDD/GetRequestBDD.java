package restBDD;

import java.util.List;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetRequestBDD {
	
	@Test
	public void test1() {
		
		RestAssured.given().baseUri("http://localhost:3000/employees")
						   .when()
						   .get()
						   .then()
						   .log()
//						   .status()
//						   .statusCode(200);
						   .body()
						   .statusCode(200);
//						   .all();	
		
	}
	
	@Test
	public void test2() {
		
		RestAssured.given().baseUri("http://localhost:3000/employees")
//						   .queryParam("name", "Pankaj-Updated")
						   .when()
						   .get()
						   .then()
						   .log()
						   .body()
						   .statusCode(200)
						   .body("[4].name",Matchers.equalTo("Eclipse"));
		
	}
	
	@Test
	public void test3() {
		
		Response response = RestAssured.given()
										.baseUri("http://localhost:3000/employees")
//										.queryParam("id", "1")
										.when()
										.get();
		
		JsonPath jpath = response.jsonPath();
		List<String> names = jpath.get("name");
		
		System.out.println(names.get(4));
		Assert.assertEquals(names.get(4), "Eclipse");
		
		String Header = response.getHeader("Content-Type");
		System.out.println(Header);
		
	}

	


}
