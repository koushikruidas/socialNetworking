package com.social.friendService.entity;

import com.social.friendService.model.FriendshipStatus;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Builder
public class Friendship {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;

    private UUID userId;
    private UUID friendId;

    @Enumerated(EnumType.STRING)
    private FriendshipStatus status;
}

