<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Escolas de Samba</title>
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


	<form action="ControlaEscolaSamba" method="get">
	
		<input type="hidden" name="acao" value="salvar">
	
		<h2>Cadastro de Escolas de Samba</h2>
	
		<h3>Nome</h3><input type="text" name="nome">
		
		<h3>Endere�o da Quadra</h3><input type="text" name="enderecoQuadra">
		
		<h3>Endere�o do Barrac�o</h3><input type="text" name="enderecoBarracao">
		
		<h3>Endere�o do Barrac�o</h3><input type="text" name="enderecoBarracao">
		
		<h3>Cores</h3><input type="text" name="cor">
		
		<h3>Data de Funda��o</h3><input type="text" name="dataFundacao">
		
		<h3>Lema</h3><input type="text" name="lema">
		
		<h3>Filia��o</h3><input type="text" name="filiacao">

		<h3>Grupo Atual</h3>
		<input name="grupos" list="grupos">
 		<datalist id="grupos">
			<option value="Grupo Especial">
			<option value="S�rie A">
			<option value="Grupo B">
			<option value="Grupo C">
			<option value="Grupo D">
			<option value="Grupo Avalia��o">
		</datalist>

		<h3>Email</h3><input type="text" name="email">
		
		<h3>Telefone</h3><input type="text" name="telefone">

		<br/>
		<br/>

		<input type="submit" value="Salvar e Continuar">
		<input type="submit" value="Salvar e Sair" onclick="form.method='post';">
	</form>

</body>
</html>