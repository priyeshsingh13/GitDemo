package stepDefinations;

import java.io.IOException;

import io.cucumber.core.gherkin.Step;
import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void BeforeScneario() throws IOException
	{
		StepDefination sd = new StepDefination();
		
		if(StepDefination.place_id ==null)
		{
			sd.add_place_payload_with("Priyesh", "Hindi", "Korba");
			sd.user_call_with_http_request("AddPlaceAPI", "POST");
			sd.verify_the_place_id_created_maps_to_using("Priyesh", "GetPlaceAPI");
		}
	}

}
