package com.social.friendService.repository;

import com.social.friendService.entity.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FriendshipRepository extends JpaRepository<Friendship, UUID> {
    boolean existsByUserIdAndFriendId(UUID userId, UUID friendId);

    List<Friendship> findByUserId(UUID userId);
}

