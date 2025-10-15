<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		String successMsg = (String) request.getAttribute("successMsg");
		String errorMsg = (String) request.getAttribute("errorMsg");
		UserBean bean = (UserBean) request.getAttribute("bean");
	%>
	<%@include file="Header.jsp"%>
	<div align="center">
		<h3>My Profile</h3>
		<%
		if(errorMsg!=null){
		
		%>
		<h2 style="color: red"><%=errorMsg%></h2>
		<%
		}
		%>
		<%
			if (successMsg != null) {
		%>
		<h2 style="color: green"><%=successMsg%></h2>
		<%
			}
		%>

		<form action="MyProfileCtl" method="post">

			<table>
			<tr>
			<td><input type="hidden"name="id"value="<%=bean.getId()%>"></td>
			</tr>
				<tr>

					<th>First Name</th>
					<td><input type="text" name="firstName"
						value="<%=bean.getFirstName()%>"></td>
				</tr>
				<tr>
					<th>Last Name</th>
					<td><input type="text" name="lastName"
						value="<%=bean.getLastName()%>"></td>
				</tr>
				<tr>
					<th>Login</th>
					<td><input type="email" name="login"
						value="<%=bean.getLogin()%>"></td>
				</tr>
				<tr>
					<th>Password</th>
					<td><input type="password" name="password"
						value="<%=bean.getPassword()%>"></td>
				</tr>
				<tr>
					<th>Dob</th>
					<td><input type="date" name="dob" value="<%=bean.getDob()%>"></td>

				</tr>
				<tr>
					<th></th>
					<td><input type="submit" name="operation" value="Update"></td>
				</tr>

			</table>
		</form>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>