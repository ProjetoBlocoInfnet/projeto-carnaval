<%@ page import="dao.EscolaSambaDAO" %>
<%@ page import="dao.TorcedorDAO" %>
<%@ page import="negocio.EscolaSamba" %>
<%@ page import="negocio.Torcedor" %>
<%@ page import="dao.EnsaioDAO" %>
<%@ page import="negocio.Ensaio" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	EscolaSamba escola = (EscolaSamba) new EscolaSambaDAO().obterPorId( 0 );
%>
<title>Escola de samba:<%=escola.getNome()%> </title>
</head>
<body>
	<fieldset>
		<ul>
			<li>
				<label for="nome">Nome</label>
				<input name= "nome" value="<%=escola.getNome()%>" />
			</li>
			
			<li>
				<label for="enderecoQuadra">Endereço de quadra</label>
				<input name= "enderecoQuadra" value="<%=escola.getEnderecoQuadra()%>" />
			</li>

			<li>
				<label for="enderecoBarracao">Endereço de barracão</label>
				<input name= "enderecoBarracao" value="<%=escola.getEnderecoBarracao()%>" />
			</li>

			<li>
				<label for="lema">Lema</label>
				<input name= "lema" value="<%=escola.getLema()%>" />
			</li>
			
			<li>
				<label for="filiacao">Filiação</label>
				<input name= "filiacao" value="<%=escola.getFiliacao()%>" />
			</li>
			
			<li>
				<label for="grupo">Grupo atual</label>
				<input name= "grupo" value="<%=escola.getGrupoAtual()%>" />
			</li>
			
			<li>
				<label for="email">Email</label>
				<input name= "email" value="<%=escola.getEmail()%>" />
			</li>
			
			<li>
				<label for="telefone">Telefone</label>
				<input name= "telefone" value="<%=escola.getTelefone()%>" />
			</li>

			<li>
				<label for="cnpj">CNPJ</label>
				<input name= "cnpj" value="<%=escola.getCnpj()%>" />
			</li>			
		</ul>
	</fieldset>
	
	<section>
	<hr/>
	<h2>torcedores:</h2>
		<%
			for ( negocio.Entidade t : new TorcedorDAO().obterTodos() ) {
				if ( ( (Torcedor) t).getEscolaSamba() == escola ) {
					out.println( "<li>" + ( (Torcedor)t).getNome() + "</li>" );
				}
			}
		%>
	</section>
	<section>
	<hr/>
	<h2>Ensaios:</h2>
		<%
			for ( negocio.Entidade e : new EnsaioDAO().obterTodos() ) {
				if ( ((Ensaio)e).getEscola() == escola ) {
					out.println( "<li>" + ((Ensaio)e).getData() + "</li>" );
				}
			}
		%>
	</section>
</body>
</html>