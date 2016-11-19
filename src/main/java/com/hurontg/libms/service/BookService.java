package com.hurontg.libms.service;

import java.util.List;

import com.hurontg.libms.domain.Book;

public interface BookService {
	List<Book> findAllBooks();

	Book findBookById(Long id);

	void addNewBook(Book book);

	void updateBook(Book book);

	void deleteBook(Long id);
}
