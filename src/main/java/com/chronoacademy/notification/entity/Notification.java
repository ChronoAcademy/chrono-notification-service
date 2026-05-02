package com.chronoacademy.notification.entity;

import com.chronoacademy.notification.enums.NotificationChannel;
import com.chronoacademy.notification.enums.NotificationStatus;
import com.chronoacademy.notification.enums.NotificationType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    private String id;

    private String userId;             // référence vers auth-service (vérifié via Feign)
    private String title;
    private String message;

    private NotificationType type;     // TASK_DUE, SESSION_START, REMINDER, SYSTEM
    private NotificationStatus status; // UNREAD, READ, ARCHIVED
    private NotificationChannel channel; // sms , email , push

    private String sourceService;      // "task-service", "scheduling-service"...
    private String sourceEntityId;     // id de la tâche ou créneau concerné

    private LocalDateTime createdAt;
    private LocalDateTime readAt;
}
