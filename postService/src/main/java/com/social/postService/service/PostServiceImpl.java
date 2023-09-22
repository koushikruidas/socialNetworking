package com.social.postService.service;

import com.social.postService.entity.Post;
import com.social.postService.exception.ResourceNotFoundException;
import com.social.postService.model.PostDTO;
import com.social.postService.repo.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final ModelMapper mapper;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, ModelMapper mapper) {
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDTO> getAllPosts() {
        return postRepository.findAll().stream()
                .map(post -> mapper.map(post, PostDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PostDTO getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
        return mapper.map(post, PostDTO.class);
    }

    @Override
    @Transactional
    public PostDTO createPost(PostDTO postDTO) {
        Post post = mapper.map(postDTO, Post.class);
        post.setDateTime(new Date()); // Set the current date and time
        postRepository.save(post);
        return mapper.map(post, PostDTO.class);
    }

    @Override
    @Transactional
    public PostDTO updatePost(Long id, PostDTO postDTO) {
        Post updatedPost = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
        updatedPost.setUserId(postDTO.getUserId());
        updatedPost.setUserName(postDTO.getUserName());
        updatedPost.setDescription(postDTO.getDescription());
        updatedPost.setPostImageURL(postDTO.getPostImageURL());
        postRepository.save(updatedPost);
        return mapper.map(updatedPost, PostDTO.class);
    }

    @Override
    @Transactional
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}

