package csci310.servlet;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;

import csci310.model.Portfolio;
import csci310.model.Stock;
import csci310.service.DatabaseClient;

public class RemoveStockServletTest extends Mockito {

	@Test
	public void testDoGet() throws Exception {
		// Test Correct input behavior for removing from portfolio
		
		DatabaseClient dbc = new DatabaseClient();
		dbc.createTable();
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		RequestDispatcher rd = mock(RequestDispatcher.class);
		
		// Mock request parameters
		when(request.getParameter("ticker")).thenReturn("AAPL");
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("userID")).thenReturn(123);
		when(request.getParameter("selector")).thenReturn("portfolio");
		
		when(request.getRequestDispatcher("/homepage.jsp")).thenReturn(rd);
		
		// Run servlet
		RemoveStockServlet rs = new RemoveStockServlet();
		rs.doGet(request, response);
		
		// Check if stock was removed from the portfolio
		Portfolio port = dbc.getPortfolio(123);
		ArrayList<Stock> p = port.getPortfolio();
		for(int i = 0; i < p.size(); i++) {
			assertTrue(!p.get(i).getTicker().equalsIgnoreCase("AAPL"));
		}
		
		// Test Correct input behavior for removing from viewed stocks
		when(request.getParameter("selector")).thenReturn("viewed");
		
		// Run servlet again
		rs.doGet(request, response);
		
		// Check if stock was removed from the portfolio
		Portfolio viewed = dbc.getViewedStocks(123);
		ArrayList<Stock> v = viewed.getPortfolio();
		for(int i = 0; i < v.size(); i++) {
			assertTrue(!v.get(i).getTicker().equalsIgnoreCase("AAPL"));
		}
	}
}
