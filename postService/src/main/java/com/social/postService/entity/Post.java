package com.social.postService.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String userName;
    private String description;
    private String postImageURL;
    private int likes;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;
}

