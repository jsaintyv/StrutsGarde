package org.garde.actions;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;

public class AbstractStrutsAction implements ServletRequestAware,
ServletResponseAware {
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;

	}

	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;

		System.out.println("<parameters " + arg0.getMethod() + " - "
				+ arg0.getRequestURI() + "?" + arg0.getQueryString() + ">");
		System.out.println("[" + ActionContext.getContext().getName()  + "]");
		
		Enumeration en = request.getParameterNames();
		while (en.hasMoreElements()) {
			String name = (String) en.nextElement();
			System.out.println(name + "=" + request.getParameter(name));

		}
		System.out.println("</parameters>");
	}
	
	public String getActionValue() {
		return request.getParameter("action:" + ActionContext.getContext().getName());
	}
}
