package com.rays.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rays.bean.UserBean;
import com.rays.model.UserModel;

@WebServlet("/MyProfileCtl")
public class MyProfileCtl extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	

		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");

		if (user == null) {
			request.setAttribute("errorMsg", "Session expire plz login again");
			RequestDispatcher rd = request.getRequestDispatcher("LoginView.jsp");
			rd.forward(request, response);
		}
		request.setAttribute("bean", user);
		RequestDispatcher rd = request.getRequestDispatcher("MyProfileView.jsp");
		rd.forward(request, response);

		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		HttpSession session=request.getSession();
		
		
		
		UserBean bean=new UserBean();
		UserModel model=new UserModel();
		
	String firstName=request.getParameter("firstName");
	String lastName=request.getParameter("lastName");
	String login=request.getParameter("login");
	String password=request.getParameter("password");
	String dob=request.getParameter("dob");
	int id=Integer.parseInt(request.getParameter("id"));
	
	try {
		
	bean.setFirstName(firstName);
	bean.setLastName(lastName);
	bean.setLogin(login);
	bean.setPassword(password);
	bean.setDob(sdf.parse(dob));
	bean.setId(id);
	
	model.update(bean);	
	session.setAttribute("user", bean);
	request.setAttribute("successMsg", "User Update  Sucessfully");
	
	} catch (Exception e) {
		request.setAttribute("errorMsg", e.getMessage());

		e.printStackTrace();
	}
	request.setAttribute("bean", bean);
	RequestDispatcher rd = request.getRequestDispatcher("MyProfileView.jsp");
	rd.forward(request, response);

	
	}
	
	



		
		

	}


