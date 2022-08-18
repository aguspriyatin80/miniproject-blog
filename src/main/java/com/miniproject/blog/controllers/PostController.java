package com.miniproject.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniproject.blog.entities.Post;
import com.miniproject.blog.payloads.PostDTO;
import com.miniproject.blog.services.PostService;

@RestController
@RequestMapping("/api/v1")
public class PostController {

	@Autowired
	private PostService postService;
	
	@PostMapping("/users/{userId}/categories/{categoryId}/posts")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){
		
		PostDTO createPost = this.postService.createPost(postDto, userId, categoryId); 
		return new ResponseEntity<PostDTO>(createPost,HttpStatus.CREATED); 
	}
	
	@GetMapping("/users/{userId}/posts")
	public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable("userId") Integer id){
		List<PostDTO> postsByUser = this.postService.getPostsByUser(id);
		return new ResponseEntity<List<PostDTO>>(postsByUser, HttpStatus.OK);
	}
	
	@GetMapping("/categories/{categoryId}/posts")
	public ResponseEntity<List<PostDTO>> getPostsByCategory(@PathVariable("categoryId") Integer id){
		List<PostDTO> postsByCategory = this.postService.getPostsByCategory(id);
		return new ResponseEntity<List<PostDTO>>(postsByCategory, HttpStatus.OK);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<List<PostDTO>> getAllPosts(){
		List<PostDTO> allPosts = this.postService.getAllPosts();
		return new ResponseEntity<List<PostDTO>>(allPosts,HttpStatus.OK);
	}
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId){		
		PostDTO postDto = this.postService.getPost(postId);
		return new ResponseEntity<PostDTO>(postDto,HttpStatus.OK);
	}
}
