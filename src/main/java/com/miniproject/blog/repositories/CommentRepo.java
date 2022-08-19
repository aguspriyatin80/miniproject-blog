package com.miniproject.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miniproject.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
