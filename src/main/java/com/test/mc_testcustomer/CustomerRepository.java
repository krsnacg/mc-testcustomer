package com.test.mc_testcustomer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.test.mc_testcustomer.model.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class CustomerRepository {

    private final List<Customer> customerDB = new ArrayList<>(List.of(
        Customer.builder().customerId(UUID.randomUUID()).name("John").lastname("Doe").username("johnd").build(),
        Customer.builder().customerId(UUID.randomUUID()).name("Jane").lastname("Smith").username("janes").build(),
        Customer.builder().customerId(UUID.fromString("9985bdda-fc5d-4b56-8488-6121f6fe8b18")).name("Jane").lastname("Smith").username("janes").build()
    ));

    public Mono<Customer> findById(UUID customerId) {
        return Mono.justOrEmpty(
            customerDB.stream()
                .filter(c -> c.getCustomerId().equals(customerId))
                .findFirst()
        );
    }

    public Flux<Customer> findAll() {
        return Flux.fromIterable(customerDB);
    }

    public Mono<Customer> save(Customer customer) {
        return Mono.fromSupplier(UUID::randomUUID)
            .map(id -> Customer.builder()
                .customerId(id)
                .name(customer.getName())
                .lastname(customer.getLastname())
                .age(customer.getAge())
                .username(customer.getUsername())
                .build())
            .doOnNext(customerDB::add);
    }
    
}
