package org.ernest.geocoding.googleapi.client;


import org.ernest.geocoding.googleapi.client.contracts.GoogleGeocodingContract;

public interface GeocodingApiClient {

    GoogleGeocodingContract getResponse(String address);

}
