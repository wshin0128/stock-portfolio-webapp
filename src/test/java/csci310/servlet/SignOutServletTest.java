package csci310.servlet;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;

public class SignOutServletTest extends Mockito {

	@Test
	public void testDoPostHttpServletRequestHttpServletResponse() throws ServletException, IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		when(request.getParameter("username")).thenReturn("Test");
		when(request.getParameter("password")).thenReturn("Test2");
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        SignOutServlet sos = new SignOutServlet();
		sos.doPost(request, response);

		writer.flush();
		assertTrue(stringWriter.toString().contains("0"));
	}

}
