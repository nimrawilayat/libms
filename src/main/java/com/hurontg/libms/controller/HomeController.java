package com.hurontg.libms.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hurontg.libms.persistence.BookDao;

@Controller
public class HomeController {
	
	private BookDao bookDao;
	
	@Autowired
	public HomeController(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHomePage1() {
		return "home";
	}
	
	@RequestMapping(value = "/home1", method = RequestMethod.GET)
	public String getHomePage2(Model model) {
		model.addAttribute("name", "Jack Beanstalk");
		model.addAttribute("currentDate", new Date());
		
		model.addAttribute("books", bookDao.getBooks());
		return "home";
	}

}
