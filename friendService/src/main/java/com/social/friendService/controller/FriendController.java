package com.social.friendService.controller;

import com.social.friendService.entity.Friendship;
import com.social.friendService.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friendships")
public class FriendController {

    private final FriendService friendService;

    @Autowired
    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    // Create a new friendship (add friend)
    @PostMapping("/send-friend-request")
    public ResponseEntity<HttpStatus> addFriend(
            @RequestParam("userId") UUID userId,
            @RequestParam("friendId") UUID friendId) {
        friendService.sendFriendRequest(userId, friendId);
        return ResponseEntity.ok().build();
    }

    // Accept a pending friendship request
    @PostMapping("/accept")
    public ResponseEntity<Void> acceptFriendship(@RequestParam("friendshipId") UUID friendshipId) {
        friendService.acceptFriendship(friendshipId);
        return ResponseEntity.ok().build();
    }

    // Reject a pending friendship request
    @PostMapping("/reject")
    public ResponseEntity<Void> rejectFriendship(@RequestParam("friendshipId") UUID friendshipId) {
        friendService.rejectFriendship(friendshipId);
        return ResponseEntity.ok().build();
    }

    // Get all friendships for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Friendship>> getFriendshipsByUserId(@PathVariable("userId") UUID userId) {
        List<Friendship> friendships = friendService.getFriendshipsByUserId(userId);
        return ResponseEntity.ok(friendships);
    }
}
