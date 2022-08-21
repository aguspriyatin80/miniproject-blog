package com.miniproject.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RegisterRequest {

	@NotBlank
	private String name;
		
	@Email
	private String email;
	
	@NotNull
	private String password;
	
	private String about;

	public RegisterRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RegisterRequest(@NotBlank String name, @Email String email, @NotNull String password, String about) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	
}
