<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="styles.css">
<title>Sign Up</title>
</head>
<body>
	<div class="navbar">
		<div class="wrap">
			<h1>USC CS 310: Stock Portfolio Management</h1>
		</div>
	</div>
	<div class="signup"> 
	
		<form id="register">
			<div>
				<label for="username"> Username </label>
				<input type="text" id="username" name="username" minlength="1" required>
			</div>
			
			<br/>
			
			<div>
				<label for="password"> Password (8 characters minimum required) </label>
				<input type="password" id="password" name="password" minlength="8" required>
			</div>
			
			<br/> <br/>
			
			<div id="signupbutton">
				<button type="submit" class="signup-button"><i class="fas fa-sign-out-alt"></i>&nbsp&nbspSign Up</button>
			</div>
		</form>
		
	</div>
</body>
</html>
