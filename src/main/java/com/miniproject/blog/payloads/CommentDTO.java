package com.miniproject.blog.payloads;

public class CommentDTO {

	private Integer id;
	
	private String content;

	public CommentDTO(Integer id, String content) {
		super();
		this.id = id;
		this.content = content;
	}

	public CommentDTO() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
