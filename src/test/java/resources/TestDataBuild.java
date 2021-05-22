package resources;

import java.util.ArrayList;
import java.util.List;

import Pojo.GetLocation;
import Pojo.PostLocation;

public class TestDataBuild {

	
	public PostLocation addPlacePayload (String name, String language, String address)
	{
		PostLocation pl = new PostLocation();
		pl.setAccuracy(50);
		pl.setAddress(address);
		pl.setLanguage(language);
		pl.setPhone_number("(+91) 983 893 3937");
		pl.setWebsite("http://google.com");
		pl.setName(name);
		
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		pl.setTypes(myList);
		
		GetLocation l = new GetLocation();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		pl.setLocation(l);
		
		return pl;
	}
	
	public String deletePlacePayload(String placeid)
	{
		return "{\r\n    \"place_id\":\""+placeid+"\"\r\n}";
	}
}
