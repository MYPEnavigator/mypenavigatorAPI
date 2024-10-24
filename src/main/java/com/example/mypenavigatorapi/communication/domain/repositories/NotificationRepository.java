package com.example.mypenavigatorapi.communication.domain.repositories;

import com.example.mypenavigatorapi.communication.domain.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAllByUserId(Long userId);
}
