package com.social.notificaService.controller;

import com.social.notificaService.model.FriendRequestDTO;
import com.social.notificaService.service.NotificationService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // Endpoint to send a friend request notification
    @PostMapping("/friend-request")
    public void sendFriendRequestNotification(
            @RequestBody FriendRequestDTO friendRequestDTO) {
        // You can add logic to fetch user information, construct the notification message, and send it
        String notificationMessage = friendRequestDTO.getSenderId() + " sent you a friend request.";
        notificationService.sendFriendRequestNotification(friendRequestDTO.getSenderId(),friendRequestDTO.getReceiverId());
    }

    // Endpoint to send a comment notification
    @PostMapping("/comment")
    public void sendCommentNotification(
            @RequestParam("postId") UUID postId,
            @RequestParam("commenterId") String commenterId,
            @RequestParam("receiverId") UUID receiverId) {
        // You can add logic to fetch user information, construct the notification message, and send it
        String notificationMessage = commenterId + " commented on your post (Post ID: " + postId + ").";
        notificationService.sendFriendRequestNotification(receiverId, receiverId);
    }

    // Add endpoints for other types of notifications as needed
    @PostMapping("/accept-request")
    public void acceptFriendRequestNotification(
            @RequestBody FriendRequestDTO friendRequestDTO) {
        notificationService.friendRequestAcceptedNotification(friendRequestDTO.getSenderId(),friendRequestDTO.getReceiverId());
    }

    @PostMapping("/reject-request")
    public void rejectFriendRequestNotification(
            @RequestBody FriendRequestDTO friendRequestDTO) {
        notificationService.friendRequestRejectedNotification(friendRequestDTO.getSenderId(),friendRequestDTO.getReceiverId());
    }
}
