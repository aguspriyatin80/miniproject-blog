package com.miniproject.blog.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproject.blog.entities.Category;
import com.miniproject.blog.payloads.CategoryDTO;
import com.miniproject.blog.repositories.CategoryRepo;
import com.miniproject.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
    CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;


	 @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = this.modelMapper.map(categoryDTO, Category.class);
        Category addedCategory = this.categoryRepo.save(category);
        return this.modelMapper.map(addedCategory, CategoryDTO.class);
    }



	@Override
	public List<CategoryDTO> getCategories() {		
		List<Category> categories = this.categoryRepo.findAll();
		List<CategoryDTO> categoryDtos = categories.stream().map((cat)->this.modelMapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());
		return categoryDtos;
	}
}
