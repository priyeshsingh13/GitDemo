Feature: Validation of place API

@AddPlace @Regression
Scenario Outline: Verify if the place is begin successfully added using AddPlaceAPI
	Given Add place Payload with "<name>" "<language>" "<address>"
	When user call "AddPlaceAPI" with "POST" http request
	Then the API call got success with status code 200
	And "status" in Response body is "OK"
	And "scope" in Response body is "APP"
	And verify the place_id created maps to "<name>" using "GetPlaceAPI"
	
	
Examples:
	|name  		|language | address 			|
	|Zenone		|English  | Pune, 411057		|
#	|Sanskriti	|Hindi	  | Korba, 495682		|	


@DeletePlace @Regression
Scenario: Verify if Delete place API is working
	Given deletePlace payload
	When user call "deletePlaceAPI" with "DELETE" http request
	Then the API call got success with status code 200
	And "status" in Response body is "OK"