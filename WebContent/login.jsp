<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<div style="text-align: center">
		<h1>Login</h1>
		<form action="login" method="post">
			<label for="username">Username:</label>
			<input name="username" size="30" />
			<br><br>
			<label for="password">Password:</label>
			<input type="password" name="password" size="30" />
			<br>${message}
			<br><br>
			<button type="submit">Login</button>
		</form>
	</div>
</body>
</html>