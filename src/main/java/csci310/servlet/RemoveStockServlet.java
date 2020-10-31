package csci310.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import csci310.model.Stock;
import csci310.service.DatabaseClient;
import csci310.service.FinnhubClient;
import csci310.service.HomePageModule;

import java.text.SimpleDateFormat;
import java.util.Date;



public class RemoveStockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
	}
}
