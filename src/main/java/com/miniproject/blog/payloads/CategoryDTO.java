package com.miniproject.blog.payloads;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

public class CategoryDTO{
	
	private Integer categoryId;

    @NotBlank
    @Size(min=4, message = "Minimum size for category title is 4")    
    private String categoryTitle;

    private String categoryDescription;

	public CategoryDTO(Integer categoryId,
			@NotBlank @Size(min = 4, message = "Minimum size for category title is 4") String categoryTitle,
			String categoryDescription) {
		super();
		this.categoryId = categoryId;
		this.categoryTitle = categoryTitle;
		this.categoryDescription = categoryDescription;
	}

	public CategoryDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
    
    
}
