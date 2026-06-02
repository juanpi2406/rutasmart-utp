package com.rutasmart.api.notification;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private final NotificationRepository notifications;

    public NotificationController(NotificationRepository notifications) {
        this.notifications = notifications;
    }

    @GetMapping
    public List<NotificationEntity> list() {
        return notifications.findAllByOrderByIdDesc();
    }

    @PutMapping("/{id}/read")
    public NotificationEntity markRead(@PathVariable Long id) {
        NotificationEntity notification = notifications.findById(id).orElseThrow();
        notification.setRead(true);
        return notifications.save(notification);
    }
}
