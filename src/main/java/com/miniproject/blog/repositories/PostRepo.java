package com.miniproject.blog.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.miniproject.blog.entities.Category;
import com.miniproject.blog.entities.Post;
import com.miniproject.blog.entities.User;

public interface PostRepo extends JpaRepository<Post,Integer>{
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	Page<Post> findByUserId(Integer userId, Pageable pageable);
}
