package com.miniproject.blog.services;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.miniproject.blog.payloads.RegisterRequest;
import com.miniproject.blog.payloads.UserDTO;

public interface UserService {

	// register
	UserDTO registerNewUser(RegisterRequest registerRequest);
	
	
	// create user
	UserDTO createUser(UserDTO userDTO);
	
	//get users
	
    List<UserDTO> getUsers();
    
    // get one user
    UserDTO getUser(Integer userId);
    
    //update
    UserDTO updateUser(UserDTO UserDTO, Integer UserId);
    
    // delete
    void deleteUser(Integer UserId);
}
