<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Login de integrante</title>
</head>
<body>

	<form method="post" action="login">
		<fieldset>
			<legend>Login de integrante</legend>
			<ul>
				<li><label for="login">Login</label> <input name="login"
					type="text" /></li>
				<label for="senha">Password:</label>
				<input name="senha" type="password" />
				<li></li>
			</ul>
			<input type="submit" />
		</fieldset>
	</form>
</body>
</html>