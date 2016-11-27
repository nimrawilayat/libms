package com.hurontg.libms.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Book {
	private Long id;
	
	@NotNull
	@Size(min=5, max=45, message="The Title property name must be between {min} and {max}")
	private String title;
	
	@NotNull
	@Size(min=2, max=45, message="Author name must be between {min} and {max}")
	private String author;

	private Long version;
	
	public Book() {
	}

	public Book(Long id, String title, String author) {
		this(id, title, author, 1L);
	}
	
	public Book(Long id, String title, String author, Long version) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.version = version;
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

	
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", version=" + version + "]";
	}

	
}
