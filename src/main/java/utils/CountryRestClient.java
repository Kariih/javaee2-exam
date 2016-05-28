package utils;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class CountryRestClient {
	
	List<String> countries = new ArrayList<String>();
	
	private String downloadCountries(){
		URI uri = UriBuilder.fromUri("http://restcountries.eu/rest/v1/all").port(80).build();

		Client client = ClientBuilder.newClient();
        Response response = client.target(uri).request("application/json").get(); 
        
        return response.readEntity(String.class);  
	}
	
	public List<String> createCountryList(){
        JsonParser parser = new JsonParser();
        JsonArray json =(JsonArray) parser.parse(downloadCountries());
        for (JsonElement element : json) {
        	JsonElement country = element.getAsJsonObject().get("name");
        	countries.add(country.toString().replace("\"", ""));
		} 
        
		return !countries.isEmpty() ? countries : null;
	}

}
