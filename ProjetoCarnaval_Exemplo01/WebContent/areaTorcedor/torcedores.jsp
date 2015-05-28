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


<div class="container">
	
	<div>
	<h1>Área do Torcedor</h1>	
	<hr/>
	<form action="Logout" method="post">
		  <div class="form-group">
		      <button type="submit" class="btn btn-primary">Logout</button>
		  </div>
	</form>
	<br>
	<a href="ControlaTorcedor?tela=areaTorcedor"><button type="button" class="btn btn-default">Voltar</button></a>
	<br><br>
		
	<form action="ControlaAreaIntegrante" method="post">
		<input type="hidden" name="action" value="consultarEnsaioEscola">
		<div class="row">  
		  <div class="col-lg-6">
		    <div class="input-group">
		      <select name="escolaId" id="escola" class="form-control">  
		      <option>selecione a escola de Samba --></option>
		      	
		     </select>
		      <span class="input-group-btn">
		        <input type="submit" class="btn btn-default" type="button" value="Consultar Ensaio">
		      </span>
		    </div><!-- /input-group -->
		  </div><!-- /.col-lg-6 -->
		</div><!-- /.row -->
	</form>
	<hr>
	
	
		<div >
		<h4>Lista de Torcedores das Escolas de Samba</h4>
		<hr>
		<table class="table table-hover">
	  		<thead>
	  		<th>Id</th>
	  		<th>Nome </th>
	  		<th>sexo</th>
	  		</thead>
	  		<tbody>
	  		
		  		<tr>
		  			<td>1</td>  
		  			<td>Torcedor I</td>
		  			<td>Masculino</td>  	  			
		  		</tr>
		  		<tr>
		  			<td>2</td>  
		  			<td>Torcedor II</td>
		  			<td>Feminino</td>  	  			
		  		</tr>
	  		</tbody>
		</table>
		</div>
		
	
</div>


</body>
</html>