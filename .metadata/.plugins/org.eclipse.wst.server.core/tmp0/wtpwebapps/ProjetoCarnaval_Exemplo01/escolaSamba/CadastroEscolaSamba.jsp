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
	<link rel="stylesheet" href="datepicker/css/datepicker.css">
	
	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
	
	<!-- Latest compiled and minified JavaScript -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<script src="datepicker/js/bootstrap-datepicker.js"></script>
	
</head>
<body>

<%
Usuario usuario = (Usuario) session.getAttribute("usuario");
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
	<br>
	<form action="ControlaEscolaSamba" method="get">
		<input type="submit" class="btn btn-info" value="Voltar">
	</form>
	<br><br>
	
	<hr>
	<form class="form-horizontal" action="ControlaEscolaSamba" method="post" id="form-escolaSamba">
		
		<input type="hidden" name="action" value="cadastrar">
		  
		  <div class="form-group">
		    <label for="nome" class="col-sm-2 control-label">Nome</label>
		    <div class="col-sm-10">
		      <input type="text" name="nome" class="form-control" id="nome" placeholder="Nome">
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="endereco" class="col-sm-2 control-label">Endereço Quadra</label>
		    <div class="col-sm-10">
		      <input type="text" name="enderecoQuadra" class="form-control" id="endereco" placeholder="Endereço Quadra">
		    </div>
		  </div> 
		  
		   <div class="form-group">
		    <label for="endereco" class="col-sm-2 control-label">Endereço Barracao</label>
		    <div class="col-sm-10">
		      <input type="text" name="enderecoBarracao" class="form-control" id="endereco" placeholder="Endereço Barracao">
		    </div>
		  </div> 
		  
		  <div class="form-group">
		    <label for="cor" class="col-sm-2 control-label">Cor da Escola</label>
		    <div class="col-sm-10">		   
		      <input type="text" name="cor" class="form-control" id="cor" placeholder="Cor">
		    </div>
		  </div> 
		  
		   <div class="form-group">
		    <label for="dataFundacao" class="col-sm-2 control-label">Data de Fundação</label>
		    <div class="col-sm-10">
		      <input type="text" name="dataFundacao" class="form-control" id="dataFundacao" placeholder="Data de Fundação">		      
		      <script>
			      $(document).ready(function () {
			        $('#dataFundacao').datepicker({
			            format: "dd/mm/yyyy",
			            language: "pt-BR"
			        });
			      });
			    </script>
		    </div>
		  </div>
		  
		   <div class="form-group">
		    <label for="lema" class="col-sm-2 control-label">Lema</label>
		    <div class="col-sm-10">
		      <input type="text" name="lema" class="form-control" id="lema" placeholder="Lema">
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="filiacao" class="col-sm-2 control-label">Filiação</label>
		    <div class="col-sm-10">
		      <input type="text" name="filiacao" class="form-control" id="filiacao" placeholder="Filiação">
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="grupoAtual" class="col-sm-2 control-label">Grupo Atual</label>
		    <div class="col-sm-10">
		     <select name="grupoAtual" id="grupoAtual" class="form-control">
		      	<option value="1">Grupo Especial</option>
	        	<option value="2">Série A</option>
	        	<option value="3">Série B</option>
	       	 	<option value="4">Série C</option>
	        	<option value="5">Série D</option>
	       		<option value="6">Grupo de Avaliação</option>     
		     </select>
		    </div>
		  </div>
		  
		 	  
		   <div class="form-group">
		    <label for="email" class="col-sm-2 control-label">E-mail</label>
		    <div class="col-sm-10">		   
		      <input type="text" name="email" class="form-control" id="email" placeholder="E-mail">
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="telefone" class="col-sm-2 control-label">Telefone</label>
		    <div class="col-sm-10">		   
		      <input type="text" name="telefone" class="form-control" id="telefone" placeholder="Telefone">
		    </div>
		  </div>
		  
		  
		   <div class="form-group">
		    <label for="cnpj" class="col-sm-2 control-label">CNPJ</label>
		    <div class="col-sm-10">
		      <input type="text" name="cnpj" class="form-control" id="cnpj" placeholder="CNPJ">
		    </div>
		  </div>
		 
	  		
	  	<br>
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		      <button type="submit" class="btn btn-primary">Cadastrar Escola</button>
		    </div>
		  </div>
	</form>
	
</div>


</body>
</html>