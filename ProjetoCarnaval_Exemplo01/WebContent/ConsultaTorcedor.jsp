<%@ page import="negocio.Torcedor" %>
<%@ page import="dao.TorcedorDAO" %>
<%@ page import="negocio.EscolaSamba" %>
<%@ page import="dao.EscolaSambaDAO" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Consulta torcedor</title>
</head>
<body>
	<h1>Consulta torcedor</h1>
	<form>
		<% 
		Torcedor torcedor = (Torcedor)new TorcedorDAO().obterPorId( Integer.parseInt( request.getParameter("idTorcedor")));		
		out.println( "<h2>" + torcedor.getNome() + "</h2>");
		%>
		<legend>Dados pessoais</legend>
		<%
			
			out.println( "<ul>" );			
			out.println( "<li><label for='endereco'>Endereço:</label><input readonly='readonly' value='" + torcedor.getEndereco() + "' name='endereco'/></li>" );
			out.println( "<li><label for='cpf'>CPF:</label><input readonly='readonly' value='" + torcedor.getCpf() + "' name='cpf'/></li>" );
			out.println( "<li><label for='cep'>CEP:</label><input readonly='readonly' value='" + torcedor.getCep() + "' name='cep'/></li>" );
			out.println( "<li><label for='telefone'>Telefone:</label><input value='" + torcedor.getTelefone() + "' readonly='readonly' name='telefone'/></li>" );
			out.println( "<li><label for='email'>Email:</label><input value='" + torcedor.getEmail() + "' readonly='readonly' name='email'/></li>" );
			out.println( "<li><label for='sexo'>Sexo:</label><input value='" + torcedor.getSexo() + "' readonly='readonly' name='sexo'/></li>" );		
			
			out.println( "<li>Escola do coração: <a href='ConsultaEscola.jsp?idEscola=" + torcedor.getEscolaSamba().getId() + "'>" + torcedor.getEscolaSamba().getNome() + "</a></li>" );
			out.println( "</ul>" );
		%>
	</form>
	<hr />
	<a href="#" onclick="window.history.back()">Voltar</a>

</body>
</html>