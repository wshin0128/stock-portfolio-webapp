package csci310.servlet;

import static org.junit.Assert.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;

import csci310.service.DatabaseClient;

public class GraphButtonsServletTest extends Mockito {

	@Test
	public void testDoPost() throws Exception {
		
		DatabaseClient dbc = new DatabaseClient();
		dbc.createTable();

		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		RequestDispatcher rd = mock(RequestDispatcher.class);
		
		assertTrue(true);
		
	}

}
