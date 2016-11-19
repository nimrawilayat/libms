package com.hurontg.libms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hurontg.libms.domain.Book;
import com.hurontg.libms.persistence.BookDao;

@Service
public class BookServiceImpl implements BookService {

	private BookDao bookDao;

	@Autowired
	public BookServiceImpl(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	@Override
	public List<Book> findAllBooks() {
		List<Book> books = bookDao.getBooks();
		return books;
	}

	@Override
	public Book findBookById(Long id) {
		Book book = bookDao.getBookById(id);
		return book;
	}

	@Override
	public void addNewBook(Book book) {
		bookDao.addBook(book);
	}

	@Override
	public void updateBook(Book book) {
		bookDao.updateBook(book);
	}

	@Override
	public void deleteBook(Long id) {
		bookDao.deleteBook(id);
	}

}
