# Geocoding

### How do I make it work?

1. Set up your API key on the _geolocation.api_ property from application.properties
3. You can start the project using _mvn spring-boot:run_

### Available methods

```
    @PostMapping("/store/address")
    public void storeAddress(@RequestBody String address)

    @PostMapping("/store/addresses")
    public void storeAddresses(@RequestBody byte[] addressesFileContent)

    @GetMapping("/get/addresses/all")
    public Collection<Address> getAllAddresses()
    
```