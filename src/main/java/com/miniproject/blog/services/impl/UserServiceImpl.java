package com.miniproject.blog.services.impl;

import java.util.List;
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
		User user = modelMapper.map(userDTO, User.class);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
        User addedUser = userRepo.save(user);
        return modelMapper.map(addedUser, UserDTO.class);
	}

	@Override
	public List<UserDTO> getUsers() {
		List<User> users = userRepo.findAll();
		List<UserDTO> userDtos = users.stream().map((u)->modelMapper.map(u, UserDTO.class)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public UserDTO getUser(Integer userId) {
		User user = userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));
		return modelMapper.map(user, UserDTO.class);
	}

	@Override
	public UserDTO updateUser(UserDTO userDto, Integer userId) {
		
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setAbout(userDto.getAbout());
		
		User updatedUser = userRepo.save(user);

		return modelMapper.map(updatedUser, UserDTO.class);
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User", "User Id", userId));
		userRepo.delete(user);
	}

	@Override
	public UserDTO registerNewUser(RegisterRequest req) {
		User user = modelMapper.map(req, User.class);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		Role role = roleRepo.findById(AppConstants.NORMAL_USER).get();
		
		user.getRoles().add(role);
		User newUser = userRepo.save(user);
		
		return modelMapper.map(newUser, UserDTO.class);
	}


}
