package com.rays.util;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.StyledEditorKit.BoldAction;

public class DataValidator {
	
	public static boolean signUpValidation(HttpServletRequest request) {
		
		boolean isValid=true;
		
		if(request.getParameter("firstName")=="") {
			request.setAttribute("firstName", "firstName is required");
			 isValid=false;
		}
		if(request.getParameter("lastName")=="") {
			request.setAttribute("lastName", "lastName is required");
			 isValid=false;
		}
		if(request.getParameter("login")=="") {
			request.setAttribute("login", "login is required");
			 isValid=false;
		}
		if(request.getParameter("password")=="") {
			request.setAttribute("password", "password is required");
			 isValid=false;
		}
		if(request.getParameter("dob")=="") {
			request.setAttribute("dob", "dob is required");
			 isValid=false;
		}

		return isValid;
		
	}

}
