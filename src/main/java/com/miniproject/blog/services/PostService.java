package com.miniproject.blog.services;

import java.util.List;

import com.miniproject.blog.payloads.PostDTO;

public interface PostService {
	//create
	PostDTO createPost(PostDTO postDto, Integer userId, Integer categoryId);
	
	// get all posts by category
	
	List<PostDTO> getPostsByCategory(Integer categoryId);
	
	// get all posts by user
	List<PostDTO> getPostsByUser(Integer categoryId);
	
	// get all posts
	List<PostDTO> getAllPosts();
}
