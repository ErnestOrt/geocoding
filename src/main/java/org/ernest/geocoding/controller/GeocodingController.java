package org.ernest.geocoding.controller;

import org.ernest.geocoding.domain.Address;
import org.ernest.geocoding.parser.FileParser;
import org.ernest.geocoding.repository.AddressesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class GeocodingController {

    @Autowired
    AddressesRepository addressesRepository;

    @Autowired
    FileParser fileParser;

    @PostMapping("/store/address")
    public void storeAddress(@RequestBody String address){
        addressesRepository.addAddress(address);
    }

    @PostMapping("/store/addresses")
    public void storeAddresses(@RequestBody byte[] addressFileContent){
        addressesRepository.addAddresses(fileParser.parse(addressFileContent));
    }

    @GetMapping("/get/addresses/all")
    public Collection<Address> getAllAddress(){
        return addressesRepository.getAllAddress();
    }
}
