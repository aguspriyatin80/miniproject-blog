package com.miniproject.blog.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.miniproject.blog.entities.User;

@RestController
@RequestMapping("/rest-client")
public class RestClient {

	private static final String URL = "http://localhost:9090/api/v1/users";
	
	@Autowired
	public RestTemplate restTemplate;
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers(){
		User[] users =  restTemplate.getForObject(URL, User[].class);
		return new ResponseEntity<>(Arrays.asList(users),HttpStatus.OK);		
	}
	
	@GetMapping("/users/{id}")
	private ResponseEntity<String> getSingleUser(@PathVariable int id) {
        String user = restTemplate.getForObject(URL+"/"+id, String.class, id);        
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
	@PostMapping("/users")
	public ResponseEntity<User> create(@RequestBody User newUser) {
		User createdUser = restTemplate.postForObject(URL, newUser, User.class);
		return new ResponseEntity<>(createdUser,HttpStatus.OK);
	}
	@PutMapping("/users/{id}")
	 private ResponseEntity<User> updateUser(@PathVariable("id") int userId, @RequestBody User user){
		User updatedUser = restTemplate.postForObject(URL, user, User.class, userId);
		return new ResponseEntity<>(updatedUser,HttpStatus.OK);
	}
}
