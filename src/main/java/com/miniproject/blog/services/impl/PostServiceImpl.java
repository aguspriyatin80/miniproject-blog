package com.miniproject.blog.services.impl;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproject.blog.entities.Category;
import com.miniproject.blog.entities.Post;
import com.miniproject.blog.entities.User;
import com.miniproject.blog.exceptions.ResourceNotFoundException;
import com.miniproject.blog.payloads.PostDTO;
import com.miniproject.blog.repositories.CategoryRepo;
import com.miniproject.blog.repositories.PostRepo;
import com.miniproject.blog.repositories.UserRepo;
import com.miniproject.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	UserRepo userRepo;
	
	
	@Autowired
	PostRepo postRepo;
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public PostDTO createPost(PostDTO postDto, Integer userId, Integer categoryId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User Id",userId));
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id",categoryId));
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost = this.postRepo.save(post);
		
		return this.modelMapper.map(newPost, PostDTO.class);
		
	}

}
