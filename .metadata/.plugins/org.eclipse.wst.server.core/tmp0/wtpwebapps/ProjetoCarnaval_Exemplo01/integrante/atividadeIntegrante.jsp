<%@ page import="negocio.Usuario" %>
<%@ page import="negocio.Entidade" %>
<%@ page import="negocio.Integrante" %>
<%@ page import="negocio.Atividade" %>
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
List<Entidade> listaAtividades = null;
if(request.getAttribute("listaAtividades") != null){
	listaAtividades = (List<Entidade>) request.getAttribute("listaAtividades");
}
%>

<div class="container">
	
	<div>
	<h1>Área da Escola de Samba</h1>
	
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
	<br>
	<form action="ControlaIntegrante" method="get">
		<input type="submit" class="btn btn-info" value="Voltar">
	</form>
	<br><br>
	
	<hr>
	<form class="form-horizontal" action="ControlaIntegrante" method="post" id="form-escolaSamba">
		
		<input type="hidden" name="action" value="cadastrarAtividade">
		  
		   
		  
		   <div class="form-group">
		    <label for="cpf" class="col-sm-2 control-label">Atividades:</label>
		    <div class="col-sm-10">
		      <select multiple name="atividade[]" class="form-control">
		      <%for(Entidade entidade: listaAtividades){ 
		      		Atividade atividade = (Atividade) entidade;
		      		
		      %>
				  <option value="<%= atividade.getId()%>"><%= atividade.getAcao().getNome() %></option>
			  <%} %>
				
				</select>
		    </div>
		  </div>
		  
		  
		  
	  		
	  	<br>
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		      <button type="submit" class="btn btn-primary">Cadastrar Atividades</button>
		    </div>
		  </div>
	</form>
	
	<hr>
	<h3>Atividades Cadastradas</h3>
	<hr>
	<div >
	<table class="table table-hover">
  		<thead>
  		<th>Id</th>
  		<th>Atividade</th>
  		<th>Ação</th>
  		</thead>
  		<tbody>  		
	  		<tr>
	  			<td>1</td>  
	  			<td>Mestre Sala</td>
	  			<td><a href=""><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a> 
	  			</td>
	  		</tr>
	  		<tr>
	  			<td>2</td>  
	  			<td>Confeccionador de instrumentos</td>
	  			<td><a href=""><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a> 
	  			</td>
	  		</tr>
  		</tbody>
	</table>
	</div>
</div>


</body>
</html>