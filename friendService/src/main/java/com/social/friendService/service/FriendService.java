package com.social.friendService.service;

import com.social.friendService.entity.Friendship;
import com.social.friendService.model.FriendRequestDTO;
import com.social.friendService.model.FriendshipStatus;
import com.social.friendService.repository.FriendshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class FriendService {

    @Autowired
    private FriendshipRepository friendshipRepository;
    @Autowired
    private RestTemplate restTemplate;

    public void sendFriendRequest(UUID senderId, UUID receiverId) {
        // Check if the friendship already exists
        if (friendshipRepository.existsByUserIdAndFriendId(senderId, receiverId)) {
            throw new IllegalStateException("Friendship already exists.");
        }
        // URL for the Notification service
        String URL = "http://notification-service/notifications/friend-request";
        // Create a new friendship request
        Friendship friendshipRequest = Friendship.builder()
                .userId(senderId)
                .friendId(receiverId)
                .status(FriendshipStatus.PENDING)
                .build();
        FriendRequestDTO friendRequestDTO = FriendRequestDTO.builder()
                .senderId(senderId)
                .receiverId(receiverId)
                .build();
        friendshipRepository.save(friendshipRequest);
        sendFriendRequestNotification(URL,friendRequestDTO);
    }

    public void acceptFriendship(UUID friendshipId) {
        // URL for the Notification service
        String URL = "http://notification-service/notifications/accept-request";
        Friendship friendship = friendshipRepository.findById(friendshipId)
                .orElseThrow(() -> new IllegalArgumentException("Friendship not found."));

        if (friendship.getStatus() == FriendshipStatus.PENDING) {
            friendship.setStatus(FriendshipStatus.ACCEPTED);
            friendshipRepository.save(friendship);
            FriendRequestDTO friendRequestDTO = FriendRequestDTO.builder()
                    .senderId(friendship.getUserId())
                    .receiverId(friendship.getFriendId())
                    .build();
            // Send a notification that the friend request was accepted
            sendFriendRequestAcceptedNotification(URL,friendRequestDTO);
        }
    }

    public void rejectFriendship(UUID friendshipId) {
        // URL for the Notification service
        String URL = "http://notification-service/notifications/reject-request";
        Friendship friendship = friendshipRepository.findById(friendshipId)
                .orElseThrow(() -> new IllegalArgumentException("Friendship not found."));

        if (friendship.getStatus() == FriendshipStatus.PENDING) {
            friendship.setStatus(FriendshipStatus.REJECTED);
            FriendRequestDTO friendRequestDTO = FriendRequestDTO.builder()
                    .senderId(friendship.getUserId())
                    .receiverId(friendship.getFriendId())
                    .build();
            // Send a notification that the friend request was rejected
            sendFriendRequestRejectedNotification(URL,friendRequestDTO);
        }
    }

    public List<Friendship> getFriendshipsByUserId(UUID userId) {
        return friendshipRepository.findByUserId(userId);
    }

    private void sendFriendRequestNotification(String URL, FriendRequestDTO friendRequestDTO) {
        restTemplate.postForObject(URL,friendRequestDTO,Void.class);
    }

    private void sendFriendRequestAcceptedNotification(String URL, FriendRequestDTO friendRequestDTO) {
        restTemplate.postForObject(URL,friendRequestDTO,Void.class);
    }

    private void sendFriendRequestRejectedNotification(String URL, FriendRequestDTO friendRequestDTO) {
        restTemplate.postForObject(URL,friendRequestDTO,Void.class);
    }
}
