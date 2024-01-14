Feature: Validate Place API 


@AddPlace @Regression
Scenario Outline: Verify if Place is added successfully using Add Place API 

	Given Add Place Payload with name "<name>" "<address>" "<language>"
	When user calls "AddPlaceAPI" with "POST" HTTP request 
	Then API call is success with status code "200" 
	And "status" in response body is "OK" 
	And verify place_id created maps to "<name>" using "GetPlaceAPI"
	
	Examples: 
		|name|address|language|
		|Sachin|JP Nagar BNG|EN|
		|Rahul|JayaNagar BNG|KA|
		
@DeletePlace @Regression	
Scenario: Verify Delete API is working

Given Delete Place payload
When user calls "DeletePlaceAPI" with "POST" HTTP request 
Then API call is success with status code "200" 
And "status" in response body is "OK" 
