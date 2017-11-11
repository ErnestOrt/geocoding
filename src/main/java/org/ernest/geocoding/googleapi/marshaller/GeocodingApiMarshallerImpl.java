package org.ernest.geocoding.googleapi.marshaller;

import org.ernest.geocoding.domain.Address;
import org.ernest.geocoding.googleapi.client.GeocodingApiClient;
import org.ernest.geocoding.googleapi.client.contracts.GoogleGeocodingContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GeocodingApiMarshallerImpl implements GeocodingApiMarshaller{

    @Autowired
    private GeocodingApiClient geocodingApiClient;

    @Override
    public Address getAddress(String addressName) {
        GoogleGeocodingContract googleGeocodingContract = geocodingApiClient.getResponse(addressName);

        Address address = new Address();
        address.setId(UUID.randomUUID().toString());
        address.setName(addressName);
        address.setLatitude(googleGeocodingContract.getResults().get(0).getGeometry().getLocation().getLat());
        address.setLongitude(googleGeocodingContract.getResults().get(0).getGeometry().getLocation().getLng());
        return address;
    }
}
