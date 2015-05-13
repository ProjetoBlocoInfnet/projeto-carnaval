<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Quesitos</title>
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

	<form action="ControlaQuesito" method="get">
	
		<h2>Cadastro de Quesitos</h2>
	
		<input type="hidden" name="acao" value="<%=oAcao%>">

		<h3>Nome</h3><input type="text" name="nome">
		
		<h3>Descrição</h3><input type="text" name="descricao">

		<br/>
		<br/>

		<input type="submit" value="Salvar e Continuar">
		<input type="submit" value="Salvar e Sair" onclick="form.method='post';">
	</form>

</body>
</html>