package com.miniproject.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproject.blog.entities.User;
import com.miniproject.blog.payloads.UserDTO;
import com.miniproject.blog.repositories.UserRepo;
import com.miniproject.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
//	@Override
//	public UserDTO createUser(UserDTO userDTO) {
//		User user = this.modelMapper.map(userDTO, User.class);
//		User addedUser = this.userRepo.save(user);
//		return this.modelMapper.map(addedUser, UserDTO.class);
//	}
	
	@Override
	public UserDTO createUser(UserDTO userDTO) {
		User user = this.dtoToUser(userDTO);
		User savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
	}
	
	public User dtoToUser(UserDTO userDTO) {
//		User user = new User();
//		user.setId(userDTO.getId());
//		user.setName(userDTO.getName());
//		user.setEmail(userDTO.getEmail());
//		user.setPassword(userDTO.getPassword());
//		user.setAbout(userDTO.getAbout());
		User user = this.modelMapper.map(userDTO, User.class);
		return user;
	}
	
	public UserDTO userToDto(User user) {
//		UserDTO userDTO = new UserDTO();
//		userDTO.setId(user.getId());
//		userDTO.setName(user.getName());
//		userDTO.setEmail(user.getEmail());
//		userDTO.setPassword(user.getPassword());
//		userDTO.setAbout(user.getAbout());
		UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
		return userDTO;
	}
	
	

}
