package com.social.commentService.service;

import com.social.commentService.model.CommentDTO;
import java.util.List;

public interface CommentService {
    List<CommentDTO> getAllComments();
    CommentDTO getCommentById(Long id);
    CommentDTO createComment(CommentDTO commentDTO);
    CommentDTO updateComment(Long id, CommentDTO commentDTO);
    void deleteComment(Long id);
}

