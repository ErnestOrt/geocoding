package org.ernest.geocoding.repository;


import org.ernest.geocoding.domain.Address;

import java.util.Collection;
import java.util.List;

public interface AddressesRepository {

    void addAddresses(List<String> addressesList);
    void addAddress(String addressName);
    Collection<Address> getAllAddress();
}
