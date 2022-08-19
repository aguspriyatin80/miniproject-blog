package com.miniproject.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniproject.blog.payloads.CommentDTO;
import com.miniproject.blog.payloads.ErrorResponse;
import com.miniproject.blog.services.CommentService;

@RestController
@RequestMapping("/api/v1")

public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDto, @PathVariable Integer postId){
		
		CommentDTO createComment = this.commentService.createComment(commentDto, postId);
		return new ResponseEntity<CommentDTO>(createComment, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ErrorResponse> deleteComment(@PathVariable("commentId") Integer commentId){
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<ErrorResponse>(new ErrorResponse("Comment deleted successfully!", true), HttpStatus.OK);
	}
	
}
