package com.miniproject.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miniproject.blog.entities.Post;
import com.miniproject.blog.payloads.ErrorResponse;
import com.miniproject.blog.payloads.PostDTO;
import com.miniproject.blog.payloads.PostResponse;
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
	public ResponseEntity<PostResponse> getPostsByUser(@PathVariable("userId") Integer id,
		@RequestParam(value="pageNumber", defaultValue="0", required=false) int pageNumber,
		@RequestParam(value="pageSize", defaultValue="5", required=false) int pageSize)		
	{
		PostResponse postsByUser = this.postService.getPostsByUser(id, pageNumber, pageSize);
		return new ResponseEntity<PostResponse>(postsByUser, HttpStatus.OK);
	}
	
	@GetMapping("/categories/{categoryId}/posts")
	public ResponseEntity<List<PostDTO>> getPostsByCategory(@PathVariable("categoryId") Integer id){
		List<PostDTO> postsByCategory = this.postService.getPostsByCategory(id);
		return new ResponseEntity<List<PostDTO>>(postsByCategory, HttpStatus.OK);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
		@RequestParam(value="pageNumber", defaultValue="0", required=false) Integer pageNumber,
		@RequestParam(value="pageSize", defaultValue="5", required=false) Integer pageSize,
		@RequestParam(value="sortBy", defaultValue="postId", required=false) String sortBy,
		@RequestParam(value="sortDir", defaultValue="asc", required=false) String sortDir)

	{
		PostResponse postResponse = this.postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId){		
		PostDTO postDto = this.postService.getPost(postId);
		return new ResponseEntity<PostDTO>(postDto,HttpStatus.OK);
	}
	
	@DeleteMapping("/posts/{postId}")
	public ErrorResponse deletePostBydId(@PathVariable Integer postId){		
		this.postService.deletePost(postId);
		return new ErrorResponse("Post with id : "+ postId + " was deleted", true);
	}
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> updatePostBydId(@RequestBody PostDTO postDto, @PathVariable Integer postId){		
		PostDTO updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDTO>(updatePost,HttpStatus.OK);
	}
}
