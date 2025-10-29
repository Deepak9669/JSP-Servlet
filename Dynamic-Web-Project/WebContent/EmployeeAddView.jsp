
<%@page import="com.rays.bean.EmployeeBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="Header.jsp"%>

	<%
		String successMsg = (String) request.getAttribute("successMsg");
		String errorMsg = (String) request.getAttribute("errorMsg");
		EmployeeBean bean = (EmployeeBean) request.getAttribute("bean");
	%>

	<div align="center">
		<%
			if (bean != null && bean.getId() > 0) {
		%>
		<h3>Update Employee</h3>
		<%
			} else {
		%>
		<h3>Add Employee</h3>
		<%
			}
		%>

		<%
			if (successMsg != null) {
		%>
		<h3 style="color: green;"><%=successMsg%></h3>
		<%
			}
		%>

		<%
			if (errorMsg != null) {
		%>
		<h3 style="color: red;"><%=errorMsg%></h3>
		<%
			}
		%>

		<form action="EmployeeAddCtl.do" method="post">
			<input type="hidden" name="id"
				value="<%=bean != null ? bean.getId() : ""%>">
			<table>
				<tr>
					<th>Employee</th>
					<td><input type="text" name="employee"
						value="<%=bean != null ? bean.getEmployee() : ""%>"
						placeholder="enter employee"></td>
				</tr>
				<tr>
					<th>Salary</th>
					<td><input type="text" name="salary"
						value="<%=bean != null ? bean.getSalary() : ""%>"
						placeholder="enter salary"></td>
				</tr>
				<tr>
					<th>Login</th>
					<td><input type="email" name="login"
						value="<%=bean != null ? bean.getLogin() : ""%>"
						placeholder="enter your login"></td>
				</tr>
				<tr>
					<th>Password</th>
					<td><input type="password" name="password"
						value="<%=bean != null ? bean.getPassword() : ""%>"
						placeholder="enter your password"></td>
				</tr>
				<tr>
					<th>Status</th>
					<td><input type="text" name="status"
						value="<%=bean != null ? bean.getStatus() : ""%>"
						placeholder="enter your status"></td>
				</tr>
				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=bean != null ? "update" : "save"%>"></td>
				</tr>
			</table>

		</form>

	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>
<