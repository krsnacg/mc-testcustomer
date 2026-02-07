package com.test.mc_testcustomer.model;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Customer {
    private UUID customerId;
    private String name;
    private String lastname;
    private Integer age;
    private String username;
}
