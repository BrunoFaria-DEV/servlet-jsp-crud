<%@ page import="br.edu.cba.ifmt.Model.City" %> 
<%@ page import="br.edu.cba.ifmt.Model.User" %> 
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Editar Usuário</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/styles.css">
</head>
<body>

	<% User user = (User) request.getAttribute("user"); %>

    <div class="form-container">
        <div class="header-with-button space-between">
       		<h2>Editar Usuário</h2>
            <a class="button-link" href="${pageContext.request.contextPath}/usuarios">Voltar</a>
        </div>
        <form action="<%= request.getContextPath() %>/usuarios/update/<%= user.getId() %>" method="POST">
            <div class="form-group">
                <label for="nome">Nome:</label>
                <input type="text" id="nome" name="nome" required value="<%= user.getNome() %>">
            </div>

            <div class="form-group">
                <label for="email">E-mail:</label>
                <input type="email" id="email" name="email" required value="<%= user.getEmail() %>">
            </div>
            
            <div class="form-group">
                <label for="cpf">CPF:</label>
                <input type="text" id="cpf" name="cpf" required maxlength="14" value="<%= user.getCPF() %>">
            </div>
            
            <div class="form-group">
                <label for="municipio_id">Município:</label>
                <select id="municipio_id" name="municipio_id">
                    <%
                        List<City> cities = (List<City>) request.getAttribute("cities");
                        for(City city : cities) {
                    %>
                        <option value="<%= city.getId() %>" <%= city.getId() == user.getCity().getId() ? "selected" : "" %>>
                            <%= city.getNome() %>
                        </option>
                    <% } %>
                </select>
            </div>

            <div class="form-group">
                <button type="submit" class="submit-button">Salvar</button>
            </div>
        </form>
    </div>
    
</body>
</html>