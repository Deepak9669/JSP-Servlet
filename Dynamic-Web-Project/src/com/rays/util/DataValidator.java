package com.rays.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.StyledEditorKit.BoldAction;

public class DataValidator {

	public static boolean signUpValidation(HttpServletRequest request) {

		boolean isValid = true;

		if (request.getParameter("firstName") == "") {
			request.setAttribute("firstName", "firstName is required");
			isValid = false;
		} else if (!(request.getParameter("firstName").length() >= 3
				&& request.getParameter("firstName").length() <= 12)) {
			request.setAttribute("firstName", "Your firstName name must be between 3 and 12 characters long.");
			isValid = false;
		}
		if (request.getParameter("login") == "") {
			request.setAttribute("login", "login is required");
			isValid = false;
		} else if (!request.getParameter("login").endsWith("@gmail.com")) {
			request.setAttribute("login", "invalid login format");
			isValid = false;
		}
		if (request.getParameter("password") == "") {
			request.setAttribute("password", "password is required");
			isValid = false;
		} else if (!(request.getParameter("password").length() >= 8
				&& request.getParameter("password").length() <= 12)) {
			request.setAttribute("password", "Your password must be between 8 and 12 characters long.");
			System.out.println("password is required");
			isValid = false;
		}
		if (request.getParameter("dob") == "") {
			request.setAttribute("dob", "dob is required");
			isValid = false;
		} else if (!(request.getParameter("dob") == "")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			try {
				Date dob = sdf.parse(request.getParameter("dob"));
				Date now = new Date();
				int age = now.getYear() - dob.getYear();
				if (!(age >= 18 && age <= 60)) {
					request.setAttribute("dob", "you are not eligible for this web site");
					isValid = false;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return isValid;

	}
	public static boolean employeeValidation(HttpServletRequest request) {

		boolean isValid = true;

		if (request.getParameter("employee") == null || request.getParameter("employee").trim().isEmpty()) {
			request.setAttribute("employee", "Employee name is required");
			isValid = false;
		} else if (!(request.getParameter("employee").length() >= 3
				&& request.getParameter("employee").length() <= 12)) {
			request.setAttribute("employee", "Your employee name must be between 3 and 12 characters long.");
			isValid = false;
		}
				return isValid;
	}
}