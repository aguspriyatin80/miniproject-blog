package com.miniproject.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproject.blog.entities.Comment;
import com.miniproject.blog.entities.Post;
import com.miniproject.blog.exceptions.ResourceNotFoundException;
import com.miniproject.blog.payloads.CommentDTO;
import com.miniproject.blog.repositories.CommentRepo;
import com.miniproject.blog.repositories.PostRepo;
import com.miniproject.blog.services.CommentService;


@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDTO createComment(CommentDTO commentDto, Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post Id", postId)); 
		Comment comment = modelMapper.map(commentDto,Comment.class);
		comment.setPost(post);
		Comment savedComment = commentRepo.save(comment);
		return modelMapper.map(savedComment,CommentDTO.class);
	}

	@Override
	public void deleteComment(Integer commentId) {

		Comment comment = commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "Comment Id", commentId));
		commentRepo.delete(comment);
		
	}

}
