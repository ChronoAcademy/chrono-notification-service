package com.chronoacademy.notification.event;

import com.chronoacademy.notification.config.RabbitMQConfig;
import com.chronoacademy.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TaskCreatedListener {

    private final NotificationService notificationService;

    @RabbitListener(queues = RabbitMQConfig.TASK_CREATED_QUEUE)
    public void handleTaskCreated(TaskCreatedEvent event) {
        log.info("Received TaskCreatedEvent for task {}", event.getTaskId());
        notificationService.createTaskCreatedNotification(event);
    }
}
