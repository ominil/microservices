package com.kleinmann.notification;

import com.kleinmann.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/notification")
@AllArgsConstructor
@Slf4j
public class NotificationController {

    private final NotificationService notificationService;
    @PostMapping
    void notify(@RequestBody NotificationRequest request) {
        log.info("notify: {}", request);
        notificationService.sendNotification(request);
    }
}
