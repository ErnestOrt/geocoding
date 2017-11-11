package org.ernest.geocoding.googleapi.client;

import com.google.gson.Gson;
import org.ernest.geocoding.googleapi.client.contracts.GoogleGeocodingContract;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeocodingApiClientImpl implements GeocodingApiClient{

    @Value("${geolocation.api}")
    public String apiKey;

    @Override
    public GoogleGeocodingContract getResponse(String address) {
        String content = new RestTemplate().getForObject("https://maps.googleapis.com/maps/api/geocode/json?address="+address.replaceAll(" ", "+")+"&key=" + apiKey, String.class);
        return new Gson().fromJson(content, GoogleGeocodingContract.class);
    }
}
