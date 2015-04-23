<%@page import="negocio.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Projeto Carnaval</title>
</head>
<body>
	<%
	Usuario user = null;
	if (request.getAttribute("user") != null){
		user = (Usuario)request.getAttribute("user");
	}
	%>
	<form action="ControlaAcesso" method="get">
		<input type="hidden" name="tela" value="main.jsp">
		<input type="submit" value="Exibir Usuário Logado">
		<hr>
		<h3><%=user != null ? user.obterMensagemAcesso() : ""%></h3>	
	</form>

</body>
</html>
