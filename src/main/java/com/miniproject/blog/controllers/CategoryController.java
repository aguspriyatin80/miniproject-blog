package com.miniproject.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniproject.blog.payloads.CategoryDTO;
import com.miniproject.blog.payloads.ErrorResponse;
import com.miniproject.blog.services.impl.CategoryServiceImpl;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {

	@Autowired
    CategoryServiceImpl categoryService;

    //create	
	@PostMapping("/categories")
	private ResponseEntity<CategoryDTO> saveCategory(@Valid @RequestBody CategoryDTO categoryDTO){
          CategoryDTO newCategory = this.categoryService.createCategory(categoryDTO);
          return new ResponseEntity<>(newCategory, HttpStatus.CREATED);

    }
	
	//get all
	@GetMapping("/categories")
	private ResponseEntity<List<CategoryDTO>> getCategories(){
		List<CategoryDTO> categories = this.categoryService.getCategories();
		return ResponseEntity.ok(categories);
	}
	
	// get one category
	@GetMapping("/categories/{categoryId}")
	public ResponseEntity<CategoryDTO> getCategory(@PathVariable ("categoryId") Integer id){
		CategoryDTO categoryDto = this.categoryService.getCategory(id);
		return new ResponseEntity<CategoryDTO>(categoryDto,HttpStatus.OK);
	}
}
