package com.test.mc_testcustomer;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.test.mc_testcustomer.model.Customer;

import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class CustomerRepositoryTest {

    CustomerRepository repository = new CustomerRepository();
    
    @Test
    void save_ShouldReturnCustomerWithId() {
        Customer customer = Customer.builder()
            .name("mocked")
            .build();

        StepVerifier.create(repository.save(customer))
            .expectNextMatches(c -> !c.getCustomerId().toString().isEmpty())
            .verifyComplete();
    }

    @Test
    void findById_shouldReturnEmpty_whenNotExists() {
        StepVerifier.create(repository.findById(UUID.randomUUID()))
            .verifyComplete();
    }

    @Test
    void findById_shouldReturnCustomer_whenExists() {
        UUID id = UUID.fromString("9985bdda-fc5d-4b56-8488-6121f6fe8b18");
        StepVerifier.create(repository.findById(id))
            .assertNext(c -> c.getCustomerId().equals(id))
            .verifyComplete();
    }

    @Test
    void findAll_shouldReturnNCustomers() {
        int customerNumber = 3;

        StepVerifier.create(repository.findAll())
            .expectNextCount(customerNumber)
            .verifyComplete();

    }

}
