package com.example.mypenavigatorapi.communication.controllers;

import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.communication.domain.dto.NotificationDto;
import com.example.mypenavigatorapi.communication.domain.dto.SaveNotificationDto;
import com.example.mypenavigatorapi.communication.services.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@Tag(name = "notifications", description = "endpoints to manage notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping
    @Operation(summary = "Get all notifications by user Id")
    public List<NotificationDto> findAllNotificationsByUserId(
            @RequestParam(value = "userId", defaultValue = "0") Long userId
    ) {
        return notificationService.findAllByUserId(userId).stream()
                .map(notification -> Mapper.map(notification, NotificationDto.class))
                .toList();
    }

    @PostMapping
    @Operation(summary = "Create a new notification")
    public NotificationDto saveNotification(
            @RequestParam(value = "userId", defaultValue = "0") Long userId,
            @RequestBody SaveNotificationDto dto) {
        return Mapper.map(notificationService.save(dto, userId, true), NotificationDto.class);
    }

    @PatchMapping
    @Operation(summary = "Mark notifications as seen")
    public List<NotificationDto> seenNotifications(@RequestParam(value = "userId", defaultValue = "0") Long userId) {
        return notificationService.seenNotifications(userId).stream()
                .map(notification -> Mapper.map(notification, NotificationDto.class))
                .toList();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete notification by id")
    public ResponseEntity<?> deleteNotification(@PathVariable("id") Long id) {
        return notificationService.delete(id);
    }
}
