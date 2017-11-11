package org.ernest.geocoding.repository;


import org.ernest.geocoding.domain.Address;
import org.ernest.geocoding.googleapi.marshaller.GeocodingApiMarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AddressesRepositoryImpl implements AddressesRepository {

    Logger logger = LoggerFactory.getLogger(AddressesRepositoryImpl.class);

    private Map<String, Address> addressesMap = new HashMap<>();

    @Autowired
    private GeocodingApiMarshaller geocodingApiMarshaller;

    public void addAddresses(List<String> addressesList){
        addressesList.parallelStream().forEach(a -> addAddress(a));
    }

    public void addAddress(String addressName){
        try {
            Address address = geocodingApiMarshaller.getAddress(addressName);
            addressesMap.put(address.getId(), address);
        }catch (Exception e){
            logger.error("Could not add address:["+addressName+"]");
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Address> getAllAddress() {
        return addressesMap.values();
    }
}
