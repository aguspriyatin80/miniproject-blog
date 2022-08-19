package com.miniproject.blog.services;

import java.util.List;

import com.miniproject.blog.payloads.PostDTO;
import com.miniproject.blog.payloads.PostResponse;

public interface PostService {
	//create
	PostDTO createPost(PostDTO postDto, Integer userId, Integer categoryId);
	
	// get all posts by category
	
	List<PostDTO> getPostsByCategory(Integer categoryId);
	
	// get all posts by user
	PostResponse getPostsByUser(Integer categoryId, Integer pageNumber, Integer pageSize);
	
	// get all posts
	PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
	// get post by id
	PostDTO getPost(Integer postId);
	
	// delete post by id
	void deletePost(Integer postId);
	
	PostDTO updatePost(PostDTO postDTO, Integer postId);
}
