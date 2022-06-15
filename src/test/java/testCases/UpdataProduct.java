package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.HashMap;

public class UpdataProduct {

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
	public void updateProduct() {
		
		HashMap<String,String> payload = new HashMap<String,String>();
		payload.put("name", "Samsung Galaxy s21-Ultra");
		payload.put("price", "1299");
		payload.put("description", "The best Kind of phone");
		payload.put("category", "2");
		
		
		
		
		
		
		Response response =
				
				given()
					.baseUri("https://techfios.com/api-prod/api/product")
					.headers("Content-Type","application/json; charset=UTF-8")
					.queryParam("id", "2912")
					.body(new File("src\\main\\java\\data\\UpdatePayload.json"))
					//.auth().preemptive().basic("DEFAULT_PATH", "DEFAULT_BODY_ROOT_PATH").
					.header("Authorization", "Bearer &$#*&%$*%(%(^ ").
				when()
					.put("/update.php").
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
		Assert.assertEquals(productMessage, "Product was updated.");
		
		
		
	}
	
}
		

