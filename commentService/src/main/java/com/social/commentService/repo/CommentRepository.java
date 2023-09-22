package com.social.commentService.repo;

import com.social.commentService.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // You can add custom query methods here if needed
}
