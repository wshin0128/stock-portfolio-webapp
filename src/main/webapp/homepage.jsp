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
			<a href="signIn.jsp" class="button"><i class="fas fa-sign-out-alt"></i>&nbsp&nbspSign Out</a>
		</div>
	</div>
    <div class="wrap">
    	<div class="grid-container">
	    	<div class="homepage-container" id="graph-container">
	    	</div>
	    	<div class="homepage-container" id="portfolio-container">
	    		<div class="container-header">
	    			Your Portfolio
	    			<div class="add-stocks-container">
	    			
	    				<button class="button" id="add-stock-button">Add Stock</button>
	    				<div class="modal" id="add-stock-modal">
	    					<div class="modal-box">
	    						<div class="popup-header">Add Stock</div>
	    						<div class="popup-section">
	    							<form id="add-stock-form">
	    								<div class="form-row">
	    									<label for="ticker">Stock Ticker</label>
	    									<input type="text" id="ticker">
	    								</div>
	    								<div class="form-row">
	    									<label for="ticker"># of Shares</label>
	    									<input type="number" id="shares">
	    								</div>
	    								<div class="form-row">
	    									<label for="date-purchased">Date Purchased</label>
	    									<input type="date" id="date-purchased" placeholder="yyyy-mm-dd">
	    								</div>
	    								<div class="form-row">
	    									<label for="date-sold">Date Sold</label>
	    									<input type="date" id="date-sold" placeholder="yyyy-mm-dd">
	    								</div>
	    								<div class="form-row">
	    									<span class="error-msg">Test error message</span>
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
	    								<button type="submit" class="button" id="import-stock-submit">Import Stocks</button>
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
	    				<td><a href=""><i class="fas fa-trash-alt"></i></a></td>
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
	    				<td><a href=""><i class="fas fa-trash-alt"></i></a></td>
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
	    				<td><a href=""><i class="fas fa-trash-alt"></i></a></td>
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
	    							<form id="view-stock-form">
	    								<div class="form-row">
	    									<label for="ticker">Stock Ticker</label>
	    									<input type="text" id="ticker">
	    								</div>
	    								<div class="form-row">
	    									<label for="ticker"># of Shares</label>
	    									<input type="number" id="shares">
	    								</div>
	    								<div class="form-row">
	    									<label for="date-purchased">Date Purchased</label>
	    									<input type="date" id="date-purchased" placeholder="yyyy-mm-dd">
	    								</div>
	    								<div class="form-row">
	    									<label for="date-sold">Date Sold</label>
	    									<input type="date" id="date-sold" placeholder="yyyy-mm-dd">
	    								</div>
	    								<div class="form-row">
	    									<span class="error-msg">Test error message</span>
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
	    				<td><a href=""><i class="fas fa-trash-alt"></i></a></td>
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
	    				<td><a href=""><i class="fas fa-trash-alt"></i></a></td>
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
	    				<td><a href=""><i class="fas fa-trash-alt"></i></a></td>
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
		
		// When user clicks add stock button
		viewStockButton.onclick = function() {
			document.getElementById("view-stock-form").reset();
			viewStockModal.style.display = "flex";
		}
		// When user cancels adding a stock
		viewStockCancelButton.onclick = function() {
			viewStockModal.style.display = "none";
		}
	</script>
	
</body>
</html>
