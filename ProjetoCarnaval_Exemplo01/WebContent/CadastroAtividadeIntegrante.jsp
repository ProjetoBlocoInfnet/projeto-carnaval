<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de atividade de Integrante</title>
</head>
<body>
	<h1>Cadastro de atividade de Integrante</h1>
	<section>
	<form action="CadastroAtividadeIntegrante" method="post">
		<h1>Escola</h1>
		<fieldset>
			<legend>Dados da atividade</legend>

			<datalist id="escolas">
			<option>Salgueiro</option>
			<option>Viradouro</option>
			<option>Mangueira</option>
			</datalist>

			<datalist id="acao-integrante">
			<option>Carpinteiro</option>
			<option>Ala</option>
			</datalist>

			<ul>
				<li><label for="idIntegrante">Id:</label> <input
					name="idIntegrante" /></li>
				<li><label for="escola">Escola</label> <input name="escola"
					list="escolas" /></li>
					<li><label for="atividade">Atividade</label> <input name="atividade"
					list="acao-integrante" /></li>
					
					<li>
						<label for="ano">Ano:</label>
						<input name="ano" type="number"/>
					</li>
			</ul>
		</fieldset>
		<input type="submit" />
	</form>
	</section>
	<footer> <a href="principal.html">Voltar</a> </footer>
</body>
</html>