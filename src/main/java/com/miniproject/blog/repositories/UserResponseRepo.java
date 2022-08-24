package com.miniproject.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miniproject.blog.entities.UserRegister;

public interface UserResponseRepo extends JpaRepository<UserRegister,Integer> {

}
