package com.kleinmann;

import com.kleinmann.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class NotificationService {


    private final NotificationRepository notificationRepository;

    public void sendNotification(NotificationRequest notificationRequest) {
      log.info("notify: {}", notificationRequest);

      Notification notification = Notification.builder()
              .toCustomerId(notificationRequest.toCustomerID())
              .toCustomerEmail(notificationRequest.toCustomerEmail())
              .sender(notificationRequest.toCustomerEmail())
              .message(notificationRequest.message())
              .sentAt(LocalDateTime.now())
              .build();

      notificationRepository.save(notification);

    }

}
