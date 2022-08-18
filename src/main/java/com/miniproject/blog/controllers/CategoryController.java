package com.miniproject.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miniproject.blog.payloads.CategoryDTO;
import com.miniproject.blog.payloads.ErrorResponse;
import com.miniproject.blog.payloads.SearchDTO;
import com.miniproject.blog.repositories.CategoryRepo;
import com.miniproject.blog.services.impl.CategoryServiceImpl;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {

	@Autowired
    CategoryServiceImpl categoryService;
	
	@Autowired
	CategoryRepo categoryRepo;

    //create	
	@PostMapping("/categories")
	private ResponseEntity<CategoryDTO> saveCategory(@Valid @RequestBody CategoryDTO categoryDTO){
          CategoryDTO newCategory = this.categoryService.createCategory(categoryDTO);
          return new ResponseEntity<>(newCategory, HttpStatus.CREATED);

    }
	
	//get all
	@GetMapping("/categories")
	private ResponseEntity<List<CategoryDTO>> getCategories(
			@RequestParam(value="pageNumber", defaultValue="0", required=false) int pageNumber,
			@RequestParam(value="pageSize", defaultValue="5", required=false) int pageSize)
	{
		List<CategoryDTO> categories = this.categoryService.getCategories(pageNumber,pageSize);
		return ResponseEntity.ok(categories);
	}
	
	// get one category
	@GetMapping("/categories/{categoryId}")
	public ResponseEntity<CategoryDTO> getCategory(@PathVariable ("categoryId") Integer id){
		CategoryDTO categoryDto = this.categoryService.getCategory(id);
		return new ResponseEntity<CategoryDTO>(categoryDto,HttpStatus.OK);
	}
	
	// update category
	@PutMapping("/categories/{categoryId}")
	public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable("categoryId") Integer id){
		CategoryDTO updatedCategory = this.categoryService.updateCategory(categoryDTO, id);
		return new ResponseEntity<CategoryDTO>(updatedCategory,HttpStatus.OK);
	}
	
	// delete
	@DeleteMapping("/categories/{categoryId}")
	public ResponseEntity<ErrorResponse> deleteCategory(@Valid @PathVariable("categoryId") Integer id) {
		this.categoryService.deleteCategory(id);
		return new ResponseEntity<ErrorResponse>(new ErrorResponse("category with id = " + id + " deleted succesfully", true), HttpStatus.OK);
	}
	
	// search category by name
	@PostMapping("/categories/search/name")
	public ResponseEntity<List<CategoryDTO>> searchPostsByTitle(@RequestBody SearchDTO searchDto, String keywords){
		List<CategoryDTO> result = this.categoryService.searchPosts(searchDto.getKeyword());
		return new ResponseEntity<List<CategoryDTO>>(result,HttpStatus.OK);
	}
	
}
