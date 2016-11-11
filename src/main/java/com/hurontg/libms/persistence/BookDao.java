package com.hurontg.libms.persistence;

import java.util.List;

import com.hurontg.libms.domain.Book;

public interface BookDao {
	List<Book> getBooks();
	
	List<String> getProducts();
}
