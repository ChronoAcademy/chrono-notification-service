package com.chronoacademy.notification.service;

import com.chronoacademy.notification.entity.Notification;
import com.chronoacademy.notification.enums.NotificationChannel;
import com.chronoacademy.notification.enums.NotificationStatus;
import com.chronoacademy.notification.enums.NotificationType;
import com.chronoacademy.notification.event.TaskCreatedEvent;
import com.chronoacademy.notification.exception.NotificationNotFoundException;
import com.chronoacademy.notification.exception.UserNotFoundException;
import com.chronoacademy.notification.feign.UserServiceClient;
import com.chronoacademy.notification.repository.NotificationRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserServiceClient userServiceClient; // OpenFeign

    // ─── CREATE ────────────────────────────────────────────────────────────────
    public Notification createNotification(Notification notification) {
        // Communication synchrone via OpenFeign
        // Vérifie que l'utilisateur existe dans auth-service avant de sauvegarder
        try {
            userServiceClient.getUserById(notification.getUserId());
        } catch (FeignException.NotFound e) {
            throw new UserNotFoundException(notification.getUserId());
        }

        notification.setStatus(NotificationStatus.UNREAD);
        notification.setCreatedAt(LocalDateTime.now());
        return notificationRepository.save(notification);
    }

    // ─── READ ALL BY USER ──────────────────────────────────────────────────────
    public Notification createTaskCreatedNotification(TaskCreatedEvent event) {
        Notification notification = Notification.builder()
                .userId(String.valueOf(event.getUserId()))
                .title("New task created")
                .message("Task \"" + event.getTitle() + "\" was created with priority " + event.getPriority())
                .type(NotificationType.TASK_CREATED)
                .status(NotificationStatus.UNREAD)
                .channel(NotificationChannel.PUSH)
                .sourceService("task-service")
                .sourceEntityId(String.valueOf(event.getTaskId()))
                .createdAt(LocalDateTime.now())
                .build();

        return notificationRepository.save(notification);
    }

    public List<Notification> getAllByUserId(String userId) {
        return notificationRepository.findByUserId(userId);
    }

    // ─── READ UNREAD BY USER ───────────────────────────────────────────────────
    public List<Notification> getUnreadByUserId(String userId) {
        return notificationRepository.findByUserIdAndStatus(userId, NotificationStatus.UNREAD);
    }

    // ─── READ ONE BY ID ────────────────────────────────────────────────────────
    public Notification getById(String id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new NotificationNotFoundException(id));
    }

    // ─── UPDATE — mark as read ─────────────────────────────────────────────────
    public Notification markAsRead(String id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new NotificationNotFoundException(id));
        notification.setStatus(NotificationStatus.READ);
        notification.setReadAt(LocalDateTime.now());
        return notificationRepository.save(notification);
    }

    // ─── UPDATE — mark as archived ─────────────────────────────────────────────
    public Notification markAsArchived(String id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new NotificationNotFoundException(id));
        notification.setStatus(NotificationStatus.ARCHIVED);
        return notificationRepository.save(notification);
    }

    // ─── DELETE ONE ────────────────────────────────────────────────────────────
    public void deleteById(String id) {
        if (!notificationRepository.existsById(id)) {
            throw new NotificationNotFoundException(id);
        }
        notificationRepository.deleteById(id);
    }

    // ─── DELETE ALL BY USER ────────────────────────────────────────────────────
    public void deleteAllByUserId(String userId) {
        notificationRepository.deleteByUserId(userId);
    }

    // ─── COUNT UNREAD ──────────────────────────────────────────────────────────
    public long countUnread(String userId) {
        return notificationRepository.countByUserIdAndStatus(userId, NotificationStatus.UNREAD);
    }

}
