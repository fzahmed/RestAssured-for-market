package testCases;

import static io.restassured.RestAssured.given;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReadAProduct {
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
