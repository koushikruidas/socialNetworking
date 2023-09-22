package com.social.notificaService.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;
@Data
@Builder
public class FriendRequestNotificationModel {
    private UUID notificationReceiverId;
    private UUID userId;
    private UUID friendId;
    private String message;

}
