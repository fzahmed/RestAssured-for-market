package testCases;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReadAllProducts {

	//@Test
	//public void readAllProduct() {
		//https://techfios.com/api-prod/api/product/read.php
//		given: all input details(base URI,Headers,Payload/Body,QueryParameters,Authentication)
//		when:  submit api requests(Http method,Endpoint/Resource)
//		then:  validate response(status code, Headers, responseTime, Payload/Body)

//		Response responce =
//		
//		given()
//			.baseUri("https://techfios.com/api-prod/api/product")
//			.headers("Content-Type","application/json; charset=UTF-8").
//		when()
//			.get("/read.php").
//		then()
//		.extract().response();
////			.statusCode(200)
////			.header("Content-Type", "application/json; charset=UTF-8");
//		
//		int statusCode =responce.getStatusCode();
//		System.out.println("Actual status code: "+statusCode);
//		Assert.assertEquals(statusCode,200);
//		
//		String actualHeader = responce.getHeader("Content-Type");
//		
//		Assert.assertEquals(actualHeader, "application/json; charset=UTF-8");
//		
//	}
	@Test
	public void readOneProduct() {
		
		Response responce =
				
				given()
					.baseUri("https://techfios.com/api-prod/api/product")
					.headers("Content-Type","application/json; charset=UTF-8")
					.queryParam("id", "2912")
					//.auth().preemptive().basic("DEFAULT_PATH", "DEFAULT_BODY_ROOT_PATH").
					.header("Authorization", "Bearer &$#*&%$*%(%(^ ").
				when()
					.get("/read_one.php").
				then()
				.extract().response();
		
		String body=responce.getBody().asString();
		
		System.out.println(body);
		
		JsonPath jp = new JsonPath(body);
		
		String productId =jp.get("id");
		
		System.out.println(jp.prettyPrint());
		
		System.out.println(productId);
	}
	
}
		

