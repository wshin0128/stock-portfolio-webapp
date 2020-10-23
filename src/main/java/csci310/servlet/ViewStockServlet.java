package csci310.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;	
import csci310.model.Stock;
import csci310.service.DatabaseClient;
import csci310.service.FinnhubClient;

public class ViewStockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {		
			DatabaseClient dbc = new DatabaseClient();
			FinnhubClient fc = new FinnhubClient();

			// Get form values
			String ticker = request.getParameter("ticker");
			int shares = Integer.parseInt(request.getParameter("shares"));
			String datePurchased = request.getParameter("date-purchased");
			String dateSold = request.getParameter("date-sold");
						
			// Convert dates to UNIX time
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
			Date datePurchasedObject = dateFormatter.parse(datePurchased);
			long datePurchasedUnix = datePurchasedObject.getTime();
			Date dateSoldObject = dateFormatter.parse(dateSold);
			long dateSoldUnix = dateSoldObject.getTime();
			
			// Get userID from session
			int userID = (Integer) request.getSession().getAttribute("userID");
			
			// Lookup stock name using ticker
			String companyName = "";
			boolean validTicker = true;
			try {
				companyName = fc.getCompanyNameString(ticker);
			} catch(Exception e) {
				validTicker = false;
			}
			
			// If stock ticker is invalid
			if(!validTicker) {
				System.out.println("Ticker invalid");
				request.setAttribute("viewStockErrorMessage", "Your ticker is invalid");
				request.getRequestDispatcher("/homepage.jsp").forward(request, response);
				return;
			}
			// If number of shares is 0 or smaller, set error
			else if(shares <= 0) {
				System.out.println("Shares invalid");
				request.setAttribute("viewStockErrorMessage", "You must buy at least one share");
				request.getRequestDispatcher("/homepage.jsp").forward(request, response);
				return;
			}
			// If purchase date is after sold date, set error and go back home
			else if(datePurchasedUnix >= dateSoldUnix) {
				System.out.println("Dates invalid");
				request.setAttribute("viewStockErrorMessage", "Your sell date must be after your buy date");
				request.getRequestDispatcher("/homepage.jsp").forward(request, response);
				return;
			}
			// No errors, add stock to database
			else {
				// Color override (Admin feature, not visible to user)
				if(request.getAttribute("colorOverride") != null) {
					Stock s = new Stock(companyName, ticker, (String)request.getAttribute("colorOverride"), shares, datePurchasedUnix, dateSoldUnix);
					dbc.addStockToViewed(userID, s);
				} else {
					Stock s = new Stock(companyName, ticker, null, shares, datePurchasedUnix, dateSoldUnix);
					dbc.addStockToViewed(userID, s);
				}
			}
			
			// Go back to homepage
			System.out.println("No errors");
			request.getRequestDispatcher("/homepage.jsp").forward(request, response);
			return;
			
		} catch (Exception e) {
			System.out.println("Exception from ViewStockServlet.doPost()");
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}
