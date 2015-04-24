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
	<strong>Usuário: </strong><%=request.getAttribute("usuarioLogado") %><br>
	<strong>Perfil: </strong><%=request.getAttribute("usuarioPerfil") %><br>
	</div>
	<hr/>
	<br>
	<ul class="nav nav-tabs">
  		<li role="presentation" class="active"><a href="#">Cadastrar Integrante</a></li>
  		<li role="presentation"><a href="#">Cadastrar Ensaio</a></li>
	</ul>
	<br><br>
	
	<form class="form-horizontal" action="login" method="post" id="form-escolaSamba">
		
		  
		  <div class="form-group">
		    <label for="nome" class="col-sm-2 control-label">Nome</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="nome" placeholder="Nome">
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="endereco" class="col-sm-2 control-label">Endereço</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="endereco" placeholder="Endereço">
		    </div>
		  </div> 
		  
		   <div class="form-group">
		    <label for="cpf" class="col-sm-2 control-label">CPF</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="cpf" placeholder="CPF">
		    </div>
		  </div>
		  
		    <div class="form-group">
		    <label for="cep" class="col-sm-2 control-label">CEP</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="cep" placeholder="CEP">
		    </div>
		  </div>
		  
		   <div class="form-group">
		    <label for="email" class="col-sm-2 control-label">E-mail</label>
		    <div class="col-sm-10">		   
		      <input type="text" class="form-control" id="email" placeholder="E-mail">
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="telefone" class="col-sm-2 control-label">Telefone</label>
		    <div class="col-sm-10">		   
		      <input type="text" class="form-control" id="telefone" placeholder="Telefone">
		    </div>
		  </div>
		  
		   <div class="form-group">
		    <label for="telefone" class="col-sm-2 control-label">Sexo</label>
		    <div class="col-sm-10">		   
		      <label class="radio-inline">
				  <input type="radio" name="sexo" id="M" value="M"> Masculino
			  </label>
			  <label class="radio-inline">
				  <input type="radio" name="sexo" id="F" value="F"> Feminino
			  </label>
				
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="cor" class="col-sm-2 control-label">Atividade</label>
		    <div class="col-sm-10">
		    <select name="cor" id="cor" multiple class="form-control">
			  <option>Amarelo</option>
			  <option>Azul</option>
			  <option>Verde</option>
			  <option>Rosa</option>
			  <option>Vermelho</option>
			</select>
		    </div>
		  </div>  
		  		  
		
	  		
	  	<br>
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		      <button type="submit" class="btn btn-primary">Login</button>
		    </div>
		  </div>
	</form>
	

	
</div>


</body>
</html>