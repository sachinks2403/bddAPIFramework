package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDatabuild {

	public AddPlace addPlacePayload(String name, String address, String lang) {
		
		Location l = new Location();

		l.setLat(1234.5678);
		l.setLng(988.9867);

		List<String> typesList = new ArrayList();
		typesList.add("shop");
		typesList.add("shoe park");

		AddPlace p = new AddPlace();

		p.setLocation(l);
		p.setAccuracy(12);
		p.setName(name);
		p.setPhone_number("+91-9645489076");
		p.setAddress(address);
		p.setLanguage(lang);
		p.setWebsite("http://automationParctice.com");
		p.setTypes(typesList);
		return p;
	}
	
	public String deletePayload(String placeId) {
		return "{\r\n" + 
				"    \"place_id\" : \"" + placeId + "\"\r\n" + 
				"}";
	}

}
