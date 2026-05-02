package com.chronoacademy.notification.repository;

import com.chronoacademy.notification.entity.Notification;
import com.chronoacademy.notification.enums.NotificationStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {

    // Toutes les notifications d'un user
    List<Notification> findByUserId(String userId);

    // Notifications filtrées par statut
    List<Notification> findByUserIdAndStatus(String userId, NotificationStatus status);

    // Supprimer toutes les notifications d'un user
    void deleteByUserId(String userId);

    // Compter les non lues
    long countByUserIdAndStatus(String userId, NotificationStatus status);
}
