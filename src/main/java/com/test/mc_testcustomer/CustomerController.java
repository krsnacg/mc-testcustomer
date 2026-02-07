package com.test.mc_testcustomer;

import org.springframework.web.bind.annotation.RestController;

import com.test.mc_testcustomer.model.Customer;
import com.test.mc_testcustomer.model.CustomerRequest;
import com.test.mc_testcustomer.model.CustomerResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/customer", version = "1")
public class CustomerController {

    private final CustomerService service;

    @GetMapping("/{customer-id}")
    public Mono<Customer> getCustomer(@PathVariable(name = "customer-id") String customerId) {
        return service.getCustomer(customerId);
    }

    @GetMapping("/all")
    public Flux<Customer> getMethodName() {
        return service.getAllCustomers();
    }
    
    
    @PostMapping("/")
    public Mono<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        return service.createCustomer(customerRequest);
    }
    
    
}
