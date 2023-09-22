package com.social.commentService.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CommentDTO {
    private Long id;
    private Long postId;
    private Long userId;
    private String comment;
    private Date dateTime;
}

