package restAPIXML;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.internal.path.xml.NodeChildrenImpl;
import io.restassured.response.Response;

public class XMLTest {
	
	@Test
	public void test1() {
		
		RestAssured.given().baseUri("https://chercher.tech/sample/api/books.xml")
		   .when()
		   .get()
		   .then()
		   .log()	  
		   .body()
		   .statusCode(200);
		 
	}
	
	@Test
	public void test2() {
		
		Response response = RestAssured.given().baseUri("https://chercher.tech/sample/api/books.xml")
					.when()
					.get();			
			
		NodeChildrenImpl books = response.then().extract().path("bookstore.book.title");
		System.out.println("All the books are: " + books.toString());
		for (int i = 0; i < books.size(); i++) {
			System.out.println("Book Name " + (i+1) + ": " + books.get(i).toString());
			System.out.println("Language of Book " + (i+1) + ": " + books.get(i).getAttribute("lang"));
		}
		
		NodeChildrenImpl prices = response.then().extract().path("bookstore.book.price");
		System.out.println("Book Price is: " + prices.get(0).children().get("paperback"));
//		for (int i = 0; i < prices.size(); i++) {
//		
//			System.out.println("Book " + (i+1) + " Price is: " + prices.get(i).children().get(i));
//			// need to work on this
//		}		   
		 
	}

}
