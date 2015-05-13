<%@ page import="negocio.Entidade" %>
<%@ page import="negocio.Ensaio" %>
<%@ page import="dao.EnsaioDAO" %>
<%@ page import="negocio.Usuario" %>
<%@ page import="negocio.Integrante" %>
<%@ page import="negocio.Atividade" %>
<%@ page import="negocio.EscolaSamba" %>
<%@ page import="dao.EscolaSambaDAO" %>
<%@ page import="java.util.HashSet" %>
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
	 </script>
</head>
<body>
<%
Usuario usuario = (Usuario)request.getSession().getAttribute( "usuario" );
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
	
	<nav>
	<ul class="nav nav-tabs">
  		<li><a data-toggle = "tab" href="#escolas">Minhas escolas</a></li>
  		<li><a data-toggle = "tab" href="#ensaios">Ensaios</a></li>
	</ul>
	</nav>
	<br/><br/>

	<% 
	HashSet<EscolaSamba> escolas = new HashSet<EscolaSamba>();	
	
	for ( Atividade ativ : ((Integrante) usuario ).getAtividades() ) {
		escolas.add( ativ.getEscolaSamba() );
	}
	%>

	<div class="tab-content">
	<div class = "tab-pane" id = "escolas" >
	<%
		for ( EscolaSamba escola : escolas ) {
			
				out.println( "<article id = '" + escola.getNome()+ "' >" );
				
				out.println( "<ul>" );
				
				out.println( "<li><a href='ConsultaEscola.jsp?idEscola=" + escola.getId() + "'>" + escola.getNome() + "</a</li>" );
				
				out.println( "</ul>" );
				out.println( "</article>" );				
		}
		%>
		</div>
		
		<div class = "tab-pane active" id = "ensaios" >
		
		<%
		Ensaio ensaio;
		out.println( "<ul>" );
		
		HashSet< Ensaio > ensaios = new HashSet< Ensaio >();
		
		
			for ( Entidade e : new EnsaioDAO().obterTodos() ) {
				ensaio = (Ensaio)e;
				
				if ( escolas.contains( ensaio.getEscola() ) ) {
					ensaios.add( ensaio );
				}
			}
				
			for ( Ensaio ens : ensaios ) {	

				out.println( "<li>" );
				out.println( "<a href='ConsultaEnsaio.jsp?idEnsaio=" + ens.getId() + ">" );
				out.println( ens.getEscola().getNome() + "-" + ens.getData().toString() );
				out.println( "</a>" );
				out.println( "</li>" );
			}

		out.println( "</ul>" );
	%>
		</div>
	</div>
</div>


</body>
</html>