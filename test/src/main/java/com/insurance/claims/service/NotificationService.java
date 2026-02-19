package com.insurance.claims.service;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Service for sending real-time notifications to users via WebSocket
 */
@Service
@RequiredArgsConstructor
public class NotificationService {
    
    private final SimpMessagingTemplate messagingTemplate;
    
    public void notifyUser(Long userId, String message) {
        Map<String, Object> notification = new HashMap<>();
        notification.put("message", message);
        notification.put("timestamp", LocalDateTime.now());
        
        // Send notification to specific user's topic
        messagingTemplate.convertAndSend("/topic/user/" + userId, notification);
    }
    
    public void notifyAllAdmins(String message) {
        Map<String, Object> notification = new HashMap<>();
        notification.put("message", message);
        notification.put("timestamp", LocalDateTime.now());
        
        // Send notification to all admins
        messagingTemplate.convertAndSend("/topic/admin", notification);
    }
}

