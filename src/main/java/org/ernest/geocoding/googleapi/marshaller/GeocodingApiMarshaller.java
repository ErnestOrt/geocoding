package org.ernest.geocoding.googleapi.marshaller;


import org.ernest.geocoding.domain.Address;

public interface GeocodingApiMarshaller {

    Address getAddress(String address);
}
