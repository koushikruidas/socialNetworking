package com.social.commentService.service;

import com.social.commentService.entity.Comment;
import com.social.commentService.exception.ResourceNotFoundException;
import com.social.commentService.model.CommentDTO;
import com.social.commentService.repo.CommentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDTO> getAllComments() {
        return commentRepository.findAll().stream()
                .map(comment -> modelMapper.map(comment, CommentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CommentDTO getCommentById(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("comment", "id", id));
        return modelMapper.map(comment, CommentDTO.class);
    }

    @Override
    @Transactional
    public CommentDTO createComment(CommentDTO commentDTO) {
        Comment comment = modelMapper.map(commentDTO, Comment.class);
        comment.setDateTime(new Date()); // Set the current date and time
        commentRepository.save(comment);
        return modelMapper.map(comment, CommentDTO.class);
    }

    @Override
    @Transactional
    public CommentDTO updateComment(Long id, CommentDTO commentDTO) {
        Comment updatedComment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("comment", "id", id));
        updatedComment.setPostId(commentDTO.getPostId());
        updatedComment.setUserId(commentDTO.getUserId());
        updatedComment.setComment(commentDTO.getComment());
        commentRepository.save(updatedComment);
        return modelMapper.map(updatedComment, CommentDTO.class);
    }

    @Override
    @Transactional
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
