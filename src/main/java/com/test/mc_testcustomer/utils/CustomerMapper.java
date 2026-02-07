package com.test.mc_testcustomer.utils;

import org.springframework.stereotype.Component;

import com.test.mc_testcustomer.model.Customer;
import com.test.mc_testcustomer.model.CustomerRequest;
import com.test.mc_testcustomer.model.CustomerResponse;

@Component
public class CustomerMapper {
    
    public Customer toCustomerFromRequest(CustomerRequest customerRequest) {
        return Customer.builder()
            .name(customerRequest.name())
            .lastname(customerRequest.lastname())
            .age(customerRequest.age())
            .username(customerRequest.username())
            .build();
    }

    public CustomerResponse toResponseFromCustomer(Customer customer) {
        return new CustomerResponse(
            customer.getName(),
            customer.getLastname(),
            customer.getUsername()
        );
    }

}
