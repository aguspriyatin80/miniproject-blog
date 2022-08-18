package com.miniproject.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.miniproject.blog.entities.Category;
import com.miniproject.blog.entities.Post;
import com.miniproject.blog.entities.User;
import com.miniproject.blog.exceptions.ResourceNotFoundException;
import com.miniproject.blog.payloads.PostDTO;
import com.miniproject.blog.payloads.UserDTO;
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

	@Override
	public List<PostDTO> getPostsByCategory(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		List<Post> posts = this.postRepo.findByCategory(category); 				
		List<PostDTO> postDtos = posts.stream().map((post)->this.modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDTO> getPostsByUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDTO> postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDTO> getAllPosts() {
		List<Post> allPosts = this.postRepo.findAll();
		List<PostDTO> postDtos = allPosts.stream().map((post)-> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public PostDTO getPost(Integer postId) {
		Optional<Post> post = this.postRepo.findById(postId);
		return this.modelMapper.map(post, PostDTO.class);
	}

}
