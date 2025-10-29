package com.rays.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

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

@WebServlet("/EmployeeListCtl.do")
public class EmployeeListCtl extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EmployeeModel model = new EmployeeModel();
		EmployeeBean bean = new EmployeeBean();
		
		int pageNo=1;
		int pageSize=5;

		try {
			List list = model.search(bean,pageNo,pageSize);
			List nextList=model.search(bean, pageNo+1, pageSize);
			request.setAttribute("nextList", nextList);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("EmployeeListView.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = request.getParameter("operation");
		EmployeeModel model = new EmployeeModel();
		EmployeeBean bean=new EmployeeBean();
		String[] ids = request.getParameterValues("ids");
		
		int pageNo=1;
		int pageSize=5;
		
		

		if (op.equals("delete")) {
			if (ids != null && ids.length > 0) {

				for (String id : ids) {
					try {
						model.delete(Integer.parseInt(id));
						request.setAttribute("successMsg", "record deleted sucessfully");
					} catch (Exception e) {

						e.printStackTrace();
					}
				}
			} else {
				request.setAttribute("errorMsg", "plz select at least one id");

			}
		}
		if(op.equals("search")) {
			bean.setEmployee(request.getParameter("employee"));
			bean.setSalary(request.getParameter("salary"));
			bean.setStatus(request.getParameter("status"));
			bean.setLogin(request.getParameter("login"));
			bean.setPassword(request.getParameter("password"));
			
		}
		
	
		
		if(op.equals("next")) {
			pageNo=Integer.parseInt(request.getParameter("pageNo"));
			pageNo++;
		}
		if(op.equals("previous")) {
			pageNo=Integer.parseInt(request.getParameter("pageNo"));
			pageNo--;
		}
		
		try {
			List list = model.search(bean,pageNo,pageSize);
			List nextList=model.search(bean, pageNo+1, pageSize);
			request.setAttribute("nextList", nextList);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("EmployeeListView.jsp");
		rd.forward(request, response);

	}


	}
