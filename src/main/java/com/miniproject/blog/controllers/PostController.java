package com.miniproject.blog.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

//import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.multipart.MultipartFile;

import com.miniproject.blog.config.AppConstants;
import com.miniproject.blog.payloads.ErrorResponse;
import com.miniproject.blog.payloads.PostDTO;
import com.miniproject.blog.payloads.PostResponse;
import com.miniproject.blog.services.FileService;
import com.miniproject.blog.services.PostService;

@RestController
@RequestMapping("/api/v1")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	@PostMapping("/users/{userId}/categories/{categoryId}/posts")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){
		
		PostDTO createPost = this.postService.createPost(postDto, userId, categoryId); 
		return new ResponseEntity<PostDTO>(createPost,HttpStatus.CREATED); 
	}
	
	@GetMapping("/users/{userId}/posts")
	public ResponseEntity<PostResponse> getPostsByUser(@PathVariable("userId") Integer id,
		@RequestParam(value="pageNumber", defaultValue=AppConstants.PAGE_NUMBER, required=false) int pageNumber,
		@RequestParam(value="pageSize", defaultValue=AppConstants.PAGE_SIZE, required=false) int pageSize)		
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
		@RequestParam(value="pageNumber", defaultValue=AppConstants.PAGE_NUMBER, required=false) Integer pageNumber,
		@RequestParam(value="pageSize", defaultValue=AppConstants.PAGE_SIZE, required=false) Integer pageSize,
		@RequestParam(value="sortBy", defaultValue=AppConstants.POSTS_SORT_BY, required=false) String sortBy,
		@RequestParam(value="sortDir", defaultValue=AppConstants.SORT_DIR, required=false) String sortDir)

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
	
	// post image upload
	@PostMapping("/posts/image/upload/{postId}")
	public ResponseEntity<PostDTO> uploadPostImage(
			@RequestParam("image") MultipartFile image,
			@PathVariable("postId") Integer postId) throws IOException{
		PostDTO postDto = this.postService.getPost(postId);
		String fileName = this.fileService.uploadImage(path, image);
		
		postDto.setImageName(fileName);
		PostDTO updatePost = this.postService.updatePost(postDto, postId);
		
		return new ResponseEntity<PostDTO>(updatePost,HttpStatus.OK);
		
	}
	
	//method to serve file
	@GetMapping(value="/posts/image/{imageName}", produces=MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
}
