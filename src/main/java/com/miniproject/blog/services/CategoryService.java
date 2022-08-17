package com.miniproject.blog.services;

import com.miniproject.blog.entities.Category;
import com.miniproject.blog.payloads.CategoryDTO;

public interface CategoryService {
	//create
	CategoryDTO createCategory(CategoryDTO categoryDTO);
}
