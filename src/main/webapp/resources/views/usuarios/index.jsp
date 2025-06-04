<%@page import="java.util.List"%>
<%@page import="br.edu.cba.ifmt.Model.User" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/styles.css">
	<title>Usuarios</title>
</head>
<body>
	<h1>Gestão de Usuários</h1>

	<div class="header-with-button">
		<h2>Lista de Usuários</h2>
		<a class="button-link" href="${pageContext.request.contextPath}/usuarios/novo">Novo</a>
	</div>

	<table>
		<thead>
			<tr>
				<th>Nome</th>
				<th>Email</th>
				<th>CPF</th>
				<th>Município</th>
				<th>Ações</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${users}">
				<tr>
					<td>${user.nome}</td>
					<td>${user.email}</td>
					<td>${user.CPF}</td>
					<td>${user.city.nome}</td>
					<td>
						<a class="button-link" href="${pageContext.request.contextPath}/usuarios/editar/${user.id}">Editar</a>
						<form action="${pageContext.request.contextPath}/usuarios/excluir/${user.id}" method="POST" style="display:inline;">
							<button type="submit" onclick="return confirm('Deseja realmente excluir?')">Excluir</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>