package com.chronoacademy.notification.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskCreatedEvent {
    private Long taskId;
    private Long userId;
    private String title;
    private String priority;
    private String status;
    private LocalDateTime deadline;
    private LocalDateTime createdAt;
}
