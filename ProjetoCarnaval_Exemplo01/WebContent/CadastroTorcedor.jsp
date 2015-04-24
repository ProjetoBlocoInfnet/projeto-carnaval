<%@ page import="dao.EscolaSambaDAO" %>
<%@ page import="negocio.EscolaSamba" %>
<%@ page import="negocio.Entidade" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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
				alert("Cadastro efetuado com sucesso");
			}
			else
			{
				alert("Houve uma falha no seu cadastro. Tente novamente.");
			}
		}
	</script>


	<form action="ControlaTorcedor" method="post">
	
		<input type="hidden" name="acao" value="salvar">
	
		<h2>Cadastro de Torcedor</h2>
	
		<h3>Nome</h3><input type="text" name="nome">
		
		<h3>CPF</h3><input type="text" name="cpf">
		
		<h3>Endereço</h3><input type="text" name="endereco">
		
		<h3>Cep</h3><input type="text" name="cep">
		
		<h3>Sexo</h3>
		
		<input name=sexos list="sexos">
 		<datalist id="sexos">
			<option value="Masculino">
			<option value="Feminino">
		</datalist>

		<h3>Email</h3><input type="text" name="email">
		
		<h3>Telefone</h3><input type="text" name="telefone">
		
		<h3>Escola de Samba do Coração</h3>
		<input name=escolaSamba list="escolaSamba">
		<datalist id="escolaSamba">
		<%
			for ( Entidade e : new EscolaSambaDAO().obterTodosCollection() ) {
				EscolaSamba t = (EscolaSamba) e;
					out.println( "<option value=\"" + t.getNome() + "\">" );
			}
		%>
		</datalist>

		<h3>Login</h3><input type="text" name="login">
		
		<h3>Senha</h3><input type="text" name="senha">

		<br/>
		<br/>

		<input type="submit" value="Salvar e Continuar">
	</form>

</body>
</html>