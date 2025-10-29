package com.rays.cookies;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteCookies")
public class DeleteCookies extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// loginId parameter se cookie name le rahe hain
		String name = request.getParameter("loginId");

		response.setContentType("text/html");

		// Browser me se saari cookies nikal lo
		Cookie[] cookies = request.getCookies();

		boolean found = false;

		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals(name)) {
					c.setMaxAge(0); // delete cookie
					c.setPath("/Dynamic-Web-Project"); // same path dena jaruri hai
					response.addCookie(c);
					found = true;
					response.getWriter().println("<h3>Cookie '" + name + "' deleted successfully ✅</h3>");
					break;
				}
			}
		}

		if (!found) {
			response.getWriter().println("<h3>Cookie not found ❌</h3>");
		}
	}
}
