<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="styleSI.css">
<script src="https://kit.fontawesome.com/dbcc9507e2.js" crossorigin="anonymous"></script>
<title>Sign In</title>
</head>
<body>
	<div class="navbar">
		<div class="wrap">
			<h1>Login</h1>
			
		</div>
	</div>
	<div class="login">
		<form id="stuufff">
			<div>
				<label for="username">Username:</label>
		    <input type="text" id="username" name="username" minlength="1" required>
			</div>
			<br/>
			<div>
			<label for="pass">Password (8 characters minimum):</label>
			<input type="password" id="pass" name="password" minlength="8" required>
			    
			    
			</div>
			<br/>
			<div id="idk">
			<button type="submit" class="sign-in-button"><i class="fas fa-sign-out-alt"></i>&nbsp&nbspSign In</button>

			</div>
			
		</form>
		
	</div>
</body>
</html>