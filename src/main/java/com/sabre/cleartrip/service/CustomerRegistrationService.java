package com.sabre.cleartrip.service;

import com.sabre.cleartrip.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class CustomerRegistrationService {
    private final RestTemplate restTemplate;

    @Autowired
    public CustomerRegistrationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String registerTheCustomer(Customer customer) {


        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/getCustomer")
                .queryParam("id", customer.getId());

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<Customer> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                Customer.class);

        if(response.getBody() != null) {
            return "Customer Already Registered";
        } else {
            HttpEntity<Customer> request = new HttpEntity<>(customer, headers);

            ResponseEntity<Customer> customerResponseEntity =
                    restTemplate.exchange("http://localhost:8080/addCustomer", HttpMethod.POST, request, Customer.class);

            if(customerResponseEntity.getStatusCode().equals(HttpStatus.OK)) {
                return "Customer Successfully Registered";
            } else {
                return "Customer registration failed";
            }
        }

    }
}
