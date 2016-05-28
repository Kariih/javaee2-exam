package javaBeans;

import java.util.List;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

import utils.CountryRestClient;

@Stateless
public class CountryEjb {

	public List<String> getCountries() {
		return new CountryRestClient().createCountryList();

	}
}
