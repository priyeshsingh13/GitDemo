package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import Pojo.GetLocation;
import Pojo.PostLocation;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils{
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String place_id;
	
	
	@Given("Add place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		
		//Created response specification
		res = given().spec(requestSpec()).body(data.addPlacePayload(name, language, address));
	}

	@When("user call {string} with {string} http request")
	public void user_call_with_http_request(String resource, String method) {
			//constructor will be called with value of resource which you pass
			APIResources resourceAPI=APIResources.valueOf(resource);
			System.out.println(resourceAPI.getResource());
			resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
			
			if(method.equalsIgnoreCase("POST"))
			{
				System.out.println("POST res : "+res.toString());
				response =res.when().post(resourceAPI.getResource());
			}
			else if(method.equalsIgnoreCase("GET"))
			{
				System.out.println("GET res : "+res.toString());
				response =res.when().get(resourceAPI.getResource());
			}
			else if (method.equalsIgnoreCase("DELETE"))
			{
				System.out.println("DELETE res : "+res.toString());
				response =res.when().delete(resourceAPI.getResource());
			}
					
	}
	
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
	   
		assertEquals( response.getStatusCode(),200);
	    
	}
	@Then("{string} in Response body is {string}")
	public void in_response_body_is(String keyValue, String ExpectedValue) {
		
	    assertEquals(getJsonPath(response, keyValue), ExpectedValue);
	}
	
	@Then("verify the place_id created maps to {string} using {string}")
	public void verify_the_place_id_created_maps_to_using(String ExpectedName, String resource) throws IOException {
		place_id = getJsonPath(response, "place_id");
		res = given().spec(requestSpec()).queryParam("place_id",place_id );
		user_call_with_http_request(resource, "GET"); 
		String actualName = getJsonPath(response, "name");
		assertEquals(actualName, ExpectedName); 
		
		System.out.println(place_id);
	} 
	
	@Given("deletePlace payload")
	public void delete_place_payload() throws IOException {
		
		System.out.println("Delete place given executed");
	    res = given().spec(requestSpec()).body(data.deletePlacePayload(place_id));
		
	}

}


