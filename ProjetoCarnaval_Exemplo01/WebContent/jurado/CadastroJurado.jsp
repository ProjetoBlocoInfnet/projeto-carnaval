<%@ page import="negocio.Usuario" %>
<%@ page import="negocio.Quesito" %>
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
List<Entidade> listaQuesitos = null;
if(request.getAttribute("listaQuesitos") != null){
	listaQuesitos = (List<Entidade>) request.getAttribute("listaQuesitos");
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
	<br>
	<form action="ControlaJurado" method="get">
		<input type="submit" class="btn btn-info" value="Voltar">
	</form>
	<br><br>
	
	<hr>
	<form class="form-horizontal" action="ControlaJurado" method="post" id="form-escolaSamba">
		
		<input type="hidden" name="action" value="cadastrar">
		  
		  <div class="form-group">
		    <label for="nome" class="col-sm-2 control-label">Nome</label>
		    <div class="col-sm-10">
		      <input type="text" name="nome" class="form-control" id="nome" placeholder="Nome">
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
		    <label for="endereco" class="col-sm-2 control-label">Endereço</label>
		    <div class="col-sm-10">
		      <input type="text" name="endereco" class="form-control" id="endereco" placeholder="Endereço">
		    </div>
		  </div> 
		  
		   <div class="form-group">
		    <label for="telefone" class="col-sm-2 control-label">Telefone</label>
		    <div class="col-sm-10">
		      <input type="text" name="telefone" class="form-control" id="telefone" placeholder="Telefone">
		    </div>
		  </div>
		  
		   <div class="form-group">
		    <label for="cep" class="col-sm-2 control-label">CEP</label>
		    <div class="col-sm-10">
		      <input type="text" name="cep" class="form-control" id="cep" placeholder="CEP">
		    </div>
		  </div>  
		  
		   <div class="form-group">
		    <label for="cpf" class="col-sm-2 control-label">CPF</label>
		    <div class="col-sm-10">
		      <input type="text" name="cpf" class="form-control" id="cpf" placeholder="CPF">
		    </div>
		  </div> 
		  
		   <div class="form-group">
		    <label for="email" class="col-sm-2 control-label">E-mail</label>
		    <div class="col-sm-10">
		      <input type="text" name="email" class="form-control" id="email" placeholder="E-mail">
		    </div>
		  </div> 
		  
		  <div class="form-group">
		    <label for="quesito" class="col-sm-2 control-label">Quesito</label>
		    <div class="col-sm-10">
		     <select name="quesito" id="quesito" class="form-control">
		     <%for (Entidade e :listaQuesitos){ 
		     	Quesito quesito = (Quesito) e;
		     %>
		      	<option value="<%= quesito.getId() %>"><%= quesito.getNome() %></option>
		      <% } %>
		     </select>
		    </div>
		  </div>
	  		
	  	<br>
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		      <button type="submit" class="btn btn-primary">Cadastrar Jurado</button>
		    </div>
		  </div>
	</form>
	
</div>


</body>
</html>