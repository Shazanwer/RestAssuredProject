package restAPI;

import java.util.List;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequest {

	@Test
	public void test1() {
		RestAssured.baseURI = "http://localhost:3000/employees";
		RequestSpecification request = RestAssured.given();
		Response response = request.get();
		String ResponseBody = response.getBody().asString();
		System.out.println(ResponseBody);

		//Print all the names
		JsonPath jspath = response.jsonPath();
		List<String> names = jspath.get("name");
		for (int count = 0; count < names.size(); count++) {
			System.out.println(names.get(count));
		}

	}
}
