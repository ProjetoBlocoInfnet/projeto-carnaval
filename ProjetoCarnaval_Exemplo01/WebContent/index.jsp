<%@page import="java.util.List"%>
<%@page import="negocio.Galera"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<title>Projeto Carnaval</title>
</head>
<body>

	<%
		List<Galera> listaGalera = null;
		if(request.getAttribute("minhaGalera") != null){
			listaGalera = (List<Galera>)request.getAttribute("minhaGalera");
		}
	%>

	<form action="ControlaAcesso" method="post">	
		<input type="hidden" name="tela" value="index.jsp">
			
		<input type="text" class="form-control" name="usuario"> 
		<input type="password" class="form-control" name="senha">
		<input type="submit" value="Entrar" class="btn btn-default">
			
		<hr>
		
		<h2>Galera</h2>
		<select name="galera">
			<%
			if(listaGalera != null){
				for(Galera g : listaGalera){%>
					<option><%=g.getNome()%></option>
				<%}
			}%>
		</select>			
	</form>

</body>
</html>