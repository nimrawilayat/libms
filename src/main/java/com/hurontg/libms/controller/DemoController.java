package com.hurontg.libms.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hurontg.libms.domain.Book;
import com.hurontg.libms.persistence.BookDao;

//@Controller
public class DemoController {

	private BookDao bookDao;

	@Autowired
	public DemoController(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHomePage1() {
		return "home";
	}

	@RequestMapping(value = "/home1", method = RequestMethod.GET)
	public String getHomePage2(@RequestParam(value = "num", required = false) Long num,
			@RequestParam(value = "name", required = false) String name, Model model) {
		model.addAttribute("name", name);
		model.addAttribute("num", num);
		model.addAttribute("currentDate", new Date());

		model.addAttribute("books", bookDao.getBooks());
		return "home";
	}

	@RequestMapping(value = "/book/form", method = RequestMethod.GET)
	public String getBookForm(Model model) {
		model.addAttribute("book", new Book(null, "C++ Primer", "Mitchell Waite"));
		return "book-form";
	}

	@RequestMapping(value = "/book/form", method = RequestMethod.POST)
	public String saveBook(@Valid Book book, Errors errors) {
		if (errors.hasErrors()) {
			return "book-form";
		} else {
			return "redirect:/home1";
		}
	}

}
