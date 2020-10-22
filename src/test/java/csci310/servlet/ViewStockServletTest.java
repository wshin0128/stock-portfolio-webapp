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

public class ViewStockServletTest extends Mockito {

	@Test
	public void testDoPost() throws Exception {
		// Test Correct input behavior
		
		DatabaseClient dbc = new DatabaseClient();
		dbc.createTable();
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		RequestDispatcher rd = mock(RequestDispatcher.class);
		
		// Mock request parameters
		when(request.getParameter("ticker")).thenReturn("AAPL");
		when(request.getParameter("shares")).thenReturn("14");
		when(request.getParameter("date-purchased")).thenReturn("2020-10-10");
		when(request.getParameter("date-sold")).thenReturn("2020-10-14");
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("userID")).thenReturn(1);
		
		when(request.getRequestDispatcher("/homepage.jsp")).thenReturn(rd);
		
		// Run servlet
		ViewStockServlet vs = new ViewStockServlet();
		vs.doPost(request, response);
		
		// Make sure there is no error message
		assertTrue(request.getAttribute("viewStockErrorMessage") == null);
		
		// Convert dates
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Date datePurchasedObject = dateFormatter.parse("2020-10-10");
		long datePurchasedUnix = datePurchasedObject.getTime();
		Date dateSoldObject = dateFormatter.parse("2020-10-14");
		long dateSoldUnix = dateSoldObject.getTime();
		
		// Check if stock was added to the portfolio
		Portfolio port = dbc.getViewedStocks(1);
		ArrayList<Stock> p = port.getPortfolio();
		assertTrue(p.get(0).getName().equals("Apple Inc"));
		assertTrue(p.get(0).getTicker().equals("AAPL"));
		assertTrue(p.get(0).getQuantity() == 14);
		assertTrue(p.get(0).getBuyDate() == datePurchasedUnix);
		assertTrue(p.get(0).getSellDate() == dateSoldUnix);
		
		// Run again with color override
		when(session.getAttribute("userID")).thenReturn(2);
		when(request.getAttribute("colorOverride")).thenReturn("#000000");
		
		// Run servlet
		vs.doPost(request, response);
		
		// Make sure there is no error message
		assertTrue(request.getAttribute("viewStockErrorMessage") == null);
		
		// Check if stock was added to the portfolio
		Stock s = new Stock("Apple Inc", "AAPL", "#000000", 14, datePurchasedUnix, dateSoldUnix);
		Portfolio port2 = dbc.getViewedStocks(2);
		ArrayList<Stock> p2 = port2.getPortfolio();
		assertTrue(p2.contains(s));
		
		// Check exception catching
		request = null;
		vs.doPost(request, response);
	}
	
	@Test
	public void testDoPostTickerError() throws Exception {
		// Test invalid ticker
		
		DatabaseClient dbc = new DatabaseClient();
		dbc.createTable();
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		RequestDispatcher rd = mock(RequestDispatcher.class);
		
		// Mock request parameters
		when(request.getParameter("ticker")).thenReturn("XKWJE");
		when(request.getParameter("shares")).thenReturn("14");
		when(request.getParameter("date-purchased")).thenReturn("2020-10-10");
		when(request.getParameter("date-sold")).thenReturn("2020-10-14");
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("userID")).thenReturn(8);
		when(request.getAttribute("colorOverride")).thenReturn("#000000");
		when(request.getRequestDispatcher("/homepage.jsp")).thenReturn(rd);
		
		// Run servlet
		ViewStockServlet vs = new ViewStockServlet();
		vs.doPost(request, response);
		
		// Make sure there is an error message
		verify(request).setAttribute("viewStockErrorMessage", "Your ticker is invalid");
	}
	
	@Test
	public void testDoPostSharesError() throws Exception {
		// Test incorrect number of shares
		
		DatabaseClient dbc = new DatabaseClient();
		dbc.createTable();
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		RequestDispatcher rd = mock(RequestDispatcher.class);
		
		// Mock request parameters
		when(request.getParameter("ticker")).thenReturn("AAPL");
		when(request.getParameter("shares")).thenReturn("-2");
		when(request.getParameter("date-purchased")).thenReturn("2020-10-10");
		when(request.getParameter("date-sold")).thenReturn("2020-10-14");
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("userID")).thenReturn(8);
		when(request.getAttribute("colorOverride")).thenReturn("#000000");
		when(request.getRequestDispatcher("/homepage.jsp")).thenReturn(rd);
		
		// Run servlet
		ViewStockServlet vs = new ViewStockServlet();
		vs.doPost(request, response);
		
		// Make sure there is an error message
		verify(request).setAttribute("viewStockErrorMessage", "You must buy at least one share");
		
	}
	
	@Test
	public void testDoPostDateError() throws Exception {
		// Test incorrect date combination
		
		DatabaseClient dbc = new DatabaseClient();
		dbc.createTable();
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		RequestDispatcher rd = mock(RequestDispatcher.class);
		
		// Mock request parameters
		when(request.getParameter("ticker")).thenReturn("AAPL");
		when(request.getParameter("shares")).thenReturn("14");
		when(request.getParameter("date-purchased")).thenReturn("2020-10-14");
		when(request.getParameter("date-sold")).thenReturn("2020-10-10");
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("userID")).thenReturn(8);
		when(request.getAttribute("colorOverride")).thenReturn("#000000");
		when(request.getRequestDispatcher("/homepage.jsp")).thenReturn(rd);
		
		// Run servlet
		ViewStockServlet vs = new ViewStockServlet();
		vs.doPost(request, response);
		
		// Make sure there is an error message
		verify(request).setAttribute("viewStockErrorMessage", "Your sell date must be after your buy date");
	}

}
