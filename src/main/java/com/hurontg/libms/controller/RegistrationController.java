package com.hurontg.mars.mvc;

import javax.inject.Inject;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.hurontg.common.exception.AppServiceException;
import com.hurontg.mars.domain.AuthUser;
import com.hurontg.mars.security.AuthenticationService;
import com.hurontg.mars.security.SignInUtils;
import com.hurontg.mars.service.UserService;

@Controller
@RequestMapping("/k/registration")
public class RegistrationController extends BaseController {

	@Inject
	private UserService userSvc;
	
	@Inject
	private AuthenticationService authenticationSvc;

	private final ProviderSignInUtils providerSignInUtils;

	@Inject
	public RegistrationController(ConnectionFactoryLocator connectionFactoryLocator,
		                    UsersConnectionRepository connectionRepository) {
		this.providerSignInUtils = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);
	}
	
	/**
	 * 
	 */
	private XLogger logger = XLoggerFactory.getXLogger(RegistrationController.class.getName());

	/**
	 * Check if this email is already in use
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/emailallowed" }, method = RequestMethod.GET)
	public ResponseEntity<String> isEmailAlreadyRegistered(@RequestParam(value = "email") String email)
			throws Exception {
		ResponseEntity<String> re = null;
		try {
			Boolean inUse = userSvc.isEmailAlreadyRegistered(email);
			re = new ResponseEntity<String>(inUse ? "Y" : "N", HttpStatus.OK);
		} catch (AppServiceException e) {
			logger.catching(e);
			re = new ResponseEntity<String>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return re;
	}

	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String newRegistration(@RequestParam(value = "firstname") String firstName,
			@RequestParam(value = "lastname") String lastName, @RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password, 
			@RequestParam(value = "profiletype") String profiletype,
			Model model) throws Exception {
		try {
			registerUser(firstName, lastName, email, password, profiletype);
			model.addAttribute("registrationSuccessful", new Boolean(true));
		} catch (AppServiceException e) {
			logger.catching(e);
			model.addAttribute("registrationFailed", "Error(s) occured while trying to register");
		}

		return "login-form";
	}
	
	@RequestMapping(value = { "/social" }, method = RequestMethod.GET)
	public String newRegistrationSocial(WebRequest request, Model model) throws Exception {
		String view = "redirect:/home";
		try {			
				AuthUser user = userSvc.registerSocialUser(providerSignInUtils, request);
				SocialUserDetails currentUser = authenticationSvc.loadUserByUserId(user.getUsername());				
				SignInUtils.signin(currentUser);							
		} catch (AppServiceException e) {
			logger.catching(e);
			model.addAttribute("registrationFailed", "Error(s) occured while trying to register");
			view = "login-form";
		} catch (RuntimeException e) {
			logger.catching(e);
			model.addAttribute("registrationFailed", "Error(s) occured while trying to register");
			view = "login-form";
		}

		return view;
	}
	
	private void validateNewRegistrationParameters(String firstName, String lastName, String email, String password) {

	}
	
	private AuthUser registerUser(String firstName, String lastName, String email, String password, String profiletype) {
		validateNewRegistrationParameters(firstName, lastName, email, password);
		return userSvc.createUser(firstName, lastName, email, password, profiletype);
	}
}
