package com.test.mc_testcustomer.model;

import jakarta.validation.constraints.NotBlank;

public record CustomerRequest(
    @NotBlank String name,
    @NotBlank String lastname,
    @NotBlank Integer age,
    @NotBlank String username
) {
    
}
