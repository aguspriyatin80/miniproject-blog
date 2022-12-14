package com.miniproject.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.miniproject.blog.entities.Category;
import com.miniproject.blog.entities.Post;
import com.miniproject.blog.entities.User;
import com.miniproject.blog.exceptions.ResourceNotFoundException;
import com.miniproject.blog.payloads.PostDTO;
import com.miniproject.blog.payloads.PostResponse;
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
		
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User Id",userId));
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id",categoryId));
		
		Post post = modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost = postRepo.save(post);
		
		return modelMapper.map(newPost, PostDTO.class);
		
	}

	@Override
	public List<PostDTO> getPostsByCategory(Integer categoryId) {
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		List<Post> posts = postRepo.findByCategory(category); 				
		List<PostDTO> postDtos = posts.stream().map((post)->modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public PostResponse getPostsByUser(Integer userId, Integer pageNumber, Integer pageSize) {	
		Pageable p = PageRequest.of(pageNumber, pageSize);
		
		Page<Post> pagePost = postRepo.findByUserId(userId, p);
		
		List<Post> allPosts = pagePost.getContent();
		
		List<PostDTO> postDtos = allPosts.stream().map((post)->modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {			

		Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending(); 
		
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		
		Page<Post> pagePost = postRepo.findAll(p);
		
		List<Post> allPosts = pagePost.getContent();
		
		List<PostDTO> postDtos = allPosts.stream().map((post)-> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
	}

	@Override
	public PostDTO getPost(Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post Id", postId));
		return modelMapper.map(post, PostDTO.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post Id", postId));
		postRepo.delete(post);
	}

	@Override
	public PostDTO updatePost(PostDTO postDto, Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post Id", postId));
		post.setTitle(postDto.getTitle());
		if(postDto.getImageName() == null) {
			post.setImageName("default.png");
		} else {
			post.setImageName(postDto.getImageName());
		}
		post.setContent(postDto.getContent());
		Post updatedPost = postRepo.save(post);
		return modelMapper.map(updatedPost, PostDTO.class);
	}

}
