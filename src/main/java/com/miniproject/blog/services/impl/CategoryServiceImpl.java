package com.miniproject.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.miniproject.blog.entities.Category;
import com.miniproject.blog.exceptions.ResourceNotFoundException;
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
	public List<CategoryDTO> getCategories(int pageNumber, int pageSize) {
		
		Pageable p = PageRequest.of(pageNumber, pageSize);
		
		Page<Category> pageCategories = this.categoryRepo.findAll(p);
		
		List<Category> categories = pageCategories.getContent();
		
		List<CategoryDTO> categoryDtos = categories.stream().map((cat)->this.modelMapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());
		return categoryDtos;
	}

	@Override
	public CategoryDTO getCategory(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		return this.modelMapper.map(category, CategoryDTO.class);

	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		this.modelMapper.map(categoryDTO, category);
		Category updatedCategory = this.categoryRepo.save(category);
		return this.modelMapper.map(updatedCategory, CategoryDTO.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		this.categoryRepo.delete(category);
	}

	@Override
	public List<CategoryDTO> searchPosts(String keyword) {
//		List<Category> categories = this.categoryRepo.findByCategoryTitleIgnoreCaseContaining(keyword);
		List<Category> categories = this.categoryRepo.findCategoryByNameLike("%" + keyword + "%");
		List<CategoryDTO>  categoryDtos = categories.stream().map((cat)-> this.modelMapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());
		return categoryDtos;
	}

}
