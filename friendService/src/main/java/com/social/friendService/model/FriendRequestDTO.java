package com.social.friendService.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;
@Data
@Builder
public class FriendRequestDTO {
    private UUID senderId;
    private UUID receiverId;
}
