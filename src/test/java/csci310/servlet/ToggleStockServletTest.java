package csci310.servlet;

import static org.junit.Assert.*;

import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;

import csci310.model.Portfolio;
import csci310.model.User;
import csci310.service.DatabaseClient;
import csci310.service.FinnhubClient;
import csci310.service.HomePageModule;

public class ToggleStockServletTest extends Mockito{

	@Test
	public void testDoGet() throws SQLException {
		DatabaseClient dbc = new DatabaseClient();
		dbc.createTable();
		
		// Prepare module
		User user = new User("some dummy value", 123);
		Portfolio portfolio = dbc.getPortfolio(123);
		user.setPortfolio(portfolio);
		HomePageModule homePageModule = new HomePageModule(user, new FinnhubClient(), dbc);
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		RequestDispatcher rd = mock(RequestDispatcher.class);
		
		when(request.getParameter("ticker")).thenReturn("AAPL");
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("module")).thenReturn(homePageModule);
		
		// Mock exception
		ToggleStockServlet toggleStockServlet = new ToggleStockServlet();
		toggleStockServlet.doGet(request, response);
		
		when(request.getRequestDispatcher("/homepage.jsp")).thenReturn(rd);
		toggleStockServlet.doGet(request, response);
		
	}

}
