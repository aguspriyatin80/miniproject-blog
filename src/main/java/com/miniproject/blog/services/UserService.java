package com.miniproject.blog.services;

import java.util.List;

import com.miniproject.blog.payloads.UserDTO;
import com.miniproject.blog.payloads.UserDTO;

public interface UserService {

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
