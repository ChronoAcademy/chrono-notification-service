package com.chronoacademy.notification.dto;

import com.chronoacademy.notification.enums.NotificationChannel;
import com.chronoacademy.notification.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequestDto {

    private String userId;               // obligatoire — vérifié via Feign
    private String title;                // obligatoire
    private String message;              // obligatoire
    private NotificationType type;       // TASK_DUE, SESSION_START, REMINDER, SYSTEM
    private NotificationChannel channel; // SMS, EMAIL, PUSH
    private String sourceService;        // "task-service", "scheduling-service"...
    private String sourceEntityId;       // id de la tâche ou créneau concerné
}