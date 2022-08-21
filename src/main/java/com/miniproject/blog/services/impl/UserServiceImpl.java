package com.miniproject.blog.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.miniproject.blog.config.AppConstants;
import com.miniproject.blog.entities.Role;
import com.miniproject.blog.entities.User;
import com.miniproject.blog.exceptions.ResourceNotFoundException;
import com.miniproject.blog.payloads.RegisterRequest;
import com.miniproject.blog.payloads.UserDTO;
import com.miniproject.blog.repositories.RoleRepo;
import com.miniproject.blog.repositories.UserRepo;
import com.miniproject.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Override
	public UserDTO createUser(UserDTO userDTO) {
		User user = this.modelMapper.map(userDTO, User.class);
        User addedUser = this.userRepo.save(user);
        return this.modelMapper.map(addedUser, UserDTO.class);
	}

	@Override
	public List<UserDTO> getUsers() {
		List<User> users = this.userRepo.findAll();
		List<UserDTO> userDtos = users.stream().map((u)->this.modelMapper.map(u, UserDTO.class)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public UserDTO getUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));
		return this.modelMapper.map(user, UserDTO.class);
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO, Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));
		this.modelMapper.map(userDTO, user);
		User updatedUser = this.userRepo.save(user);
		return this.modelMapper.map(updatedUser, UserDTO.class);
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User", "User Id", userId));
		this.userRepo.delete(user);
	}

	@Override
	public UserDTO registerNewUser(UserDTO userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		
		user.getRoles().add(role);
		User newUser = this.userRepo.save(user);
		
		return this.modelMapper.map(newUser, UserDTO.class);
	}

	
}
