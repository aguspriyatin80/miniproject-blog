package com.miniproject.blog.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniproject.blog.payloads.UserDTO;
import com.miniproject.blog.services.UserService;
import com.miniproject.blog.services.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	
	@Autowired
	UserServiceImpl userService;
	
	@PostMapping("/users")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
		UserDTO createdUser = this.userService.createUser(userDTO);
		System.out.println(createdUser.toString());
		return new ResponseEntity<UserDTO>(createdUser,HttpStatus.CREATED);
		
	}
}
