package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		// Execute this code only when place id is null
		// Write code that will give you place id
		
		if(AddPlaceStepDefinition.placeId == null) {
			
			System.out.println("--- Executing Before Hooks scenario --- ");
		
		AddPlaceStepDefinition step = new AddPlaceStepDefinition();
		step.add_place_payload("Sachin", "USA", "EN");
		step.user_calls_with_post_http_request("AddPlaceAPI", "POST");
		step.verify_place_id_created_maps_to_using("Sachin", "GetPlaceAPI");
		
		}
		
	}

}
