package com.hurontg.mars.mvc;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import com.hurontg.common.exception.AppException;
import com.hurontg.common.util.Constants;

public class BaseController {
	/**
	 * 
	 */
	private XLogger logger = XLoggerFactory.getXLogger(BaseController.class
			.getName());

	/**
	 * 
	 * @param me
	 * @param req
	 * @return
	 */
	@ExceptionHandler(AppException.class)
	public String appExceptionHandler(AppException de,
			HttpServletRequest req) {
		logger.error(de.getMessage(), de);

		req.setAttribute("exception", de);
		return "error";
	}

	/**
	 * 
	 * @param ex
	 * @param req
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception ex, HttpServletRequest req) {
		logger.error(ex.getMessage(), ex);

		req.setAttribute("exception", ex);
		return "error";
	}

	/**
	 * 
	 * @param re
	 * @param req
	 * @return
	 */
	@ExceptionHandler(RuntimeException.class)
	public String runtimeExceptionHandler(RuntimeException re,
			HttpServletRequest req) {
		logger.error(re.getMessage(), re);

		req.setAttribute("exception", re);
		return "error";
	}

	/**
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				Constants.DATE_PICTURE);
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));

		binder.registerCustomEditor(String.class,
				new StringTrimmerEditor(false));
	}

}
