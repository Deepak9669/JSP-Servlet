package com.rays.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rays.bean.UserBean;
import com.rays.model.UserModel;

@WebServlet("/ChangePasswordCtl")
public class ChangePasswordCtl extends HttpServlet {

	private String newPassword;
	private String oldPassword;

	@Override
	protected void doGet(HttpServletRequest reqeques, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("ChangePasswordView.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String conformPassword = request.getParameter("ConformPassword");

		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");
		System.out.println();

		if (user == null) {
			request.setAttribute("errorMsg", "Session expired. Please login again.");
			RequestDispatcher rd = request.getRequestDispatcher("LoginView.jsp");
			rd.forward(request, response);
		}

		if (!newPassword.equals(conformPassword)) {
			request.setAttribute("errorMsg", "New and confirm password do not match.");
		} else {

			try {
				UserModel model = new UserModel();
				
				System.out.println(user.getLogin());
				model.changePassword(oldPassword, newPassword, user.getLogin());
				System.out.println(user.getLogin());

				request.setAttribute("successMsg", "Password changed successfully");

			} catch (Exception e) {

				request.setAttribute("errorMsg", e.getMessage());

				e.printStackTrace();

			}
			RequestDispatcher rd = request.getRequestDispatcher("ChangePasswordView.jsp");
			rd.forward(request, response);
		}
	}
}
