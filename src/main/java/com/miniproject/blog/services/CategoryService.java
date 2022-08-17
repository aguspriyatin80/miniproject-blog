package com.miniproject.blog.services;

import java.util.List;

import com.miniproject.blog.entities.Category;
import com.miniproject.blog.payloads.CategoryDTO;

public interface CategoryService {
	//create
	CategoryDTO createCategory(CategoryDTO categoryDTO);
	
	//get all
    List<CategoryDTO> getCategories();
}
