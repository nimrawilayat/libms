package com.hurontg.mars.mvc;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/s/xad")
public class AdminFunctionsController extends BaseController {
	/**
	 * 
	 */
	private XLogger logger = XLoggerFactory.getXLogger(AdminFunctionsController.class.getName());

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public @ResponseBody String getHomePage(Model model) throws Exception {
		doStuff();
		return "Welcome to Admin functions home";
	}

	private void doStuff() {
		PasswordEncoder pe = new BCryptPasswordEncoder();
		String encodedPassword = pe.encode("password");
		logger.error(encodedPassword);
		
	}

}
