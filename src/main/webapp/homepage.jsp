<%@page import="org.json.JSONArray"%>
<%@page import="csci310.service.GraphJSONhelper.Data_and_Labels"%>
<%@page import="csci310.service.GraphJSONhelper"%>
<%@page import="java.util.Calendar"%>
<%@page import="csci310.service.Resolution"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Map"%>
<%@page import="csci310.service.GraphingModule"%>
<%@page import="csci310.service.DatabaseClient"%>
<%@page import="csci310.model.Portfolio"%>
<%@page import="csci310.model.Stock"%>
<%@page import="java.util.ArrayList"%>
<%@page import="csci310.service.HomePageModule"%>
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
	<script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.3/dist/Chart.min.js" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/hammerjs@2.0.8/hammer.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-zoom@0.7.5/dist/chartjs-plugin-zoom.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	
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
    <% 
    	HomePageModule homePageModule = (HomePageModule) request.getSession().getAttribute("module"); 
        DatabaseClient db = new DatabaseClient();
        ArrayList<Stock> stockList = homePageModule.getStockList(); // owned stock
        ArrayList<Stock> viewedStocks = homePageModule.getViewedStockList();
        // change format to xxx.xx
        double percent = ((int) (homePageModule.getChangePercentDouble() * 10000)) / 100.0;
        
        System.out.println("percent is = " + percent);
        
        
        // chaneg format to xxx.xx
        double portfolioValue = (int) (homePageModule.getPortfolioValue() * 100) / 100.0;
        
        System.out.println("portfolio is = " + portfolioValue);
        
        // Calculate graph data
        // Calculate start time and current time
        long curr_time = System.currentTimeMillis() / 1000L;
        String tp =(String) session.getAttribute("tp");
		Calendar date= Calendar.getInstance();
		Resolution r = Resolution.Weekly;
		
		if(tp==null)
	    {
			date.add(Calendar.MONTH, -3); 
	    }
	    else if(tp.equals("1"))
	    {
	    	date.add(Calendar.DATE, -4); 
	    	r = Resolution.Daily;
	    }
	    else if(tp.equals("2"))
	    {
	    	date.add(Calendar.DATE, -9); 
	    	r = Resolution.Daily;
	    }
	    else if(tp.equals("3"))
	    {
	    	date.add(Calendar.MONTH, -1); 
	    	r = Resolution.Weekly;
	    }
	    else if(tp.equals("4"))
	    {
	    	date.add(Calendar.MONTH, -12); 
	    	r = Resolution.Monthly;
	    }
	    
	    long start_time =  date.getTimeInMillis() / 1000L;
	    
	    System.out.println("start time = " + start_time);
	    
        int userID = (int) session.getAttribute("userID");
        Portfolio Current_user_view_portfolio = db.getViewedStocks(userID);
        GraphingModule GMM = new GraphingModule();
		Map<Date, Double> portfolio_info = GMM.getPortfolioValue(db.getPortfolio(userID), r, start_time,curr_time);
		
		// Parse owned stock portfolio info into string
		GraphJSONhelper GJH = new GraphJSONhelper();
		Data_and_Labels Dn_L = GJH.Total_portfolio_Info(portfolio_info);
		String main_portfolio_json = Dn_L.Data_Json;
		
		
		
		ArrayList<String> userGraphInfo = new ArrayList<String>();	
		String Labels = "";
		Labels = Dn_L.Labels;
		if(db.getPortfolio(userID).getSize() <=0) //if the user has no portfolio, then dont append portfolio info into graph
		{
			System.out.println("empty portfolio");
			
		}
		else
		{
		    userGraphInfo.add(main_portfolio_json);
		    // Initialize labels
		}
        // Calculate labels
        boolean first_time = true; //need to set labels only once. The helper function returns a pair of Data points JSON and Labels JSON.
				
		//Fills the formatted JSON of Graphing point array list with all Data points of all viewed stocks
		for(Stock stock: Current_user_view_portfolio.getPortfolio())
		{
			
			GraphJSONhelper G = new GraphJSONhelper();
			Data_and_Labels DnL = G.StockGraphInfo(stock.getTicker(), stock.getQuantity(), r, start_time, curr_time); 
			userGraphInfo.add(DnL.Data_Json);
			
			if(first_time)
			{
				Labels = DnL.Labels;
				first_time = false;
				
				
			}
			
		}
        
		String snp = (String)session.getAttribute("snp");
		
		if(snp!=null)
		{
			GraphJSONhelper GG = new GraphJSONhelper();
			Data_and_Labels DnL = GG.StockGraphInfo("SPY", 1, r, start_time, curr_time); 
			userGraphInfo.add(DnL.Data_Json);
			Labels = DnL.Labels;
		}
        
		String GraphData = new JSONArray(userGraphInfo).toString();
		
        // Calculate isgraph
        boolean isGraph = Labels.equals("") ? false : true;
    %>
	<div class="navbar">
		<div class="wrap">
			<h1>USC CS 310: Stock Portfolio Management</h1>
			<a href="http://localhost:8080/signIn.jsp" class="button"><i class="fas fa-sign-out-alt"></i>&nbsp&nbspSign Out</a>
		</div>
	</div>
    <div class="wrap">
    	<div class="grid-container">
    	
	    	<div class="homepage-container" id="graph-container">
	    		<div class="graph-header">
	    			<span id="portfolio-value">$<%=portfolioValue%></span>
	    			<div id="portfolio-value-change" style="color: #51C58E;">
	    			    <% if (percent > 0) {%>
				            <span id="arrow">&#9650 +<%=percent %>% Today</span>
				    	<% } %>
    					<% if (percent <= 0) {%>
				            <span id="arrow2">&#128315 <%=percent %>% Today</span>
				    	<% } %>
	    			</div>
	    			<div>
	    			<button class="btn" id="zoomin"><i class="fas fa-search-plus"></i></button>
	    			<button class="btn" id = "zoomout"><i class="fas fa-search-minus"></i></button>
	    			</div>
	    		</div>

	    		<form name="GraphButtons" method="post" action="/api/GraphButtons">
	     		<div class="row justify-content-center" role="group" aria-label="Basic example">
				  <input type="submit" id="1-day-btn" class="btn btn-secondary" name="timePeriod" value="1D"/>
				  <input type="submit" id="1-week-btn" class="btn btn-secondary" name="timePeriod" value="1W"/>
				  <input type="submit" id="1-month-btn" class="btn btn-secondary" name="timePeriod" value="1M"/>
				  <input type="submit" id="1-year-btn" class="btn btn-secondary" name="timePeriod" value="1Y"/>
				</div>
				<div>
				SNP500 
				<input onChange="this.form.submit()" type="checkbox" name="SNP500" value="1" <%if(session.getAttribute("snp")!=null){%> <%="checked"%> <% } %>/>
				<input  type="hidden" name="SNP500" value="0"/>
				</div>
				
	    		<div class = "graph-main">
	    		<div class="canvas-container">
	    			<canvas id="myChart" width="300" height="200"></canvas>
	    		</div>
	    		<div>
	    		<i>      Start Date:</i>      
	    		<input type="date" id="start_date" name="start_date">
	    		<i>  End Date:</i>    
	    		<input type="date" id="end_date" name="end_date">
  				<input type="submit">
	    		</div>
				</form>
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
	    							<form action="/api/csvimport" method="post" id="import-stock-form" enctype="multipart/form-data">
	    								<div class="form-row">
	    									<label for=""csvImport"">Upload a .csv file</label>
	    									<input type="file" name="file" id="csvImport" accept=".csv">
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
	    		     
	    		     <% for(Stock stock : stockList) { %>
				        <tr>      
				            <td><%=stock.getName()%></td>
				            <td><%=stock.getTicker()%></td>
				            <td>
	    					<label class="switch">
	    						<input type="checkbox" checked>
							  	<span class="slider round"></span>
							</label>
						</td>
	    				<td><a href="/api/removestock?ticker=<%=stock.getTicker()%>&selector=portfolio" class="remove-stock-portfolio-button" onclick="return confirm('Are you sure you want to delete <%=stock.getName()%>?')"><i class="fas fa-trash"></i></a></td>
				        </tr>
				        
				    <% } %>
	    		
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
	    		
	    		<!-- table for view stock -->
	    		<table id="stock-list">
	    		    <% for(Stock stock : viewedStocks) { %>
				        <tr>      
				            <td><%=stock.getName()%></td>
				            <td><%=stock.getTicker()%></td>
				            <td>
	    					<label class="switch">
	    						<input type="checkbox" checked>
							  	<span class="slider round"></span>
							</label>
						</td>
	    				<td><a href="/api/removestock?ticker=<%=stock.getTicker()%>&selector=viewed" class="remove-stock-portfolio-button" onclick="return confirm('Are you sure you want to delete viewed stock: <%=stock.getName()%>?')"><i class="fas fa-trash"></i></a></td>
				        </tr>
				        
				    <% } %>
	    			
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
	
	// Graph variables
	var isGraph = '<%= isGraph %>'
	var graphdata = <%= GraphData %>
	var labels = <%= Labels %>
    
	var tester = JSON.parse(graphdata[0]);            
	var apple_from_javafile_output = {"borderColor":["rgba(120,0,114, 1)"],"data":[66.809997558594,73.410003662109,77.379997253418,68.339996337891,63.569999694824,73.449996948242,79.480003356934,91.199996948242,106.26000213623,129.03999328613,115.80999755859,117.51000213623],"borderWidth":1,"label":"Apple Inc value in $","fill":"false"}
	console.log(tester)
	console.log (apple_from_javafile_output)
   
	// Graph options
	var config = {
		type: 'line',
	    data: {
	        labels: [],
	        datasets: []
	    },
	    options: {
	    	responsive: true,
	    	maintainAspectRatio: false,
	        scales: {
	            yAxes: [{
	                ticks: {
	                    beginAtZero: false
	                }
	            }]
	        },
	    plugins: {
            zoom: {
                // Container for pan options
                pan: {
                    // Boolean to enable panning
                    enabled: true,

                    // Panning directions. Remove the appropriate direction to disable 
                    // Eg. 'y' would only allow panning in the y direction
                    mode: 'xy'
                },

                // Container for zoom options
                zoom: {
                    // Boolean to enable zooming
                    enabled: true,

                    // Zooming directions. Remove the appropriate direction to disable 
                    // Eg. 'y' would only allow zooming in the y direction
                    mode: 'xy',
                }
            }
        }
  	}
	}
  
	for(var i=0; i<graphdata.length; i++) {
		config.data.datasets.push(JSON.parse(graphdata[i]));	
	}
	
	config.data.labels = labels
	
	var ctx = document.getElementById('myChart');
	var myChart = new Chart(ctx, config); 
	
	$('#zoomin').click(function(){
	    
		console.log("Zoom in ")
		
	    var evt = document.createEvent('MouseEvents');
	    evt.initEvent('wheel', true, true); evt.deltaY = -1000; 
	    document.getElementById("myChart").dispatchEvent(evt);
	    document.getElementById("myChart").dispatchEvent(evt);

	});
	
$('#zoomout').click(function(){
	    
	console.log("Zoom out ")
	
	    var evt = document.createEvent('MouseEvents');
	    evt.initEvent('wheel', true, true); evt.deltaY = 1000; 
	    document.getElementById("myChart").dispatchEvent(evt);
	    document.getElementById("myChart").dispatchEvent(evt);

	});
			
	</script>
	
</body>
</html>
