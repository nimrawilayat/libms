package com.hurontg.libms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hurontg.libms.domain.Book;
import com.hurontg.libms.service.BookService;

@Controller
public class BookController {

	List<Book> books = new ArrayList<Book>();

	@Autowired
	private BookService bookService;

	@RequestMapping(value = { "/books" }, method = RequestMethod.GET)
	public String getBooks(Model model) {

//		List<Book> bookList = bookService.findAllBooks();
//		model.addAttribute("books", bookList);
		return "books";
	}

	@RequestMapping(value = { "/book/new" }, method = RequestMethod.GET)
	public String getBookForm(Model model) {
		return "book-form";
	}

	@RequestMapping(value = { "/book/new/v2" }, method = RequestMethod.GET)
	public String getBookForm_v2(Model model) {
		Book book = new Book();
		model.addAttribute("book", book);
		return "book-form-v2";
	}
	
	@RequestMapping(value = { "/books" }, method = RequestMethod.POST)
	public String createNewBook_v2(@Valid Book book, Errors error) {
		if (error.hasErrors()) {
			return "book-form-v2";
		}

		bookService.addNewBook(book);
		return "redirect:/books";
	}

	@RequestMapping(value = { "/books" }, method = RequestMethod.PUT)
	public String updateBook(@Valid Book book, Errors error) {
		if (error.hasErrors()) {
			return "book-edit";
		}

		bookService.updateBook(book);
		return "redirect:/books";
	}

	
	@RequestMapping(value = { "/book/{id}/edit" }, method = RequestMethod.GET)
	public String getBookFormForEdit(@PathVariable("id") Long id, Model model) {
		
		Book book = bookService.findBookById(id);
		model.addAttribute("book", book);
		
		return "book-edit";
	}
	
	@RequestMapping(value = { "/book/{id}/delete" }, method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long id) {		
		bookService.deleteBook(id);
		
		return "redirect:/books";
	}
	
	//
	// The following methods return json (Javascript Object Notation) representation 
	//
	
	/*
	 * Book b = new Book(23L, "C++ Primer", "M Waite");
	 * 
	 * "bookList": [
	 * 		{"id": "23", "title": "C++ Primer", "author": "M Waite"},
	 * 		{"id": "123", "title": "Python", "author": "M Waite 22"},
	 * 		{"id": "223", "title": "C++ Primer2222", "author": "M Waite 433"}
	 * 	]
	 */
	@RequestMapping(value = { "/v1/books" }, method = RequestMethod.GET)
	@ResponseBody 
	public List<Book> getBooksAsJson(Model model) {
		List<Book> bookList = bookService.findAllBooks();		
		
		return bookList;
	}

	@RequestMapping(value = { "/v1/book/{id}" }, method = RequestMethod.GET)
	@ResponseBody
	public Book getBookAsJson(@PathVariable("id") Long id, Model model) {
		
		Book book = bookService.findBookById(id);
		
		return book;
	}
	
	@RequestMapping(value = { "/v1/books" }, method = RequestMethod.POST)
	@ResponseBody 
	public String createBookV1(@Valid Book book, Errors error) {
		if (error.hasErrors()) {
			return "ERROR";
		}

		bookService.addNewBook(book);
		return "OK";
	}
	
	@ResponseBody
	@RequestMapping(value = { "/v1/books/{id}" }, method = RequestMethod.PUT)	 
	public String updateBookV1(@PathVariable("id") Long id, Book book) {
		bookService.updateBook(book);
		return "OK";
	}
	
	@ResponseBody
	@RequestMapping(value = { "/v1/books/{id}" }, method = RequestMethod.DELETE)	 
	public String deleteBookV1(@PathVariable("id") Long id) {
		bookService.deleteBook(id);
		return "OK";
	}
	
}

// @Component

// MVC - Model View Controller