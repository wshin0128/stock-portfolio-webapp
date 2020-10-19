<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="styleSI.css">
<title>Register for Portfolio Management</title>

<script>
	let url = "api/login";
	
	function parseResponse(response){
		let data = JSON.parse(response);
		console.log(data);
		let num = data; // to be gotten from data;
		if(num == 0){
			//error 1

		}
		else if(num == 1){
			window.location.href = "signIn.jsp"
		}
		
		
				
	}
	
	document.querySelector("#register").onsubmit = function(event){
		event.preventDefault();
		
		let username = document.getElementById("username").value;
		let password = document.getElementById("password").value;
		let password_confirmation = document.getElementById("confirmpassword").value;
		
		if(username.length < 1){
			
		}
		else if(password.length < 1){
			
		}
		else if(password != password_confirmation){
			
		}
		else{
			let httpRequest = new XMLHttpRequest();
			httpRequest.open("POST", url, true);
			

			// We will get alerted when backend gives back some kind of response
			httpRequest.onreadystatechange = function(){
				// This function runs when we get some kind of response back from iTunes
				console.log(httpRequest);
				// When we get back a DONE state (readyState == 4, let's do something with it)
				if(httpRequest.readyState == httpRequest.DONE) {
					// Check for errors - status code 200 means success
					if(httpRequest.status == 200) {
						console.log(httpRequest.responseText);

						// Display the results on the browser - a separate function is created for this purpose
						parseResponse(httpRequest.responseText);

					}
					else {
						console.log("AJAX Error!!");
						console.log(httpRequest.status);
						console.log(httpRequest.statusText);
					}
					
				}
			}
			
			httpRequest.send(JSON.stringify({
		        "username": username,
		        "password": password
		    })); 
		}
	}
	
</script>


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
			
			<div>
				<label > Password (8 characters minimum required) </label>
				<input type="password" id="confirmpassword" name="confirmpassword" minlength="8" required>
			</div>
			
			<br/> <br/>
			
			<div id="signupbutton">
				<button type="submit" class="signup-button"><i class="fas fa-sign-out-alt"></i>&nbsp&nbspSign Up</button>
			</div>
			
			<div id="takenUsernameError">
			</div>
		</form>
		
	</div>
</body>
</html>
