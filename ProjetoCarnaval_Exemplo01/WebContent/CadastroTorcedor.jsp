<%@ page import="dao.EscolaSambaDAO" %>
<%@ page import="negocio.EscolaSamba" %>
<%@ page import="negocio.Entidade" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Torcedor</title>
</head>
<body>
<%
String oAcao = "salvar";
String oSucesso = "";

if(request.getAttribute("oAcao") != null)
{
	oAcao = request.getAttribute("oAcao").toString();
}

if(request.getAttribute("oSucesso") != null)
{
	oSucesso = request.getAttribute("oSucesso").toString();
}
%>

	<script language="Javascript" type="text/javascript">
		var sucesso = "<%=oSucesso%>";
		if(sucesso != "")
		{
			if(sucesso == "s")
			{
				alert("cadastro efetuado com sucesso");
			}
			else
			{
				alert("houve uma falha no seu cadastro. tente novamente.");
			}
		}
	</script>


	<form action="ControlaTorcedor" method="post">
	
		<input type="hidden" name="acao" value="salvar">
	
		<h2>Cadastro de Torcedor</h2>
	
		<h3>Nome</h3><input type="text" name="nome">
		
		<h3>Endereço</h3><input type="text" name="endereco">
		
		<h3>Cep</h3><input type="text" name="cep">
		
		<datalist id="sexos">
			<option>Masculino</option>
			<option>Feminino</option>
		</datalist>


		<h3>Email</h3><input type="text" name="email">
		
		<h3>Telefone</h3><input type="text" name="telefone">
		
		<h3>Escola de Samba do Coração</h3>
		<datalist id="escolaSamba">
		<%
			for ( Entidade e : new EscolaSambaDAO().obterTodosCollection() ) {
				EscolaSamba t = (EscolaSamba) e;
					out.println( "<option>" + t.getNome() + "</option>" );
			}
		%>
		</datalist>

		<br/>
		<br/>

		<input type="submit" value="Salvar e Continuar">
	</form>

</body>
</html>