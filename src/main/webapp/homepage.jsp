<%@ page import="csci310.*" isELIgnored="false"%>

    
<%
	HttpSession s = request.getSession();
	if(s.getAttribute("login") == "false" || s.getAttribute("login") == null) {
		response.sendRedirect("signIn.jsp");
	}
%>

<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
	<link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;400;700;900&display=swap" rel="stylesheet">
	<title>Home</title>
	<script src="https://kit.fontawesome.com/dbcc9507e2.js" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	
	<script>
		// Log out user after 120 seconds of inactivity
		var userInactivity = function () {
		    var time;
		    window.onload = resetTimer;
		    // Reset timer whenever user moves mouse or presses key
		    document.onmousemove = resetTimer;
		    document.onkeypress = resetTimer;
	
		    function logout() {
		    	// Alert user logout
		        alert("You have been logged out due to two minutes of inactivity.")
		        // Redirect to sign in page
		        location.href = "/signIn.jsp";
		    }
		    // Reset the timer
		    function resetTimer() {
		        clearTimeout(time);
		        time = setTimeout(logout, 120000)
		    }
		};
		// Load timer on page load
		window.onload = function() {
			userInactivity(); 
		}
	</script>
	
</head>
<body>
	<div class="navbar">
		<div class="wrap">
			<h1>USC CS 310: Stock Portfolio Management</h1>
			<a href="signIn.jsp" class="button"><i class="fas fa-sign-out-alt"></i>&nbsp&nbspSign Out</a>
		</div>
	</div>
    <div class="wrap">
    	<div class="grid-container">
    	
	    	<div class="homepage-container" id="graph-container">
	    		<div class="graph-header">
	    			<span id="portfolio-value">$1,349.32</span>
	    			<div id="portfolio-value-change" style="color: #51C58E;">
    					<span id="arrow">&#9650 +3.25% Today</span>
    					<span id="arrow2">&#128315 -3.25% Today</span>
	    			</div>
	    		</div>

	    		<form name="getdata" action="/stockperformance" method="post">
	     		<div class="row justify-content-center" role="group" aria-label="Basic example">
				  <input type="submit" id="1-day-btn" class="btn btn-secondary" name="timePeriod" value="1D"/>
				  <input type="submit" id="1-week-btn" class="btn btn-secondary" name="timePeriod" value="1W"/>
				  <input type="submit" id="1-month-btn" class="btn btn-secondary" name="timePeriod" value="1M"/>
				  <input type="submit" id="1-year-btn" class="btn btn-secondary" name="timePeriod" value="1Y"/>
				</div>
				</form>
	    		<div class = "graph-main">
	    		<canvas id="myChart" width="1800" height="210"></canvas>

	    		</div>
	    	</div> <!-- #graph-container -->
	    	
	    	<div class="homepage-container" id="portfolio-container">
	    		<div class="container-header">
	    			Your Portfolio
	    			<div class="add-stocks-container">
	    			
	    				<button class="button" id="add-stock-button">Add Stock</button>
	    				<div class="modal" id="add-stock-modal">
	    					<div class="modal-box">
	    						<div class="popup-header">Add Stock</div>
	    						<div class="popup-section">
	    							<form id="add-stock-form" name="addStock" method="post" action="/api/addstock">
	    								<div class="form-row">
	    									<label for="ticker">Stock Ticker</label>
	    									<input type="text" id="ticker" name="ticker" required>
	    								</div>
	    								<div class="form-row">
	    									<label for="ticker"># of Shares</label>
	    									<input type="number" id="shares" name="shares" required>
	    									
	    								</div>
	    								<div class="form-row">
	    									<label for="date-purchased">Date Purchased</label>
	    									<input type="date" id="date-purchased" placeholder="yyyy-mm-dd" name="date-purchased" required>
	    								</div>
	    								<div class="form-row">
	    									<label for="date-sold">Date Sold</label>
	    									<input type="date" id="date-sold" placeholder="yyyy-mm-dd" name="date-sold" required>
	    								</div>
	    								<div class="form-row">
	    									<span class="error-msg">${errorMessage}</span>
	    								</div>
	    								
	    								<button type="submit" class="button" id="add-stock-submit">Add Stock</button>
	    							</form>
	    						</div>
	    						<div class="popup-section">
	    							<button class="button" id="add-stock-cancel">Cancel</button>
	    						</div>
	    					</div>
	    				</div>
	    				
	    				<button class="button" id="import-stock-button">Import CSV</button>
	    				<div class="modal" id="import-stock-modal">
	    					<div class="modal-box">
	    						<div class="popup-header">Import Stocks</div>
	    						<div class="popup-section">
	    							<form id="import-stock-form">
	    								<div class="form-row">
	    									<label for=""csvImport"">Upload a .csv file</label>
	    									<input type="file" id="csvImport" accept=".csv">
	    								</div>
	    								<div class="form-row">
	    									<span class="error-msg">Test error message</span>
	    								</div>
	    								<button type="submit" class="button" id="import-stock-submit">Upload File</button>
	    							</form>
	    						</div>
	    						<div class="popup-section">
	    							<button class="button" id="import-stock-cancel">Cancel</button>
	    						</div>
	    					</div>
	    				</div>
	    				
	    			</div>
	    		</div> <!-- .container-header -->
	    		
	    		<table id="stock-list">
	    			<tr>
	    				<td>Apple</td>
	    				<td>AAPL</td>
	    				<td>
	    					<label class="switch">
	    						<input type="checkbox" checked>
							  	<span class="slider round"></span>
							</label>
						</td>
	    				<td><a href=""><i class="fas fa-trash"></i></a></td>
	    			</tr>
	    			<tr>
	    				<td>Tesla</td>
	    				<td>TSLA</td>
	    				<td>
	    					<label class="switch">
	    						<input type="checkbox" checked>
							  	<span class="slider round"></span>
							</label>
						</td>
	    				<td><a href=""><i class="fas fa-trash"></i></a></td>
	    			</tr>
	    			<tr>
	    				<td>Ford Motor</td>
	    				<td>F</td>
	    				<td>
	    					<label class="switch">
	    						<input type="checkbox" checked>
							  	<span class="slider round"></span>
							</label>
						</td>
	    				<td><a href=""><i class="fas fa-trash"></i></a></td>
	    			</tr>
	    		</table>
	    	</div>  <!-- .homepage-container -->
	    	<div class="homepage-container" id="viewed-container">
	    		<div class="container-header">
	    			Viewed Stocks
	    			<div class="add-stocks-container">
	    			
	    				<button class="button" id="view-stock-button">View Stock</button>
	    				<div class="modal" id="view-stock-modal">
	    					<div class="modal-box">
	    						<div class="popup-header">View Stock</div>
	    						<div class="popup-section">
	    							<form id="view-stock-form" name="viewStock" method="post" action="/api/viewstock">
	    								<div class="form-row">
	    									<label for="ticker">Stock Ticker</label>
	    									<input type="text" id="ticker" name="ticker" required>
	    								</div>
	    								<div class="form-row">
	    									<label for="ticker"># of Shares</label>
	    									<input type="number" id="shares" name="shares" required>
	    								</div>
	    								<div class="form-row">
	    									<label for="date-purchased">Date Purchased</label>
	    									<input type="date" id="date-purchased" placeholder="yyyy-mm-dd" name="date-purchased" required>
	    								</div>
	    								<div class="form-row">
	    									<label for="date-sold">Date Sold</label>
	    									<input type="date" id="date-sold" placeholder="yyyy-mm-dd" name="date-sold" required>
	    								</div>
	    								<div class="form-row">
	    									<span class="error-msg">${viewStockErrorMessage}</span>
	    								</div>
	    								<button type="submit" class="button" id="view-stock-submit">View Stock</button>
	    							</form>
	    						</div>
	    						<div class="popup-section">
	    							<button class="button" id="view-stock-cancel">Cancel</button>
	    						</div>
	    					</div>
	    				</div>
	    				
	    			</div>
	    		</div> <!-- .container-header -->
	    		
	    		<table id="stock-list">
	    			<tr>
	    				<td>Apple</td>
	    				<td>AAPL</td>
	    				<td>
	    					<label class="switch">
	    						<input type="checkbox" checked>
							  	<span class="slider round"></span>
							</label>
						</td>
	    				<td><a href=""><i class="fas fa-trash"></i></a></td>
	    			</tr>
	    			<tr>
	    				<td>Tesla</td>
	    				<td>TSLA</td>
	    				<td>
	    					<label class="switch">
	    						<input type="checkbox" checked>
							  	<span class="slider round"></span>
							</label>
						</td>
	    				<td><a href=""><i class="fas fa-trash"></i></a></td>
	    			</tr>
	    			<tr>
	    				<td>Ford Motor</td>
	    				<td>F</td>
	    				<td>
	    					<label class="switch">
	    						<input type="checkbox" checked>
							  	<span class="slider round"></span>
							</label>
						</td>
	    				<td><a href=""><i class="fas fa-trash"></i></a></td>
	    			</tr>
	    		</table>
	    		
	    	</div>  <!-- .homepage-container -->
    	</div>
    </div>
    
    
    
    <!-- Add stock popup box -->
	<script>
		var addStockModal = document.getElementById("add-stock-modal");
		var addStockButton = document.getElementById("add-stock-button");
		var addStockCancelButton = document.getElementById("add-stock-cancel");
		var errorMessage = '${errorMessage}';
		
		// When user clicks add stock button
		addStockButton.onclick = function() {
			document.getElementById("add-stock-form").reset();
			addStockModal.style.display = "flex";
		}
		// When user cancels adding a stock
		addStockCancelButton.onclick = function() {
			addStockModal.style.display = "none";
		}
		// When the user clicks anywhere outside of the box, close it
		window.onclick = function(event) {
			if (event.target == addStockModal) {
				addStockModal.style.display = "none";
			} else if (event.target == importStockModal) {
				importStockModal.style.display = "none";
			} else if (event.target == viewStockModal) {
				viewStockModal.style.display = "none";
			}
		}
		// If the servlet returns an error message, display popup
		if(errorMessage != "") {
			console.log("errorMessage = " + errorMessage);
			addStockModal.style.display = "flex";
		}
	</script>
	
	<!-- Import stocks popup box -->
	<script>
		var importStockModal = document.getElementById("import-stock-modal");
		var importStockButton = document.getElementById("import-stock-button");
		var importStockCancelButton = document.getElementById("import-stock-cancel");
		
		// When user clicks add stock button
		importStockButton.onclick = function() {
			document.getElementById("import-stock-form").reset();
			importStockModal.style.display = "flex";
		}
		// When user cancels adding a stock
		importStockCancelButton.onclick = function() {
			importStockModal.style.display = "none";
		}
	</script>
	
	<!-- View stocks popup box -->
	<script>
		
		
	
	
		var viewStockModal = document.getElementById("view-stock-modal");
		var viewStockButton = document.getElementById("view-stock-button");
		var viewStockCancelButton = document.getElementById("view-stock-cancel");
		var viewStockErrorMessage = '${viewStockErrorMessage}';
		
		// When user clicks add stock button
		viewStockButton.onclick = function() {
			document.getElementById("view-stock-form").reset();
			viewStockModal.style.display = "flex";
		}
		// When user cancels adding a stock
		viewStockCancelButton.onclick = function() {
			viewStockModal.style.display = "none";
		}
		// If the servlet returns an error message, display popup
		if(viewStockErrorMessage != "") {
			console.log("viewStockErrorMessage = " + errorMessage);
			viewStockModal.style.display = "flex";
		}
	</script>
	

	<!-- graph script, main idea and getting data from session done -->
	<script>
	
	var graphdata = <%= (String) session.getAttribute("GraphData") %>
	var labels = <%= (String) session.getAttribute("GraphLabels") %>
	var change_per = <%= (Double) session.getAttribute("ChangePercent") %> 
	var Today_val = <%= (Double) session.getAttribute("TodaysVal") %> 
	Today_val = Today_val.toFixed(2);
	change_per = change_per.toFixed(2);
	
		console.log(labels);
     	console.log(change_per);
     	console.log(Today_val);
   
   
   document.getElementById('portfolio-value').innerHTML = "$" + Today_val;  	
    
    if(change_per>=0)
    {
    	document.getElementById("arrow2").style.display = "none";
    	document.getElementById("arrow").innerHTML = "&#9650" + change_per + "% Today"  
    }
    else
    {
    	document.getElementById("arrow").style.display = "none";
    	document.getElementById("arrow2").innerHTML = "&#128315" + change_per + "% Today"  
    }
    
   var tester = JSON.parse(graphdata[0]);            
   var apple_from_javafile_output = {"borderColor":["rgba(120,0,114, 1)"],"data":[66.809997558594,73.410003662109,77.379997253418,68.339996337891,63.569999694824,73.449996948242,79.480003356934,91.199996948242,106.26000213623,129.03999328613,115.80999755859,117.51000213623],"borderWidth":1,"label":"Apple Inc value in $","fill":"false"}
   console.log(tester)
   console.log (apple_from_javafile_output)
   
   var config = {
    type: 'line',
    data: {
        labels: [],
        datasets: []
    },
    options: {
    	responsive: false,
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero: false
                }
            }]
        }
        
    }
  }
  
	for(var i=0; i<graphdata.length; i++)
	{
		
		
	config.data.datasets.push(JSON.parse(graphdata[i]));	
		
	}

  config.data.labels = labels
	
	var ctx = document.getElementById('myChart');
	var myChart = new Chart(ctx, config);
		
		
	</script>
	
</body>
</html>
