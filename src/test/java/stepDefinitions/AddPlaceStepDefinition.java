package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.IOException;



import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDatabuild;
import resources.Utils;

public class AddPlaceStepDefinition extends Utils{
	
	
	ResponseSpecification res;
	RequestSpecification req1;
	Response apiResp;
	TestDatabuild testdata = new TestDatabuild();
	JsonPath js;
	static String placeId;
	
	
	@Given("Add Place Payload with name {string} {string} {string}")
	public void add_place_payload(String name, String address, String lang) throws IOException {
		
		req1 = given().spec(requestSpecification()).body(testdata.addPlacePayload(name,address,lang));
		
		
	}
	@When("user calls {string} with {string} HTTP request")
	public void user_calls_with_post_http_request(String apiName,String httpMethod) {
		
	res = new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON)
				.build();
	
		String resourceName = APIResources.valueOf(apiName).getResource();
		
		if(httpMethod.equalsIgnoreCase("POST"))
		apiResp = req1.when().post(resourceName);
		else if(httpMethod.equalsIgnoreCase("GET"))
			apiResp = req1.when().get(resourceName);
		else if(httpMethod.equalsIgnoreCase("PUT"))
			apiResp = req1.when().put(resourceName);
		else if(httpMethod.equalsIgnoreCase("DELETE"))
			apiResp = req1.when().delete(resourceName);
		
	}
	@Then("API call is success with status code {string}")
	public void api_call_is_success_with_status_code(String statusCode) {
		int actualStatus = apiResp.getStatusCode();
		assertEquals(String.valueOf(actualStatus), statusCode);
		
		
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
	   String resp = apiResp.asString();
	   js = new JsonPath(resp);
	   String actualValue = js.getString(key);
	   assertEquals(actualValue, value);
	   
	}
	
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
	   
		
		placeId = getJsonResponseValue(apiResp, "place_id");
		// placeId = js.getString("place_id");
		req1 = given().spec(requestSpecification()).queryParam("place_id", placeId);
		user_calls_with_post_http_request(resource,"GET");
		
		 String resp = apiResp.asString();
		  js = new JsonPath(resp);
		  String actualValue = js.getString("name");
		  assertEquals(actualValue, expectedName);
		
	}
	
	
	@Given("Delete Place payload")
	public void delete_place_payload() throws IOException {
	    req1 = given().spec(requestSpecification()).body(testdata.deletePayload(placeId));
	}


}
