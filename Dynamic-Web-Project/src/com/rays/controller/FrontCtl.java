package com.rays.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "FrontCtl", urlPatterns = "*.do")
public class FrontCtl implements Filter {

//	Called once when the filter is being removed or the web application is shutting down.
	@Override
	public void destroy() {
	}

//	Called for every request that matches the filter mapping. Used to process/filter requests/responses.
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;

		HttpSession session = request.getSession();

	session.setMaxInactiveInterval(1*60);   // with the help of this method the session expire will be set in second

		if (session.getAttribute("user") == null) {
			request.setAttribute("errorMsg", "your session expired plz login again");
			RequestDispatcher rd = request.getRequestDispatcher("LoginView.jsp");
			rd.forward(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}

//	Called once when the filter is created. Used for initialization.
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
