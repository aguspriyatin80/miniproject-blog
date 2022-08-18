package com.miniproject.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
public class UserDTO {

	private int id;
		
	@NotBlank
	private String name;
	
	@Email
	private String email;
	
	@NotNull
	private String password;
	
	private String about;

	
}
