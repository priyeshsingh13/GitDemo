package resources;

//enum is special call of java which has constant or methods

public enum APIResources {
	
	AddPlaceAPI ("/maps/api/place/add/json"),
	GetPlaceAPI ("/maps/api/place/get/json"),
	deletePlaceAPI ("maps/api/place/delete/json");
	
	private String resource;
	
	APIResources (String resource)
	{
		this.resource = resource;
	}

	public String getResource() {
		return resource;
	}
}
