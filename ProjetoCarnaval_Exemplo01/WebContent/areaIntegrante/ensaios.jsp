<%@ page import="negocio.Usuario" %>
<%@ page import="negocio.Entidade" %>
<%@ page import="negocio.Ensaio" %>
<%@ page import="negocio.EscolaSamba" %>
<%@ page import="enumerator.Grupos" %>
<%@ page import="java.util.Set" %>
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
List<Ensaio> listaEnsaios = null;
Set<Entidade> listaEscolas = null;
if(request.getAttribute("listaEnsaio") != null){
	listaEnsaios = (List<Ensaio>) request.getAttribute("listaEnsaio");
}
if(request.getAttribute("listaEscolaConsulta") != null){
	listaEscolas = (Set<Entidade>) request.getAttribute("listaEscolaConsulta");
}
%>

<div class="container">
	
	<div>
	<h1>Área do Integrante</h1>
	
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
	<a href="indexIntegrante.jsp"><button type="button" class="btn btn-default">Voltar</button></a>
	<br><br>
		
	<form action="ControlaAreaIntegrante" method="post">
		<input type="hidden" name="action" value="consultarEnsaioEscola">
		<div class="row">  
		  <div class="col-lg-6">
		    <div class="input-group">
		      <select name="escolaId" id="escola" class="form-control">  
		      <option value="0">selecione a escola de Samba --></option>
		      <% if(listaEscolas != null && listaEscolas.size() > 0)
		      {
		      for(Entidade entidade : listaEscolas){ 
		      		EscolaSamba escola = (EscolaSamba) entidade;
		      %>
  					<option value="<%=escola.getId() %>"><%=escola.getNome() %></option>
  			<% 	}
		      }%>	
		     </select>
		      <span class="input-group-btn">
		        <input type="submit" class="btn btn-default" type="button" value="Consultar Ensaio">
		      </span>
		    </div><!-- /input-group -->
		  </div><!-- /.col-lg-6 -->
		</div><!-- /.row -->
	</form>
	<hr>
	
	<%
	if(listaEnsaios != null){
		if(listaEnsaios.size() > 0 ){ %>
		<div >
		<h4>Lista de Ensaios das Escolas de Samba</h4>
		<hr>
		<table class="table table-hover">
	  		<thead>
	  		<th>Id</th>
	  		<th>Data </th>
	  		<th>Escola</th>
	  		</thead>
	  		<tbody>
	  		<% for(Ensaio ensaio : listaEnsaios){ 
	  			//Ensaio ensaio = (Ensaio) entidade; 
	  		%>
		  		<tr>
		  			<td><%=ensaio.getId() %></td>  
		  			<td><%=ensaio.getData() %></td>
		  			<td><%=ensaio.getEscola().getNome() %></td>  	  			
		  		</tr>
	  		<% } %>
	  		</tbody>
		</table>
		</div>
		
		<% } else{ %>
		
		<% }
		
	} %>
	
</div>


</body>
</html>