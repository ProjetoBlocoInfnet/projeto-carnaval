<%@ page import="negocio.Usuario" %>
<%@ page import="negocio.Entidade" %>
<%@ page import="negocio.Ensaio" %>
<%@ page import="negocio.EscolaSamba" %>
<%@ page import="enumerator.Grupos" %>
<%@ page import="java.util.List" %>
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
		
</head>
<body>

<%
Usuario usuario = (Usuario) session.getAttribute("usuario");

List<Ensaio> listaEnsaios = null;
if(request.getAttribute("listaEnsaio") != null){
	listaEnsaios = (List<Ensaio>) request.getAttribute("listaEnsaio");
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
	
	
	<form action="ControlaEnsaio" method="post">
		<input type="hidden" name="action" value="telaCadastro">
		<a href="indexEscolaSamba.jsp"><button type="button" class="btn btn-default">Voltar</button></a>
		<input type="submit" class="btn btn-info" value="Cadastrar Ensaio">
	</form>
	
	
	<hr>
	<form action="ControlaEnsaio" method="post">
		
		<input type="hidden" name="action" value="consultar">
		
		<div class="control-group">
		  <label class="control-label" for="datetimepicker"></label>
		  <div class="controls">
		    <div id="datetimepicker" class="input-prepend date">
		   <!--  <div id="datetimepicker" class="input-append date">-->
		      <span class="add-on"><i data-time-icon="icon-time" data-date-icon="icon-calendar"></i></span>
		      <input name="data" class="span2" id="datetimepicker" type="text" placeholder="Consultar por data">		      		    
		    </div>
		  </div>
		   <div class="col-sm-offset-2 col-sm-10">
		      <button type="submit" class="btn btn-default">Consultar</button>
		    </div>
		</div>	        
        
        <script type="text/javascript">
          $('#datetimepicker').datetimepicker({
            format: 'dd/MM/yyyy hh:mm:ss',
            language: 'pt-BR'
          });
        </script>
        
		       
	</form>
	
	<hr>
	
	<% if(listaEnsaios.size() > 0 ){ %>
	<div >
	<table class="table table-hover">
  		<thead>
  		<th>Id</th>
  		<th>Data </th>
  		<th>Escola</th>
  		<th>Ação</th>
  		</thead>
  		<tbody>
  		<% for(Ensaio ensaio : listaEnsaios){ 
  			//Ensaio ensaio = (Ensaio) entidade; 
  		%>
	  		<tr>
	  			<td><%=ensaio.getId() %></td>  
	  			<td><%=ensaio.getData() %></td>
	  			<td><%=ensaio.getEscola().getNome() %></td>  
	  			<td>
	  				<a href="ControlaEnsaio?idEnsaio=<%=ensaio.getId()%>&acao=alterar"><i class="icon-pencil"></i></a> | 
	  				<a href="ControlaEnsaio?idEnsaio=<%=ensaio.getId()%>&acao=excluir"><i class="icon-trash"></i></a>
	  			</td>
	  		</tr>
  		<% } %>
  		</tbody>
	</table>
	</div>
	
	<% } else{ %>
	
	<% } %>
	
</div>


</body>
</html>