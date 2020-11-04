package csci310.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import csci310.model.Stock;
import csci310.service.DatabaseClient;
import csci310.service.FinnhubClient;
import csci310.service.HomePageModule;

import java.text.SimpleDateFormat;
import java.util.Date;



public class AddStockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			// Get homepage module from session
			HomePageModule homePageModule = (HomePageModule) request.getSession().getAttribute("module");
			
			DatabaseClient dbc = new DatabaseClient();
			FinnhubClient fc = new FinnhubClient();
			
			boolean formError = false;
			
			System.out.println("Showing form data:");
			System.out.println(request.getParameter("ticker"));
			System.out.println(request.getParameter("shares"));
			System.out.println(request.getParameter("date-purchased"));
			System.out.println(request.getParameter("date-sold"));
			
			
			// Check if form was populated
			if(request.getParameter("ticker").equals("")) {
				request.setAttribute("errorMessage", "Illegal ticker symbol");
				request.setAttribute("errorMessageTicker", "Illegal ticker symbol");
				formError = true;
			}
			if(request.getParameter("shares").equalsIgnoreCase("")) {
				request.setAttribute("errorMessage", "Negative or zero values of quantity");
				request.setAttribute("errorMessageShares", "Negative or zero values of quantity");
				formError = true;
			}
			if((!request.getParameter("date-sold").equals("")) && (request.getParameter("date-purchased").equals(""))) {
				request.setAttribute("errorMessage", "Sold date with no purchase date");
				request.setAttribute("errorMessageDateSold", "Sold date with no purchase date");
				formError = true;
			}
			if((request.getParameter("date-sold").equals("")) && (!request.getParameter("date-purchased").equals(""))) {
				request.setAttribute("errorMessage", "Purchase date with no sold date");
				request.setAttribute("errorMessageDatePurchased", "Purchase date with no sold date");
				formError = true;
			}
			if(request.getParameter("date-purchased").equals("")) {
				request.setAttribute("errorMessage", "Purchase date is required");
				request.setAttribute("errorMessageDatePurchased", "Purchase date is required");
				formError = true;
			}
			if(request.getParameter("date-sold").equals("")) {
				request.setAttribute("errorMessage", "Sold date is required");
				request.setAttribute("errorMessageDateSold", "Sold date is required");
				formError = true;
			}
			
			// Lookup stock name using ticker
			String ticker = request.getParameter("ticker").toUpperCase();
			String companyName = "";
			boolean validTicker = true;
			try {
				companyName = fc.getCompanyNameString(ticker);
			} catch(Exception e) {
				validTicker = false;
			}
			
			// If stock ticker is invalid
			if(!validTicker) {
				request.setAttribute("errorMessage", "Illegal ticker symbol");
				request.setAttribute("errorMessageTicker", "Illegal ticker symbol");
				formError = true;
			}
			
			// If number of shares is 0 or smaller, set error
			int shares = 0;
			if(!request.getParameter("shares").equalsIgnoreCase("")) {
				shares = Integer.parseInt(request.getParameter("shares"));
				if(shares <= 0) {
					request.setAttribute("errorMessage", "Negative or zero values of quantity");
					request.setAttribute("errorMessageShares", "Negative or zero values of quantity");
					formError = true;
				}
			}
			
			// Check dates
			String datePurchased = "";
			String dateSold = "";
			long datePurchasedUnix = 0;
			long dateSoldUnix = 0;
			if(!request.getParameter("date-purchased").equalsIgnoreCase("") && !request.getParameter("date-sold").equalsIgnoreCase("")) {
				// Get form values
				datePurchased = request.getParameter("date-purchased");
				dateSold = request.getParameter("date-sold");

				// Convert dates to UNIX time
				SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyy");
				Date datePurchasedObject = dateFormatter.parse(datePurchased);
				datePurchasedUnix = datePurchasedObject.getTime();
				Date dateSoldObject = dateFormatter.parse(dateSold);
				dateSoldUnix = dateSoldObject.getTime();
				
				// Validate date
				Date now = new Date();
		        long currentTimeUnix = now.getTime() / 1000L;
		        long oneYearAgo = (currentTimeUnix - 31536000) * 1000;
		        
		        // If purchase date is after sold date, set error and go back home
		        if(datePurchasedUnix >= dateSoldUnix) {
		        	request.setAttribute("errorMessage", "Sold date prior to purchase date");
		        	request.setAttribute("errorMessageDateSold", "Sold date prior to purchase date");
		        	formError = true;
				}
		        
		        // Make sure no dates are older than one year ago
		        System.out.println("Purchased time: " + datePurchasedUnix);
		        System.out.println("Sold time: " + dateSoldUnix);
		        System.out.println("One year ago: " + oneYearAgo);
		        if(datePurchasedUnix < oneYearAgo) {
		        	request.setAttribute("errorMessageDatePurchased", "Purchase date cannot be older than 1 year ago");
		        	formError = true;
		        }
		        if(dateSoldUnix < oneYearAgo) {
		        	request.setAttribute("errorMessageDateSold", "Sold date cannot be older than 1 year ago");
		        	formError = true;
		        }
			}
			
			if(formError) {
				request.getRequestDispatcher("/homepage.jsp").forward(request, response);
				return;
			}
			
			// Get userID from session
			int userID = (Integer) request.getSession().getAttribute("userID");

			// Color override (Admin feature, not visible to user)
			if(request.getAttribute("colorOverride") != null) {
				Stock s = new Stock(companyName, ticker, (String)request.getAttribute("colorOverride"), shares, datePurchasedUnix, dateSoldUnix);
				// add stock to database
				// dbc.addStockToPortfolio(userID, s);
				// add stock to front end
				homePageModule.addStock(s); }
			else {
				Stock s = new Stock(companyName, ticker, null, shares, datePurchasedUnix, dateSoldUnix);
				// add stock to database
				// dbc.addStockToPortfolio(userID, s);
				// add stock to front end
				homePageModule.addStock(s);
			}
			// Go back to homepage page
			request.getRequestDispatcher("/homepage.jsp").forward(request, response); }
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception from AddStockServlet.doPost()");
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}
