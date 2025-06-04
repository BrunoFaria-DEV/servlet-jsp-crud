<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Página Inicial</title>
   	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/styles.css">
</head>
<body>
    <h1 class="h1-center">Página Inicial</h1>
    <h2 class="h2-center">Selecione a funcionalidade:</h2>

    <div class="center-container">
        <a class="button-link" href="${pageContext.request.contextPath}/usuarios">Usuários</a>
    </div>
</body>
</html>