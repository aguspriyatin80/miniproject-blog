package com.miniproject.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.miniproject.blog.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

	
	@Query(
	        value = "SELECT * FROM role WHERE id NOT IN (SELECT role_id FROM users_roles WHERE user_id = ?1)", 
	        nativeQuery = true
	)
	List<Role> getUserNotRoles(Integer userId);
}
