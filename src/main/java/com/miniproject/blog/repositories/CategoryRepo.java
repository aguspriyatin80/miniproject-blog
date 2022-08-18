package com.miniproject.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.miniproject.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer> {

	//JPQL
//	@Query("SELECT t FROM Category t WHERE LOWER(t.categoryTitle) LIKE :key")
//	List<Category> findCategoryByNameLike(@Param("key") String name);
	
	// SQL NATIVE QUERY
	@Query(value="SELECT * FROM categories WHERE LOWER(name) LIKE :key", nativeQuery=true)
	List<Category> findCategoryByNameLike(@Param("key") String name);

	// DERIVED QUERY
//	List<Category> findByCategoryTitleIgnoreCaseContaining(String keyword);
	
}
