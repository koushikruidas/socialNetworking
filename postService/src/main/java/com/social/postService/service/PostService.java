package com.social.postService.service;

import com.social.postService.entity.Post;
import com.social.postService.model.PostDTO;

import java.util.List;

public interface PostService {
    List<PostDTO> getAllPosts();
    PostDTO getPostById(Long id);
    PostDTO createPost(PostDTO postDTO);
    PostDTO updatePost(Long id, PostDTO postDTO);
    void deletePost(Long id);
}

