<%@page import="it.itsvita.byte19.ufc9.utils.Utils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>WebLoginApp</title>
</head>
<body>

	<h1>Login Con Successo</h1>
	
	<h3>Benvenuto nel Sito Signor <mark> <%= session.getAttribute(Utils.PARAM_USERNAME)  %> </mark></h3>
	<h5>La sua Password è: <mark> <%= request.getAttribute(Utils.PARAM_PASSWORD) %></mark></h5>

	<br><br>
	<a href='converter.jsp'>Accedi Al Converter Segreto</a>
	<br><br>

	<form action="logout" method="POST">
		<input type="submit" value="LogOut"/>
	</form>

</body>
</html>