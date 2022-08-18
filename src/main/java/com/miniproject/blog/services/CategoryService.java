package com.miniproject.blog.services;

import java.util.List;


import com.miniproject.blog.payloads.CategoryDTO;

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
    
        	
    // search
    
    List<CategoryDTO> searchPosts(String keyword);
	
    
}
