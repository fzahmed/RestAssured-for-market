package testCases;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateAProduct {

	SoftAssert softAssert = new SoftAssert();
	
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
////		{
//    "name": "Samsung Galaxy s21-Ultra",
//    "price": "1299",
//    "description": "The best Kind of phone.",
//    "category_id": 2
//}
//	}
	@Test
	public void CreatAProduct() {
		
		HashMap<String,String> payload = new HashMap<String,String>();
		payload.put("name", "Samsung Galaxy s21-Ultra");
		payload.put("price", "1299");
		payload.put("description", "The best Kind of phone");
		payload.put("category", "2");
		
		
		
		Response response =
				
				given()
					.baseUri("https://techfios.com/api-prod/api/product")
					.headers("Content-Type","application/json; charset=UTF-8")
					.body(payload)
					//.body(new File("src\\main\\java\\data\\Creatpayload.json"))
					//.queryParam("id", "2912")
					//.auth().preemptive().basic("DEFAULT_PATH", "DEFAULT_BODY_ROOT_PATH").
					.header("Authorization", "Bearer &$#*&%$*%(%(^ ").
				when()
					.post("/create.php").
				then()
					.extract().response();
		
		
		int actualStatusCode = response.getStatusCode();
		System.out.println("Actual Status Code:" + actualStatusCode);
		softAssert.assertEquals(actualStatusCode,201 );
		
		String actualHeader = response.getHeader("Content-Type");
		System.out.println("ActualHeader:"+actualHeader);
		softAssert.assertEquals(actualHeader,"application/json; charset=UTF-8" );
		
		String body=response.getBody().asString();
		System.out.println(body);
		
		JsonPath jp = new JsonPath(body);
		
		String productMessage =jp.get("message");
		System.out.println("Product Message:"+productMessage);
		softAssert.assertEquals(productMessage, "Product was created.");
		
		softAssert.assertAll();
	}
	
}
		

