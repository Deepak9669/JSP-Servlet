
package com.rays.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.bean.EmployeeBean;
import com.rays.bean.UserBean;
import com.rays.model.EmployeeModel;
import com.rays.model.UserModel;
import com.rays.util.DataValidator;

@WebServlet("/EmployeeAddCtl.do")
public class EmployeeAddCtl extends HttpServlet {
	

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = request.getParameter("operation");

		if (op != null) {
			if (!DataValidator.signUpValidation(request)) {
				RequestDispatcher rd = request.getRequestDispatcher("UserRegistrationView.jsp");
				rd.forward(request, response);
				return;

			}
		}

		super.service(request, response);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EmployeeModel model = new EmployeeModel();
		EmployeeBean bean = new EmployeeBean();

		String id = request.getParameter("id");
		System.out.println("id >>>>-- " + id);

		if (id != null) {
			try {
				bean = model.findById(Integer.parseInt(id));
				request.setAttribute("bean", bean);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		RequestDispatcher rd = request.getRequestDispatcher("EmployeeAddView.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = request.getParameter("operation");

		
		EmployeeBean bean = new EmployeeBean();
		EmployeeModel model = new EmployeeModel();

		String employee = request.getParameter("employee");
		String salary = request.getParameter("salary");
		String status = request.getParameter("status");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		try {
			bean.setEmployee(employee);
			bean.setSalary(salary);
			bean.setStatus(status);
			bean.setPassword(password);
			bean.setLogin(login);



			if (op.equals("update")) {
				bean.setId(Integer.parseInt(request.getParameter("id")));
				model.update(bean);
				request.setAttribute("successMsg", "User updated Successfully");
			} else {
				model.add(bean);
				request.setAttribute("successMsg", "User added Successfully");
			}

		} catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("EmployeeAddView.jsp");
		rd.forward(request, response);

	}

}
