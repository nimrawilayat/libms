package com.hurontg.libms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hurontg.libms.domain.Book;
import com.hurontg.libms.persistence.BookDao;

@Controller
public class HomeController {
	
	List<Book> books = new ArrayList<Book>();
	
	@Autowired
	private BookDao dao;
	
	@RequestMapping(value = {"/", "/home", "/home2"}, method = RequestMethod.GET)
	public String getHomePage(Model model) {
		model.addAttribute("books", books);
		return "home";
	}
	
	@RequestMapping(value = {"/somepage"}, method = RequestMethod.GET)
	public String getPage2(
			@RequestParam(name = "param1") String param1, 
			@RequestParam(name = "param2", required = false, defaultValue = "A Fine Default Value to use") String param2,
			Model model) {

		
		String s1 = "Hello World";
		
		model.addAttribute("pm1", param1);
		model.addAttribute("p2", param2);		
		
		return "home";
	}

	@RequestMapping(value = {"/books"}, method = RequestMethod.GET)
	public String getBooks(Model model) {
		
		Book book = new Book(30L, "C++ Rocks", "Mitchell Waite");
		model.addAttribute("book", book);

		model.addAttribute("books", books);
					
		
				
		return "home";
	}
	
	@RequestMapping(value = {"/book/new"}, method = RequestMethod.GET)
	public String getBookForm(Model model) {
				
		return "book-form";
	}

	@RequestMapping(value = {"/books"}, method = RequestMethod.POST)
	public String createNewBook(Book book) {
		System.out.println("//////////////////////////////////////////////////");
		System.out.println(book.getId() + ": " + book.getTitle() + ": " + book.getAuthor());
		System.out.println("//////////////////////////////////////////////////");
		
		saveNewBook(book);
		return "redirect:/home";
	}
	
	@PostConstruct
	public void getBooksFromDatabase() {
		books.add(new Book(1L, "Temple Tiger", "Jim Corbett"));
		books.add(new Book(2L, "C++ Primer Plus", "Mitchell Waite"));
		books.add(new Book(3L, "Just Java", "Mr. Coffee"));
		
	}

	private void saveNewBook(Book book) {
		books.add(book);
	}

	


}


// @Component

// MVC - Model View Controller