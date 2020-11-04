package csci310.servlet;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import csci310.service.HomePageModule;



public class ToggleStockServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {		
			String tickerString = request.getParameter("ticker");
			HomePageModule homePageModule = (HomePageModule) request.getSession().getAttribute("module");
			homePageModule.toggleStock(tickerString);
			request.getRequestDispatcher("/homepage.jsp").forward(request, response);
			return;
		} catch (Exception e) {
			System.out.println("Exception from ToggleServlet.doPost()");
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}
