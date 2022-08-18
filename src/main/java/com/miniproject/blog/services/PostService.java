package com.miniproject.blog.services;

import com.miniproject.blog.payloads.PostDTO;

public interface PostService {
	//create
	PostDTO createPost(PostDTO postDto, Integer userId, Integer categoryId); 
}
