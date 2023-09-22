package com.social.userService.service;

import com.social.userService.entity.User;
import com.social.userService.exception.ResourceNotFoundException;
import com.social.userService.model.UserDTO;
import com.social.userService.repo.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper; // Inject the ModelMapper

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }
    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> mapper.map(user,UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user","id",id));
        return mapper.map(user,UserDTO.class);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        User user = mapper.map(userDTO, User.class);
        user.setJoinDate(new Date());
        userRepository.save(user);
        return mapper.map(user,UserDTO.class);
    }

    @Override
    public UserDTO updateUser(UUID id, UserDTO userDTO) {
        User updatedUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user","id",id));
        updatedUser.setName(userDTO.getName());
        updatedUser.setImageUrl(userDTO.getImageUrl());
        userRepository.save(updatedUser);
        return mapper.map(updatedUser,UserDTO.class);
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}

