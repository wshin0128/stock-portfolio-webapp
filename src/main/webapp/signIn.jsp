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
			<h1>USC 310: Stock Portfolio Management</h1>
			
		</div>
	</div>
	<div class="login">
		<form id="logInForm">
			<h3>Login</h3>
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
			<div id="subButton">
				<button type="submit" class="sign-in-button"><i class="fas fa-sign-out-alt"></i>&nbsp&nbspSign In</button>
			</div>
			<div id = "signUpLink">
				<a href = "">Don't have an account? Sign Up!</a>
			</div>
			<div id = "errorMessage">
				
			</div>
		</form>
		
	</div>
	<script>
		let numFailed = 0;
		let url = "";
		
		function parseResponse(response){
			let data = JSON.parse(response);
			let num = 3; // to be gotten from data;
			if(num == 0){
				//error 1
				document.getElementsByTagName("BODY").style.animationName = "error";
				numFailed++;
				document.querySelector("#pass").style.borderColor = "#ff0033";
				document.querySelector("#username").style.borderColor = "#ff0033";
				document.querySelector("#errorMessage").innerHTML = "error1";
			}
			else if(num == 1){
				//error 2
				document.getElementsByTagName("BODY").style.animationName = "error";
				numFailed++;
				document.querySelector("#pass").style.borderColor = "#ff0033";
				document.querySelector("#username").style.borderColor = "#ff0033";
				document.querySelector("#errorMessage").innerHTML = "error2";
			}
			else if(num == 2){
				//error 3
				document.getElementsByTagName("BODY").style.animationName = "error";
				numFailed++;
				document.querySelector("#pass").style.borderColor = "#ff0033";
				document.querySelector("#username").style.borderColor = "#ff0033";
				document.querySelector("#errorMessage").innerHTML = "error3";
			}
			
			if(numFailed > 2){
				document.querySelector(".sign-in-button").enabled = false;
				document.querySelector("#errorMessage").innerHTML = "You have been locked for failing to sign in three times";
			}
			else{
				window.location.href = "homepage.jsp"
			}			
		}
		
		document.querySelector("#logInForm").onsubmit = function(event){
			event.preventDefault();
			
			let userName = document.querySelector("#username").value;
			let pass = document.querySelector("#pass").value;
			
			
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
			
			xmlhttp.send(JSON.stringify({
		        "username": "",
		        "password": ""
		    })); 
		}
	</script>
</body>
</html>