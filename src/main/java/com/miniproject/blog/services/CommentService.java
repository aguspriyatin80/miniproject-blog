package com.miniproject.blog.services;

import com.miniproject.blog.payloads.CommentDTO;

public interface CommentService {

	CommentDTO createComment(CommentDTO commentDto, Integer postId);
	
	void deleteComment(Integer commentId);
}
