package com.hurontg.libms.customtag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.hurontg.libms.domain.Book;

public class LibmsCustomTag2 extends SimpleTagSupport {
	private String domainObjectName;
	private String bookView = "<div class=\"well\"><div>Book Id: <<id>></div><div>Author: <<author>></div><div>Title: <<title>></div></div>";
	
	public void setDomainObjectName(String domainObjectName) {
		this.domainObjectName = domainObjectName;
	}

	public LibmsCustomTag2() {
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		try {
			Book book = (Book) getJspContext().findAttribute(domainObjectName);
			if (book != null) {
				getJspContext().getOut().write(bookView.replaceAll("<<id>>", book.getId().toString())
					.replaceAll("<<author>>", book.getAuthor())
					.replaceAll("<<title>>", book.getTitle()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			// stop page from loading further by throwing SkipPageException
			// throw new SkipPageException("Exception in formatting " + number +
			// " with format " + format);
		}
	}

}