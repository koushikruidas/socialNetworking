package com.social.notificaService.service;

import com.social.notificaService.model.FriendRequestNotificationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class NotificationService {

    private final KafkaTemplate<String, FriendRequestNotificationModel> kafkaTemplate;

    @Autowired
    public NotificationService(KafkaTemplate<String, FriendRequestNotificationModel> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendFriendRequestNotification(UUID senderId, UUID receiverId) {
        // Construct a notification message and send it to the "friend_requests" Kafka topic
        String notificationMessage = senderId + " sent you a friend request.";
        FriendRequestNotificationModel kafkaMessage = FriendRequestNotificationModel.builder()
                .notificationReceiverId(receiverId)
                .userId(senderId)
                .friendId(receiverId)
                .message(notificationMessage).build();

        kafkaTemplate.send("friend_requests", kafkaMessage);
    }

    // Add methods for other types of notifications as needed
    public void friendRequestAcceptedNotification(UUID senderId, UUID receiverId) {
        // Construct a notification message and send it to the "friend_requests" Kafka topic
        String notificationMessage = receiverId + " accepted your friend request.";
        FriendRequestNotificationModel kafkaMessage = FriendRequestNotificationModel.builder()
                .notificationReceiverId(senderId)
                .userId(senderId)
                .friendId(receiverId)
                .message(notificationMessage).build();

        kafkaTemplate.send("friend_requests", kafkaMessage);
    }

    public void friendRequestRejectedNotification(UUID senderId, UUID receiverId) {
        // Construct a notification message and send it to the "friend_requests" Kafka topic
        String notificationMessage = receiverId + " rejected your friend request.";
        FriendRequestNotificationModel kafkaMessage = FriendRequestNotificationModel.builder()
                .notificationReceiverId(senderId)
                .userId(senderId)
                .friendId(receiverId)
                .message(notificationMessage).build();

        kafkaTemplate.send("friend_requests", kafkaMessage);
    }
}
