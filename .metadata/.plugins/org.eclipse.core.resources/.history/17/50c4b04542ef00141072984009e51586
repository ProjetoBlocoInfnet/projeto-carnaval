<%@ page import="negocio.Usuario" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Projeto Carnaval</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	
	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
	
	<!-- Latest compiled and minified JavaScript -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
	
</head>
<body>

<div class="container">
	
	<div>
	<h1>Área da Escola de Samba</h1>
	</div>	
	<form action="Logout" method="post">	
		  <div class="form-group">
		      <button type="submit" class="btn btn-primary">Logout</button>
		  </div>
	</form>	
	<div class="alert alert-info" role="alert">
	<%
		Usuario usuario = (Usuario)request.getSession().getAttribute( "usuario" );
	%>
	<strong>Usuário: </strong>
	
	<% 
		out.println( usuario.getLogin() ); 
	%>
	
	<br/>
	<strong>Perfil: </strong><%=usuario.getPerfil().nomeBonito%><br/>
	</div>
	<hr/>
	<br>
	<ul class="nav nav-tabs">
  		<li role="presentation"><a href="ControlaIntegrante">Cadastrar Integrante</a></li>
  		<li role="presentation"><a href="CadastraEnsaio.html">Cadastrar Ensaio</a></li>
	</ul>
	<br><br>
	
</div>


</body>
</html>