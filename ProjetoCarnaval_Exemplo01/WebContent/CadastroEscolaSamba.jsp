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
			alert("variavel retornou " + sucesso);
			if(sucesso == "s")
			{
				alert("cadastro efetuado com sucesso");
			}
			else
			{
				alert("houve uma falha no seu cadastro. tente novamente.");
			}
		}
		else
		{
			alert("variavel retornou " + sucesso);
		}
	</script>


	<form action="ControlaEscolaSamba" method="get">
	
		<input type="hidden" name="acao" value="salvar">
	
		<h2>Cadastro de Escolas de Samba</h2>
	
		<h3>Nome</h3><input type="text" name="nome">
		
		<h3>Endereço da Quadra</h3><input type="text" name="enderecoQuadra">
		
		<h3>Endereço do Barracão</h3><input type="text" name="enderecoBarracao">
		
		<h3>Endereço do Barracão</h3><input type="text" name="enderecoBarracao">
		
		<h3>Cores</h3><input type="text" name="cor">
		
		<h3>Data de Fundação</h3><input type="text" name="dataFundacao">
		
		<h3>Lema</h3><input type="text" name="lema">
		
		<h3>Filiação</h3><input type="text" name="filiacao">

		<h3>Grupo Atual</h3><select name="cbGrupoAtual">      
	        <option value="GrupoEspecial">Grupo Especial</option>
	        <option value="SerieA">Série A</option>
	        <option value="GrupoB">Série B</option>
	        <option value="GrupoC">Série C</option>
	        <option value="GrupoD">Série D</option>
	        <option value="GrupoAvaliacao">Grupo de Avaliação</option>      
		</select>

		<h3>Email</h3><input type="text" name="email">
		
		<h3>Telefone</h3><input type="text" name="telefone">

		<br/>
		<br/>

		<input type="submit" value="Salvar e Continuar">
		<input type="submit" value="Salvar e Sair" onclick="form.method='post';">
	</form>

</body>
</html>