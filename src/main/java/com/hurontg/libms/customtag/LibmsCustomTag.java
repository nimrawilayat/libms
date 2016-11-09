package com.hurontg.libms.customtag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class LibmsCustomTag extends SimpleTagSupport {
	private Integer count;

	public LibmsCustomTag() {
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public void doTag() throws JspException, IOException {
		try {
			if (count == null || count.equals(0)) {
				getJspContext().getOut().write(
						"<p>This is a simple custom tag. It demonstrates that writing custom tags to generate html is actually easy</p>");
			} else {
				for (int i = 1; i <= count; i++) {
					getJspContext().getOut().write("<p>(" + i
							+ ") This is a simple custom tag. It demonstrates that writing custom tags to generate html is actually easy</p>");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// stop page from loading further by throwing SkipPageException
			// throw new SkipPageException("Exception in formatting " + number +
			// " with format " + format);
		}
	}

}