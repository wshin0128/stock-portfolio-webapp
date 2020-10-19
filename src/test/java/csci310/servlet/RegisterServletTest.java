package csci310.servlet;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.Mockito;

public class RegisterServletTest extends Mockito {

	@Test
	public void testDoPostHttpServletRequestHttpServletResponse() throws ServletException, IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		when(request.getParameter("username")).thenReturn("Test");
		when(request.getParameter("password")).thenReturn("Test2");
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        RegisterServlet rs = new RegisterServlet();
		rs.doPost(request, response);

		writer.flush();
		assertTrue(stringWriter.toString().contains("0"));
	}

}
