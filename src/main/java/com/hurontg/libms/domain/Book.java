package com.hurontg.libms.domain;

import javax.validation.constraints.Size;

public class Book {
	private Long id;	
	@Size(min = 5, max = 10)
	private String title;
	@Size(min = 5, max = 10)
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
