package com.miniproject.blog.payloads;

public class ErrorResponse {

	private String message;
	private Boolean status;
	public ErrorResponse(String message, Boolean status) {
		super();
		this.message = message;
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public ErrorResponse() {
	}
	
	
}
