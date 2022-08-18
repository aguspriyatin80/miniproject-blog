package com.miniproject.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproject.blog.entities.Category;
import com.miniproject.blog.entities.User;
import com.miniproject.blog.payloads.CategoryDTO;
import com.miniproject.blog.payloads.UserDTO;
import com.miniproject.blog.repositories.UserRepo;
import com.miniproject.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO updateUser(UserDTO UserDTO, Integer UserId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(Integer UserId) {
		// TODO Auto-generated method stub
		
	}
	
}
