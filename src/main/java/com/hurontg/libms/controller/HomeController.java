package com.hurontg.mars.mvc;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hurontg.common.util.Utils;
import com.hurontg.mars.domain.Authority;

@Controller
public class HomeController extends BaseController {
	/*
	 * 
	 */
	public static final String ADMINISTRATOR_HOME = "/s/xad/home";	
	public static final String MEMBER_HOME = "/s/xmd/home";
	public static final String RECRUITER_HOME = "/s/xrd/home";
	
	/**
	 * 
	 */
	private XLogger logger = XLoggerFactory.getXLogger(HomeController.class.getName());

	/**
	 * Home page, user role based
	 * 
	 */
	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String getHomePage(Model model) throws Exception {
		String userHome = HomeController.HomPageUtil.getHomePageByRole();
		if (userHome == null) {
			logger.error("No role found for user");
			throw new AccessDeniedException("User Role not found");
		}

		return "forward:/" + userHome;
	}
	
	/**
	 * This is also called from UserSessionController
	 * @author anila
	 *
	 */
	public static class HomPageUtil {
		/**
		 * xad : admin xmd : member xrd : recruiter
		 * 
		 * @return
		 */
		public static String getHomePageByRole() {
			if (Utils.isUserInRole(Authority.ROLE_ADMINISTRATOR)) {
				return ADMINISTRATOR_HOME;
			} else if (Utils.isUserInRole(Authority.ROLE_MEMBER)) {
				return MEMBER_HOME;
			} else if (Utils.isUserInRole(Authority.ROLE_RECRUITER)) {
				return RECRUITER_HOME;
			} else {
				throw new AccessDeniedException("User Role not found");
			}
		}

	}

}
