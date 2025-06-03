<%@page import="java.util.List"%>
<%@page import="br.edu.cba.ifmt.Model.User" %>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Pagina inicial:</h1>
	<h2>Selecione a funcionalidade:</h2>
	<% List<User> users = (List<User>) request.getAttribute("users"); %>
	<% for(User user : users) { %>
	<span><%= user.getNome()%></span><br>
	<% } %>
</body>
</html>