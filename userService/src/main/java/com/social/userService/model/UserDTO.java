package com.social.userService.model;

import com.social.userService.entity.User;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class UserDTO {
    private UUID id;
    private String name;
    private String imageUrl;
    private Date joinDate;
    private List<User> friendList;
}
