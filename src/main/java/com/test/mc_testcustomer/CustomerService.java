package com.test.mc_testcustomer;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.test.mc_testcustomer.model.Customer;
import com.test.mc_testcustomer.model.CustomerRequest;
import com.test.mc_testcustomer.model.CustomerResponse;
import com.test.mc_testcustomer.utils.CustomerMapper;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public Mono<Customer> getCustomer(String customerId) {
        return repository.findById(UUID.fromString(customerId));
    }

    public Mono<CustomerResponse> createCustomer(CustomerRequest customerRequest) {
        return repository
            .save(mapper.toCustomerFromRequest(customerRequest))
            .map(mapper::toResponseFromCustomer);
    }

    public Flux<Customer> getAllCustomers() {
        return repository.findAll();
    }
    
}
