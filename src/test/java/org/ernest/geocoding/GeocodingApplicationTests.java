package org.ernest.geocoding;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.ernest.geocoding.domain.Address;
import org.ernest.geocoding.googleapi.client.GeocodingApiClient;
import org.ernest.geocoding.googleapi.client.contracts.GoogleGeocodingContract;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GeocodingApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	private GeocodingApiClient geocodingApiClient;

	@Test
	public void givenSeveralAddressesWhenRequestingThemThenAddressesAreStoredAndRetrieved() throws Exception {
		when(geocodingApiClient.getResponse(anyString())).thenReturn(getGoogleApiResponse());

		String requestedAddress = "Angola";

		restTemplate.postForLocation("http://localhost:" + port + "/store/address", requestedAddress);
		List<Address> addresses = Arrays.asList(this.restTemplate.getForObject("http://localhost:" + port + "/get/addresses/all", Address[].class));

		assertEquals(1, addresses.size());

		Address address = addresses.get(0);
		assertNotNull(address.getId());
		assertEquals(requestedAddress, address.getName());
		assertEquals(new Double(37.4216548), new Double(address.getLatitude()));
		assertEquals(new Double(-122.0856374), new Double(address.getLongitude()));

		restTemplate.postForLocation("http://localhost:" + port + "/store/addresses", IOUtils.toString(getClass().getClassLoader().getResourceAsStream("input_addresses.txt")).getBytes());
		addresses = Arrays.asList(this.restTemplate.getForObject("http://localhost:" + port + "/get/addresses/all", Address[].class));

		assertEquals(14, addresses.size());
	}

	private GoogleGeocodingContract getGoogleApiResponse() throws IOException {
		return new Gson().fromJson(IOUtils.toString(getClass().getClassLoader().getResourceAsStream("google_api_response.txt")), GoogleGeocodingContract.class);
	}
}