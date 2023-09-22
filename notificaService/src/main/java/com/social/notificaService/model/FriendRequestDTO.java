package com.social.notificaService.model;

import lombok.Data;

import java.util.UUID;

@Data
public class FriendRequestDTO {
    private UUID senderId;
    private UUID receiverId;
}
