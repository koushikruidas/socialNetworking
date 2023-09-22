package com.social.userService.service;

import com.social.userService.entity.User;
import com.social.userService.model.UserDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserDTO> getAllUsers();

    UserDTO getUserById(UUID id);

    UserDTO createUser(UserDTO user);

    UserDTO updateUser(UUID id, UserDTO user);

    void deleteUser(UUID id);
}
