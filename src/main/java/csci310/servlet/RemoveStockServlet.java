package csci310.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import csci310.service.HomePageModule;

public class RemoveStockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			// Get homepage module from session
			HomePageModule homePageModule = (HomePageModule) request.getSession().getAttribute("module");

			// Get ticker
			String ticker = request.getParameter("ticker");
			
			// Get whether removing from portfolio or viewed stocks
			String selector = request.getParameter("selector");
			
			// Remove stock from either portfolio or viewed stocks
			if(selector.equalsIgnoreCase("portfolio")) {
				// TODO: Remove stock from portfolio
				homePageModule.removeStock(ticker);
			} else if(selector.equalsIgnoreCase("viewed")) {
				// TODO: Remove stock from viewed stock list
				homePageModule.removeViewedStock(ticker);
			}
			// Go back to homepage page
			request.getRequestDispatcher("/homepage.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}
