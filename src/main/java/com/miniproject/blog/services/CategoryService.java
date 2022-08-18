package com.miniproject.blog.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.miniproject.blog.entities.Category;
import com.miniproject.blog.payloads.CategoryDTO;
import com.miniproject.blog.payloads.SearchDTO;

public interface CategoryService {
	//create
	CategoryDTO createCategory(CategoryDTO categoryDTO);
	
	//get categories
    List<CategoryDTO> getCategories(int pageNumber, int pageSize);
    
    // get one category
    CategoryDTO getCategory(Integer categoryId);
    
    //update
    CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId);
    
    // delete
    void deleteCategory(Integer categoryId);
    
        	
	
    
}
