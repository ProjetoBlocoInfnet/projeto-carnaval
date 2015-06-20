<%@ page import="negocio.Usuario" %>
<%@ page import="negocio.Entidade" %>
<%@ page import="negocio.Integrante" %>
<%@ page import="negocio.Atividade" %>
<%@ page import="negocio.Acao" %>
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

%>

<div class="container">
	
	<div>
	<h1>Área do Administrador</h1>
	
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
	<form action="ControlaCarnaval" method="get">
		<input type="submit" class="btn btn-info" value="Voltar">
	</form>
	<br><br>
	
	<hr>
	<form class="form-horizontal" action="ControlaCarnaval" method="post" id="form-quesito">
		
		<input type="hidden" name="action" value="cadastrarQuesito">
		  
		   <div class="form-group">
		    <label for="escolaSamba" class="col-sm-2 control-label">Escola:</label>
		    <div class="col-sm-10">
		      <select name="escolaSamba" class="form-control">		      
				  <option value="1">Salgueiro</option>
			  	  <option value="2">Mangueira</option>
				</select>
		    </div>
		  </div> 
		  
		  <div class="form-group">
		    <label for="quesito" class="col-sm-2 control-label">Quesito:</label>
		    <div class="col-sm-10">
		      <select name="quesitos" class="form-control">		      
				  <option value="1">Quesito 1</option>
			  	  <option value="2">Quesito 2</option>
				</select>
		    </div>
		  </div>  
		  
		 <div class="form-group">
		    <label for="jurado" class="col-sm-2 control-label">Jurado:</label>
		    <div class="col-sm-10">
		      <select name="jurados" class="form-control">		      
				  <option value="1">Jurado 1</option>
			  	  <option value="2">jurado 2</option>
				</select>
		    </div>
		  </div>
		 	
		 <div class="form-group">
		    <label for="pontuacao" class="col-sm-2 control-label">Pontuação:</label>
		    <div class="col-sm-10">
		      <input type="text" name="pontuacao" value="" class="form-control" placeholder="Pontuação">
		    </div>
		  </div>
		   
	  		
	  	<br>
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		      <button type="submit" class="btn btn-primary">Incluir Pontuação</button>
		    </div>
		  </div>
	</form>
	
	<hr>
	<h3>Quadro de Pontuações</h3>
	<hr>
	<div >
	<table class="table table-hover">
  		<thead>
  		<th>Escola de Samba</th>  		
  		<th>Quesito </th>
  		<th>Jurado</th>
  		<th>Pontuação</th>
  		<th>Ação</th>
  		</thead>
  		<tbody>	      
	  		<tr>
	  			<td>Salgueiro</td>
	  			<td>Quesito 1</td>
	  			<td>Jurado 1</td>
	  			<td>20</td>
	  			<td>
	  			<a href="ControlaCarnaval?idCarnaval&idJurado=<&acao=excluirJurado"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a> 
	  			</td>
	  		</tr>
		  
  		</tbody>
	</table>
	</div>
</div>


</body>
</html>