package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.File;

public class DeleteProduct {


	@Test(priority=1)
	public void deleteAProduct() {
		
		Response response =
				
				given()
					.baseUri("https://techfios.com/api-prod/api/product")
					.headers("Content-Type","application/json; charset=UTF-8")
					.body(new File("src\\main\\java\\data\\DeletePayload.json"))
					//.auth().preemptive().basic("DEFAULT_PATH", "DEFAULT_BODY_ROOT_PATH").
					.header("Authorization", "Bearer &$#*&%$*%(%(^ ").
				when()
					.delete("/delete.php").
				then()
					.extract().response();
		
		int actualStatusCode = response.getStatusCode();
		System.out.println("Actual Status Code:" + actualStatusCode);
		Assert.assertEquals(actualStatusCode,200 );
		
		String actualHeader = response.getHeader("Content-Type");
		System.out.println("ActualHeader:"+actualHeader);
		Assert.assertEquals(actualHeader,"application/json; charset=UTF-8" );
		
		String body=response.getBody().asString();
		System.out.println(body);
		
		JsonPath jp = new JsonPath(body);
		
		String productMessage =jp.get("message");
		System.out.println("Product Message:"+productMessage);
		Assert.assertEquals(productMessage, "Product was deleted.");
	}
	
		@Test(priority=2)
		public void readOneProduct() {
		
		Response response =
				
				given()
					.baseUri("https://techfios.com/api-prod/api/product")
					.headers("Content-Type","application/json; charset=UTF-8")
					.queryParam("id", "2867")
					//.auth().preemptive().basic("DEFAULT_PATH", "DEFAULT_BODY_ROOT_PATH").
					.header("Authorization", "Bearer &$#*&%$*%(%(^ ").
				when()
					.get("/read_one.php").
				then()
				.extract().response();
		
		int actualStatusCode = response.getStatusCode();
		System.out.println("Actual Status Code:" + actualStatusCode);
		Assert.assertEquals(actualStatusCode,404);
		
		String actualHeader = response.getHeader("Content-Type");
		System.out.println("ActualHeader:"+actualHeader);
		Assert.assertEquals(actualHeader,"application/json" );
		
		String body=response.getBody().asString();
		System.out.println(body);
		
		JsonPath jp = new JsonPath(body);
		
		String productMessage =jp.get("message");
		System.out.println("Product Message:"+productMessage);
		Assert.assertEquals(productMessage, "Product does not exist.");
	}
	
	
	
	
}
		

