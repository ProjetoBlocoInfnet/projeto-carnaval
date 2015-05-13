<%@ page import="negocio.Entidade" %>
<%@ page import="negocio.Usuario" %>

<%@ page import="negocio.Torcedor" %>
<%@ page import="negocio.EscolaSamba" %>

<%@ page import="dao.TorcedorDAO" %>
<%@ page import="dao.EscolaSambaDAO" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Projeto Carnaval</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>

</head>
<body>

	<div class="container">

		<div>
			<h1>Área do Torcedor</h1>
		</div>
		<form action="Logout" method="post">
			<div class="form-group">
				<button type="submit" class="btn btn-primary">Logout</button>
			</div>
		</form>
		
		<%
			Usuario usuario = (Usuario)request.getSession().getAttribute( "usuario" );
		
		%>
		
		<div class="alert alert-info" role="alert">
			<strong>Usuário: </strong><% out.println( usuario.getLogin() );%><br>
			<strong>Perfil: </strong><%=usuario.getPerfil().nomeBonito %><br>
		</div>
		<hr />
		<br>

		<nav>
		<ul class="nav nav-tabs">
			<li><a data-toggle="tab" href="#escola">Dados da Escola de
					Samba</a></li>
			<li><a data-toggle="tab" href="#torcedores">Outros
					Torcedores</a></li>
		</ul>
		<br />
		<br />
		</nav>
		<div class="tab-content">
			<%
				EscolaSamba escola = ( (Torcedor) usuario ).getEscolaSamba();
			%>
		
			<div class="tab-pane active" id="escola">
				

				<table class="table table-hover">
					<thead>
						<th>Nome Escola</th>
						<th>Endereço da Quadra</th>
						<th>Endereço do Barracão</th>
						<th>Grupo</th>
					</thead>
					<tbody>
						<tr>
							<td><%=escola.getNome() %></td>
							<td><%=escola.getEnderecoQuadra()%></td>
							<td><%=escola.getEnderecoBarracao()%></td>
							<td><%=escola.getGrupoAtual().nomeBonito %></td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<div class="tab-pane" id = "torcedores">
				<ul>
				<%
					Torcedor t;
				
					for ( Entidade e : new TorcedorDAO().obterTodos() ) {
						t = (Torcedor)e;
						out.println( "<li>" + t.getNome() + "</li>" );
					}
				%>
				</ul>
			</div>
			
		</div>
	</div>
</body>
</html>