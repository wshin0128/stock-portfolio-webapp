package csci310.servlet;


import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import csci310.model.Portfolio;
import csci310.model.Stock;
import csci310.service.HomePageModule;



public class ToggleStockServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {		
			String tickerString = request.getParameter("ticker");
			String typeString = request.getParameter("type");
			HomePageModule homePageModule = (HomePageModule) request.getSession().getAttribute("module");
			if (typeString.equals("selectAll")) {
				Map<Stock, Boolean> ownedMap = homePageModule.getStockToGraphMap();
				for (Stock stock : ownedMap.keySet()) {
					if (ownedMap.get(stock) == false) { // not selected
						homePageModule.toggleStock(stock.getTicker());
					}
				}
			}
			else if (typeString.equals("deSelectAll")) {
				Map<Stock, Boolean> ownedMap = homePageModule.getStockToGraphMap();
				for (Stock stock : ownedMap.keySet()) {
					if (ownedMap.get(stock) == true) { // selected
						homePageModule.toggleStock(stock.getTicker());
					}
				}
			}
			else if (typeString.equals("owned")){
				homePageModule.toggleStock(tickerString);
			} else {
				homePageModule.toggleViewedStock(tickerString);
			}
			request.getRequestDispatcher("/homepage.jsp").forward(request, response);
			return;
		} catch (Exception e) {
			System.out.println("Exception from ToggleServlet.doPost()");
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}
