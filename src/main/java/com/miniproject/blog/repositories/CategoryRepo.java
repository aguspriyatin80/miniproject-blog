package com.miniproject.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.miniproject.blog.entities.Category;
import com.miniproject.blog.payloads.SearchDTO;

public interface CategoryRepo extends JpaRepository<Category,Integer> {

	//JPQL
//	@Query("SELECT t FROM Category t WHERE LOWER(t.categoryTitle) LIKE LOWER(CONCAT('%', ?1,'%'))")
	
	// NATIVE QUERY
	@Query(value = "SELECT * FROM categories WHERE LOWER(name) LIKE LOWER(CONCAT('%', ?1,'%'))", nativeQuery = true)
	List<Category> findCategoryByNameLike(String name);

	// DERIVED QUERY
//	List<Category> findByCategoryTitleIgnoreCase(String name);
}
