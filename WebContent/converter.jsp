<%@page import="it.itsvita.byte19.ufc9.utils.Utils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Private Converter</title>
</head>
<body>

	<h1>Benvenuto al tuo Private Converter Gentile <mark><%= session.getAttribute(Utils.PARAM_USERNAME) %></mark></h1>
	
	<form action="converter" method="POST">
	
		<label>Da m/s a Km/h</label>
		<input type="radio" name="conversion" value="<%= Utils.CONV_MS_TO_KMH %>" id="<%=Utils.CONV_MS_TO_KMH%>"/>
		
		<br><br>
		
		<label>Da Km/h a m/s</label>
		<input type="radio" name="conversion" value="<%= Utils.CONV_KMH_TO_MS  %>" id="<%= Utils.CONV_KMH_TO_MS  %>"/>
		
		<br><br>
		
		<label>Da Miglia a Metri</label>
		<input type="radio" name="conversion" value="<%= Utils.CONV_MILES_TO_METERS %>" id="<%= Utils.CONV_MILES_TO_METERS %>"/>
		
		<br><br>
		
		<label>Da Metri a Miglia</label>
		<input type="radio" name="conversion" value="<%= Utils.CONV_METERS_TO_MILES %>" id="<%= Utils.CONV_METERS_TO_MILES %>"/>
		
		<br><br>
		
		<input type="number" name="value" placeholder="Valore da Convertire"/>
		
		<input type="submit" value="Converti"/>
		
		<%
			if (request.getAttribute("conversionResult") != null){
		%>
			 <p>Il Risultato della tua Conversione è:  <mark><%= request.getAttribute("conversionResult") %></mark></p>
		<%
			  }
		%>
	
	</form>
	
	<br><br>
	
	<form action="logout" method="POST">
		<input type="submit" value="Logout"/>
	</form>

</body>
</html>