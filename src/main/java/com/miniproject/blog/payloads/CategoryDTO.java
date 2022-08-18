package com.miniproject.blog.payloads;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CategoryDTO{
	
	private Integer categoryId;

    @NotBlank
    @Size(min=4, message = "Minimum size for category title is 4")    
    private String categoryTitle;

    private String categoryDescription;   
}
