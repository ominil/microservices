package com.kleinmann.clients.notification;

import lombok.Builder;

@Builder
public record NotificationRequest(
        Integer toCustomerID,
        String toCustomerEmail,
        String message
) {
}
