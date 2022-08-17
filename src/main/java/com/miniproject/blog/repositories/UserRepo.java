package com.miniproject.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miniproject.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
