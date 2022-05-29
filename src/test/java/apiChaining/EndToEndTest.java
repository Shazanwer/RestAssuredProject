package apiChaining;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EndToEndTest {

	Response response;
	String BaseURI = "http://localhost:3000/employees";

	@Test
	public void test1() {

		response = GetMethodAll();
		Assert.assertEquals(response.getStatusCode(), 200);

		response = PostMethod("Eclipse", "5000");
		Assert.assertEquals(response.getStatusCode(), 201);
		JsonPath jspath = response.jsonPath();
		int EMP_ID = jspath.get("id");
		System.out.println("New EMP ID is: " + EMP_ID);

		response = PutMethod("Eclipse-Updated", "7000", EMP_ID);
		Assert.assertEquals(response.getStatusCode(), 200);
		jspath = response.jsonPath();
		Assert.assertEquals(jspath.get("name"), "Eclipse-Updated");

		response = DeleteMethod(EMP_ID);
		Assert.assertEquals(response.getStatusCode(), 200);		
		Assert.assertEquals(response.getBody().asString(), "{}");

		response = GetMethod(EMP_ID);
		Assert.assertEquals(response.getStatusCode(), 404);		
		Assert.assertEquals(response.getBody().asString(), "{}");

	}

	public Response GetMethodAll() {
		RestAssured.baseURI = BaseURI;
		RequestSpecification request = RestAssured.given();
		Response response = request.get();
		return response;
	}
	
	public Response GetMethod(int EMP_ID) {
		RestAssured.baseURI = BaseURI;
		RequestSpecification request = RestAssured.given();
		Response response = request.get("/" + EMP_ID);
		return response;
	}

	public Response PostMethod(String Name, String Salary) {

		Map<String, Object> MapObj = new HashMap<String, Object>();
		MapObj.put("name", Name);
		MapObj.put("salary", Salary);

		RestAssured.baseURI = BaseURI;
		RequestSpecification request = RestAssured.given();
		Response response = request.contentType(ContentType.JSON).accept(ContentType.JSON).body(MapObj).post("/create");
		return response;
	}

	public Response PutMethod(String Name, String Salary, int EMP_ID) {

		Map<String, Object> MapObj = new HashMap<String, Object>();
		MapObj.put("name", Name);
		MapObj.put("salary", Salary);

		RestAssured.baseURI = BaseURI;
		RequestSpecification request = RestAssured.given();
		Response response = request.contentType(ContentType.JSON).accept(ContentType.JSON).body(MapObj)
				.put("/" + EMP_ID);
		return response;
	}

	public Response DeleteMethod(int EMP_ID) {

		RestAssured.baseURI = BaseURI;
		RequestSpecification request = RestAssured.given();
		Response response = request.delete("/" + EMP_ID);

		return response;
	}

}
