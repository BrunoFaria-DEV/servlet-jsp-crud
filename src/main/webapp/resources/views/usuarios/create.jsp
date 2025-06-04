<%@ page import="br.edu.cba.ifmt.Model.City" %> 
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/styles.css">
	<title>Cadastrar Usuário</title>
</head>
<body>

    <div class="form-container">
        <div class="header-with-button space-between">
            <h2>Cadastrar Usuário</h2>
            <a class="button-link" href="${pageContext.request.contextPath}/usuarios">Voltar</a>
        </div>

        <form action="${pageContext.request.contextPath}/usuarios/store" method="POST">
            <div class="form-group">
                <label for="nome">Nome:</label>
                <input type="text" id="nome" name="nome" required>
            </div>

            <div class="form-group">
                <label for="email">E-mail:</label>
                <input type="email" id="email" name="email" required>
            </div>
            
            <div class="form-group">
                <label for="cpf">CPF:</label>
                <input type="text" id="cpf" name="cpf" required maxlength="14">
            </div>
            
            <div class="form-group">
                <label for="municipio_id">Município:</label>
                <select id="municipio_id" name="municipio_id" required>
                    <c:forEach var="city" items="${cities}">
                        <option value="${city.id}">${city.nome}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <button type="submit" class="submit-button">Enviar</button>
            </div>
        </form>
    </div>
    
</body>
</html>