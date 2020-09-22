<%@ page import="csci310.*" %>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="style.css">
	<title>Home</title>
	<script src="https://kit.fontawesome.com/dbcc9507e2.js" crossorigin="anonymous"></script>
</head>
<body>
	<div class="navbar">
		<div class="wrap">
			<h1>WebApp Name</h1>
			<a href="" class="sign-out-button"><i class="fas fa-sign-out-alt"></i>&nbsp&nbspSign Out</a>
		</div>
	</div>
    <div class="wrap">
    	<div class="grid-container">
	    	<div class="homepage-container" id="graph-container">
	    	</div>
	    	<div class="homepage-container" id="portfolio-container">
	    		<div class="container-header">
	    			Your Portfolio
	    			<div class="plus-minus-buttons-container">
	    				<a href=""><i class="fas fa-plus-circle"></i></a>
	    				<a href=""><i class="fas fa-minus-circle"></i></a>
	    			</div>
	    		</div>
	    	</div>
	    	<div class="homepage-container" id="viewed-container">
	    		<div class="container-header">
	    			Viewed Stocks
	    			<div class="plus-minus-buttons-container">
	    				<a href=""><i class="fas fa-plus-circle"></i></a>
	    				<a href=""><i class="fas fa-minus-circle"></i></a>
	    			</div>
	    		</div>
	    	</div>
    	</div>
    </div>
</body>
</html>
