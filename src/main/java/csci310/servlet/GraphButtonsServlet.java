package csci310.servlet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/GraphButtons")
public class GraphButtonsServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
try {
		HttpSession session = request.getSession();
		System.out.println(request.getParameter("timePeriod"));
		System.out.println(request.getParameter("SNP500"));
		
		String tp = (String)request.getParameter("timePeriod");
		String snp = (String)request.getParameter("SNP500");
	
	if(snp!=null) {	
		if(snp.equals("1"))
		{
			
			session.setAttribute("snp", "1");
			//request.getRequestDispatcher("/homepage.jsp").forward(request, response);
			//return;
		}
		else
		{
			session.setAttribute("snp", null);
		}
	}
	
	
		if(tp.equals("1D"))
		{
			session.setAttribute("tp", "1");
			System.out.println("set tp to 1");
		}
		else if(tp.equals("1W"))
		{
			session.setAttribute("tp", "2");
			System.out.println("set tp to 2");
		}
		else if(tp.equals("1M"))
		{
			session.setAttribute("tp", "3");
			System.out.println("set tp to 3");
		}
		else if(tp.equals("1Y"))
		{
			session.setAttribute("tp", "4");
			System.out.println("set tp to 4");
		}

		
			request.getRequestDispatcher("/homepage.jsp").forward(request, response);	
		}
		catch (Exception e) {
			System.out.println("Exception from GraphButtons.doPost()");
			System.out.println(e);
			request.getRequestDispatcher("/homepage.jsp").forward(request, response);	
			//response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
			
	}
	
}
