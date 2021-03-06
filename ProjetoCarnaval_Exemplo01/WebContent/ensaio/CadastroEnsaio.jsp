<%@ page import="negocio.Usuario" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<title>Projeto Carnaval</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
	
	
	<link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" media="screen" 
                  href="http://tarruda.github.com/bootstrap-datetimepicker/assets/css/bootstrap-datetimepicker.min.css">
	
	
	
	
</head>
<body>

<%
Usuario usuario = (Usuario) session.getAttribute("usuario");
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
	<form action="ControlaEnsaio" method="get">
		<input type="submit" class="btn btn-info" value="Voltar">
	</form>
	
	<hr>
	<h4>Cadastro de Ensaio</h4>
	<hr>
	<form class="form-horizontal" action="ControlaEnsaio" method="post" id="form-escolaSamba">
		
		<input type="hidden" name="action" value="cadastrar">
		
   	 	<div class="form-group">
   	 	 <label for="datetimepicker" class="col-sm-2 control-label"></label><br>
   	 	  	<div class="col-sm-6">
	   	 		<div id="datetimepicker" class="input-append date">
		          	<input type="text" name="data" placeholder="Data do Ensaio"></input>
		          	<span class="add-on">
		            	<i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
		         	 </span>
	       		 </div>
	        </div>
	     </div>
        
        <script type="text/javascript"
         src="http://cdnjs.cloudflare.com/ajax/libs/jquery/1.8.3/jquery.min.js">
        </script> 
        <script type="text/javascript"
         src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/js/bootstrap.min.js">
        </script>
        <script type="text/javascript"
         src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.min.js">
        </script>
        <script type="text/javascript"
         src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.pt-BR.js">
        </script>
        <script type="text/javascript">
          $('#datetimepicker').datetimepicker({
            format: 'dd/MM/yyyy hh:mm:ss',
            language: 'pt-BR'
          });
        </script>
		  
	  		
	  	<br>
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		      <button type="submit" class="btn btn-primary">Cadastrar Ensaio</button>
		    </div>
		  </div>
	</form>
	
</div>


</body>
</html>