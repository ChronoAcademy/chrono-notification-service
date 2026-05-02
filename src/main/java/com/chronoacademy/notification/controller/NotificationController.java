package com.chronoacademy.notification.controller;

import com.chronoacademy.notification.entity.Notification;
import com.chronoacademy.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    // ─── CREATE ────────────────────────────────────────────────────────────────
    // POST http://localhost:8085/notifications
    @PostMapping
    public ResponseEntity<Notification> create(@RequestBody Notification notification) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(notificationService.createNotification(notification));
    }

    // ─── READ ALL BY USER ──────────────────────────────────────────────────────
    // GET http://localhost:8085/notifications/user/{userId}
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notification>> getAllByUser(@PathVariable String userId) {
        return ResponseEntity.ok(notificationService.getAllByUserId(userId));
    }

    // ─── READ UNREAD BY USER ───────────────────────────────────────────────────
    // GET http://localhost:8085/notifications/user/{userId}/unread
    @GetMapping("/user/{userId}/unread")
    public ResponseEntity<List<Notification>> getUnread(@PathVariable String userId) {
        return ResponseEntity.ok(notificationService.getUnreadByUserId(userId));
    }

    // ─── READ ONE BY ID ────────────────────────────────────────────────────────
    // GET http://localhost:8085/notifications/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Notification> getById(@PathVariable String id) {
        return ResponseEntity.ok(notificationService.getById(id));
    }

    // ─── COUNT UNREAD ──────────────────────────────────────────────────────────
    // GET http://localhost:8085/notifications/user/{userId}/unread/count
    @GetMapping("/user/{userId}/unread/count")
    public ResponseEntity<Long> countUnread(@PathVariable String userId) {
        return ResponseEntity.ok(notificationService.countUnread(userId));
    }

    // ─── UPDATE — mark as read ─────────────────────────────────────────────────
    // PUT http://localhost:8085/notifications/{id}/read
    @PutMapping("/{id}/read")
    public ResponseEntity<Notification> markAsRead(@PathVariable String id) {
        return ResponseEntity.ok(notificationService.markAsRead(id));
    }

    // ─── UPDATE — mark as archived ─────────────────────────────────────────────
    // PUT http://localhost:8085/notifications/{id}/archive
    @PutMapping("/{id}/archive")
    public ResponseEntity<Notification> markAsArchived(@PathVariable String id) {
        return ResponseEntity.ok(notificationService.markAsArchived(id));
    }

    // ─── DELETE ONE ────────────────────────────────────────────────────────────
    // DELETE http://localhost:8085/notifications/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        notificationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ─── DELETE ALL BY USER ────────────────────────────────────────────────────
    // DELETE http://localhost:8085/notifications/user/{userId}
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteAllByUser(@PathVariable String userId) {
        notificationService.deleteAllByUserId(userId);
        return ResponseEntity.noContent().build();
    }
}
