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

import com.hurontg.libms.domain.Book;
import com.hurontg.libms.service.BookService;

@Controller
public class BookController {

	List<Book> books = new ArrayList<Book>();

	@Autowired
	private BookService bookService;

	@RequestMapping(value = { "/books" }, method = RequestMethod.GET)
	public String getBooks(Model model) {

		List<Book> bookList = bookService.findAllBooks();
		model.addAttribute("books", bookList);
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


}

// @Component

// MVC - Model View Controller