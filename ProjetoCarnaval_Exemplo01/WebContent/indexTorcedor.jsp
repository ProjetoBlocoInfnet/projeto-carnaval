<%@ page import="negocio.EscolaSamba" %>
<%@ page import="negocio.Entidade" %>
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


</head>
<body>

<%
List<Entidade> listaEscola = null;
if(request.getAttribute("listaEscola") != null){
	listaEscola = (List<Entidade>) request.getAttribute("listaEscola");
}
%>

<div class="container">
	
	<div>
	<h1>Cadastro de Torcedor</h1>
	
	
	<hr/>
	<a href="index.jsp"><button type="button" class="btn btn-default">Voltar</button></a>
	<br>
	
	
	<hr>
	<form class="form-horizontal" action="ControlaTorcedor" method="post" id="form-escolaSamba">
		
		<input type="hidden" name="action" value="cadastrar">
		  
		  <div class="form-group">
		    <label for="nome" class="col-sm-2 control-label">Nome</label>
		    <div class="col-sm-10">
		      <input type="text" name="nome" class="form-control" id="nome" placeholder="Nome">
		    </div>
		  </div>
		  
		   <div class="form-group">
		    <label for="login" class="col-sm-2 control-label">Login</label>
		    <div class="col-sm-10">
		      <input type="text" name="login" class="form-control" id="login" placeholder="Login">
		    </div>
		  </div>
		  
		   <div class="form-group">
		    <label for="senha" class="col-sm-2 control-label">Senha</label>
		    <div class="col-sm-10">
		      <input type="password" name="senha" class="form-control" id="senha" placeholder="Senha">
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="endereco" class="col-sm-2 control-label">Endereço</label>
		    <div class="col-sm-10">
		      <input type="text" name="endereco" class="form-control" id="endereco" placeholder="Endereço">
		    </div>
		  </div> 
		  
		  
		   <div class="form-group">
		    <label for="cpf" class="col-sm-2 control-label">cpf</label>
		    <div class="col-sm-10">
		      <input type="text" name="cpf" class="form-control" id="cpf" placeholder="CPF" maxlength="11">
		    </div>
		  </div>
		  
		   <div class="form-group">
		    <label for="cep" class="col-sm-2 control-label">CEP</label>
		    <div class="col-sm-10">
		      <input type="text" name="cep" class="form-control" id="cep" placeholder="CEP" maxlength="8">
		    </div>
		  </div>

		  
		  <div class="form-group">
		    <label for="sexo" class="col-sm-2 control-label">Sexo</label>
		    <div class="col-sm-10">
		    <div class="radio">
				  <label>
				    <input type="radio" name="sexo" id="sexo" value="M" >
				   Masculino
				  </label>
				</div>
				<div class="radio">
				  <label>
				    <input type="radio" name="sexo" id="sexo" value="F">
				    Feminino
				  </label>
				</div>
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
		      <input type="text" name="telefone" class="form-control" id="telefone" placeholder="Telefone" maxlength="10">
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="escolaSamba" class="col-sm-2 control-label">Escola de Samba </label>
		    <div class="col-sm-10">
		     <select name="escolaSamba" id="escolaSamba" class="form-control">
		     <%for (Entidade e :listaEscola){ 
		     	EscolaSamba escola = (EscolaSamba) e;
		     %>
		      	<option value="<%= escola.getId() %>"><%= escola.getNome() %></option>
		      <% } %>
		     </select>
		    </div>
		  </div>
	  		
	  	<br>
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		      <button type="submit" class="btn btn-primary">Cadastrar Integrante</button>
		    </div>
		  </div>
	</form>
	
</div>

</body>
</html>