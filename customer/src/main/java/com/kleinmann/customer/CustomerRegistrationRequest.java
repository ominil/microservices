package com.kleinmann.customer;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email
) {

}
