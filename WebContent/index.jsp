<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>WebLoginSecure</title>
</head>
<body>
	<h1>Welcome</h1>
	
	<form action="login" method="POST">
		<input type="text" name="username" placeholder="User Name"/>
		<input type="password" name="password" placeholder="Password"/>
		<input type="submit" value="Login"/>
	</form>
	
	<br><br>
	
	
    <%
		if (request.getAttribute("wrongPassword") != null){
	%>
		 <p style="color:Red">Your password is wrong or empty, please retry!</p>
	<%
		  }
	%>
	
	
</body>
</html>