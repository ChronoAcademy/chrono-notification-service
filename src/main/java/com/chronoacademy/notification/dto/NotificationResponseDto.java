package com.chronoacademy.notification.dto;

import com.chronoacademy.notification.enums.NotificationChannel;
import com.chronoacademy.notification.enums.NotificationStatus;
import com.chronoacademy.notification.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponseDto {

    private String id;
    private String userId;
    private String title;
    private String message;
    private NotificationType type;       // TASK_DUE, SESSION_START, REMINDER, SYSTEM
    private NotificationStatus status;   // UNREAD, READ, ARCHIVED
    private NotificationChannel channel; // SMS, EMAIL, PUSH
    private String sourceService;
    private String sourceEntityId;
    private LocalDateTime createdAt;
    private LocalDateTime readAt;
}