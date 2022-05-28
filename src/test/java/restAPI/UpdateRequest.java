package restAPI;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UpdateRequest {
	@Test
	public void test1() {
		RestAssured.baseURI = "http://localhost:3000/employees";
		RequestSpecification request = RestAssured.given();
		Response response = request.contentType(ContentType.JSON).accept(ContentType.JSON)
				.body("{\r\n"
						+ "    \"name\": \"Eclipse-Updated\",\r\n"
						+ "    \"salary\": \"10500\"\r\n"
						+ "}")
				.put("/7");		
		String ResponseBody = response.getBody().asString();
		System.out.println(ResponseBody);
		int ResponseCode = response.getStatusCode();
		Assert.assertEquals(ResponseCode, 200);
		
		
	}

}
