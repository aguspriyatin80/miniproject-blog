package com.miniproject.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniproject.blog.payloads.ErrorResponse;
import com.miniproject.blog.payloads.UserDTO;
import com.miniproject.blog.repositories.UserRepo;
import com.miniproject.blog.services.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/v1")
public class UserController {
	
	@Autowired
	UserServiceImpl userService;
		
	@Autowired
	UserRepo userRepo;
	
	@PostMapping("/users")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
		UserDTO createdUser = this.userService.createUser(userDTO);
		System.out.println(createdUser.toString());    
		return new ResponseEntity<UserDTO>(createdUser,HttpStatus.CREATED);
	}
	
	@GetMapping("/users")	 
	ResponseEntity<List<UserDTO>> getUsers(){
		List<UserDTO> users = this.userService.getUsers();
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<UserDTO> getUser(@PathVariable("userId") Integer id){
		UserDTO userDto = this.userService.getUser(id);		
		return new ResponseEntity<UserDTO>(userDto,HttpStatus.OK);
	}
	
	@PutMapping("/users/{userId}")
	@PreAuthorize("hasRole('NORMAL') or hasRole('ADMIN')")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDto, @PathVariable("userId") Integer id){
		UserDTO updatedUser = this.userService.updateUser(userDto,id);
		return new ResponseEntity<UserDTO>(updatedUser,HttpStatus.OK);
	}
		
	@DeleteMapping("/users/{userId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ErrorResponse> deleteUser(@PathVariable("userId") Integer id){
		this.userService.deleteUser(id);	
		return new ResponseEntity<ErrorResponse>(new ErrorResponse("user with id = " + id + " deleted succesfully", true), HttpStatus.OK);
	}
}
