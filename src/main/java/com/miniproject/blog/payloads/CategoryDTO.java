package com.miniproject.blog.payloads;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Data
public class CategoryDTO implements Serializable{
	
	private Integer categoryId;

//    @NotBlank
//    @Size(min=4, message = "Minimum size for category title is 4")    
    private String categoryTitle;

    private String categoryDescription;   
}
