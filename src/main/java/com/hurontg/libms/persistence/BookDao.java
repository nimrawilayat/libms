package com.hurontg.libms.persistence;

import java.util.List;

import com.hurontg.libms.domain.Book;

public interface BookDao {
	// Data Access Object	
	List<Book> getBooks();
	
	void addBook(Book book);
	
}
