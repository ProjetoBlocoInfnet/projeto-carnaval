<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Escolas de Samba</title>
</head>
<body>
	<form action="ControlaEscolaSamba" method="get">
	
		<h2>Cadastro de Escolas de Samba</h2>
	
		<h3>Nome</h3><input type="text" name="nome">
		
		<h3>Endere�o da Quadra</h3><input type="text" name="enderecoQuadra">
		
		<h3>Endere�o do Barrac�o</h3><input type="text" name="enderecoBarracao">
		
		<h3>Endere�o do Barrac�o</h3><input type="text" name="enderecoBarracao">
		
		<h3>Cores</h3><input type="text" name="cor">
		
		<h3>Data de Funda��o</h3><input type="text" name="dataFundacao">
		
		<h3>Lema</h3><input type="text" name="lema">
		
		<h3>Filia��o</h3><input type="text" name="filiacao">

		<h3>Grupo Atual</h3><select name="cbGrupoAtual">      
	        <option value="GrupoEspecial">Grupo Especial</option>
	        <option value="SerieA">S�rie A</option>
	        <option value="GrupoB">S�rie B</option>
	        <option value="GrupoC">S�rie C</option>
	        <option value="GrupoD">S�rie D</option>
	        <option value="GrupoAvaliacao">Grupo de Avalia��o</option>      
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