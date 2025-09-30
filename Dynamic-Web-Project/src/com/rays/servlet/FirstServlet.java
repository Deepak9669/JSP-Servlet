package com.rays.servlet;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

 @WebServlet("/FirstServlet")//wild card mapping
public class FirstServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse respsponse) 
    		throws ServletException, IOException {
    	System.out.println("<----in doGet method----->");
    	respsponse.sendRedirect("FirstView.jsp");
    	
    	    }	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
            System.out.println("in doPost method");
         String firstName =  request.getParameter("firstName");
         String lastName =  request.getParameter("lastName");
         String login =  request.getParameter("login");
         String password =  request.getParameter("password");
         String dob =  request.getParameter("dob");
         String address =  request.getParameter("address");
         String pinNo =  request.getParameter("pinNo");
         
         System.out.println(firstName + "\n" +lastName+ "\n" +login+ "\n" +password +"\n"
        		+ dob + "\n" + address + "\n" + pinNo  );
       
    }
}

