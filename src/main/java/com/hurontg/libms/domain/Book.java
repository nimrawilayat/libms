package com.hurontg.libms.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Book {
	private Long id;
	
	@NotNull
	@Size(min=5, max=16)
	private String title;
	
	@NotNull
	@Size(min=2, max=26, message="Author name must be between {min} and {max}")
	private String author;

	public Book() {
	}

	public Book(Long id, String title, String author) {
		this.id = id;
		this.title = title;
		this.author = author;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
