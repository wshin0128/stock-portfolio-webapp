package csci310.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import csci310.service.DatabaseClient;
import csci310.service.FinnhubClient;
import csci310.service.HomePageModule;

public class RemoveStockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			// Get homepage module from session
			HomePageModule homePageModule = (HomePageModule) request.getSession().getAttribute("module");
			
			DatabaseClient dbc = new DatabaseClient();
			FinnhubClient fc = new FinnhubClient();

			// Get ticker
			String ticker = request.getParameter("ticker");
			
			// Get userID from session
			int userID = (Integer) request.getSession().getAttribute("userID");
			
			// Get whether removing from portfolio or viewed stocks
			String selector = request.getParameter("selector");
			
			// Remove stock from either portfolio or viewed stocks
			if(selector.equalsIgnoreCase("portfolio")) {
				// TODO: Remove stock from portfolio
				
			} else if(selector.equalsIgnoreCase("viewed")) {
				// TODO: Remove stock from viewed stock list
			}
			
			// Go back to homepage page
			request.getRequestDispatcher("/homepage.jsp").forward(request, response);
			
		} catch (Exception e) {
			System.out.println("Exception from RemoveStockServlet.doPost()");
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}
