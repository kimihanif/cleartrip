package com.sabre.cleartrip.controller;

import com.sabre.cleartrip.domain.Customer;
import com.sabre.cleartrip.service.CustomerRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClearTripController {

    private final CustomerRegistrationService customerRegistrationService;

    @Autowired
    public ClearTripController(CustomerRegistrationService customerRegistrationService) {
        this.customerRegistrationService = customerRegistrationService;
    }

    @PostMapping("/registerCustomer")
    public String regCustomer(@RequestBody Customer customer) {
        return customerRegistrationService.registerTheCustomer(customer);
    }
}
