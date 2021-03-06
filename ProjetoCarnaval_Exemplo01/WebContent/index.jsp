<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	<h1>Login</h1>
	</div>
	<hr/>
	<br>
	
	<%
	if(request.getAttribute("resultado") != null){%>
		<div class="alert alert-danger" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<strong><%=request.getAttribute("resultado") %></strong></div>
	<% }else if (request.getAttribute("logout") != null){%>
		<div class="alert alert-info" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<strong><%=request.getAttribute("logout") %></strong></div>
	<% } %>
	
	<form class="form-horizontal" action="Login" method="post">
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
		    <div class="col-sm-offset-2 col-sm-10">
		      <button type="submit" class="btn btn-primary">Login</button>
		    </div>
		  </div>
	</form>
	<hr>
	<form action="ControlaTorcedor" method="get" >
			<div class="form-group">
		    <div class="col-sm-offset-1 col-sm-10">
		      <input type="hidden" name="tela" value="cadastrar">
		      <button type="submit" class="btn btn-primary">Cadastrar Torcedor</button>
		    </div>
		  </div>
	</form>
	
	
</div>
</body>
</html>
