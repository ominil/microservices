package com.kleinmann.customer;

import com.kleinmann.clients.fraud.FraudCheckResponse;
import com.kleinmann.clients.fraud.FraudClient;
import com.kleinmann.clients.notification.NotificationClient;
import com.kleinmann.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public record CustomerService(CustomerRepository customerRepository,
                              FraudClient fraudClient,
                              NotificationClient notificationClient) {

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        // todo: check if email is valid
        // todo check if email is not taken

        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (Objects.requireNonNull(fraudCheckResponse).isFraudster()) {
            throw new IllegalStateException("fraudster detected");
        }


        // todo: make it async. i.e. add to queue
        notificationClient.sendNotification(NotificationRequest.builder()
                        .toCustomerID(customer.getId())
                        .toCustomerEmail(customer.getEmail())
                        .message("Yeah you're not a fraudster!")
                        .build());

    }
}
