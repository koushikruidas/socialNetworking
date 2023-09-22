package com.social.postService.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class PostDTO {
    private Long id;
    private Long userId;
    private String userName;
    private String description;
    private String postImageURL;
    private int likes;
    private Date dateTime;

    // Constructors, getters, and setters
}

