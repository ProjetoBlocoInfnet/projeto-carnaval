<%@ page import="negocio.Usuario" %>
<%@ page import="negocio.Entidade" %>
<%@ page import="negocio.EscolaSamba" %>
<%@ page import="enumerator.Grupos" %>
<%@ page import="java.util.List" %>
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

<%
Usuario usuario = (Usuario) session.getAttribute("usuario");
List<Entidade> listaEscolas = null;
if(request.getAttribute("listaEscola") != null){
	listaEscolas = (List<Entidade>) request.getAttribute("listaEscola");
}
%>

<div class="container">
	
	<div>
	<h1>Área do Admnistrador</h1>
	
	<form action="Logout" method="post">
		  <div class="form-group">
		      <button type="submit" class="btn btn-primary">Logout</button>
		  </div>
	</form>
	<div class="alert alert-info" role="alert">
	<strong>Usuário: </strong><%=usuario.getLogin() %><br>
	<strong>Perfil: </strong><%=usuario.getPerfil().nomeBonito %><br>
	</div>	
	<hr/>
	<a href="indexAdmin.jsp"><button type="button" class="btn btn-default">Voltar</button></a>
	<br><br>
	<form action="ControlaEscolaSamba" method="post">
		<input type="hidden" name="action" value="telaCadastro">
		<input type="submit" class="btn btn-info" value="Cadastrar Escola de Samba">
	</form>
	
	<br>
	
	<form action="ControlaEscolaSamba" method="post">
		<input type="hidden" name="action" value="consultar">
		<div class="row">  
		  <div class="col-lg-6">
		    <div class="input-group">
		      <input type="text" name="nome" class="form-control" placeholder="Consultar por...">
		      <span class="input-group-btn">
		        <input type="submit" class="btn btn-default" type="button" value="Consultar">
		      </span>
		    </div><!-- /input-group -->
		  </div><!-- /.col-lg-6 -->
		</div><!-- /.row -->
	</form>
	<hr>
	
	<% if(listaEscolas.size() > 0 ){ %>
	<div >
	<table class="table table-hover">
  		<thead>
  		<th>Id</th>
  		<th>Nome Escola	</th>
  		<th>Endereço Quadra</th>
  		<th>Endereço Barracão</th>
  		<th>Telefone</th>
  		<th>E-mail</th>
  		<th>CNPJ</th>
  		<th>Grupo</th>
  		<th>Ação</th>
  		</thead>
  		<tbody>
  		<% for(Entidade entidade : listaEscolas){ 
  			EscolaSamba escola = (EscolaSamba) entidade;  	
  			
  		%>
	  		<tr>
	  			<td><%=escola.getId() %></td>  
	  			<td><%=escola.getNome() %></td>  		
	  			<td><%=escola.getEnderecoQuadra() %></td>  		
	  			<td><%=escola.getEnderecoBarracao() %></td> 
	  			<td><%=escola.getTelefone() %></td>  
	  			<td><%=escola.getEmail() %></td>  	
	  			<td><%=escola.getCnpj() %></td> 
	  			<td><%= escola.getGrupoAtual()%></td> 		
	  			<td><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> | <span class="glyphicon glyphicon-trash" aria-hidden="true"></span></td>
	  		</tr>
  		<% } %>
  		</tbody>
	</table>
	</div>
	
	<% } else{ %>
	
	<% } %>
	
</div>


</body>
</html>