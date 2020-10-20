package csci310.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import csci310.model.Portfolio;
import csci310.model.Stock;
import csci310.model.User;
import csci310.service.DatabaseClient;
import csci310.service.GraphJSONhelper;
import csci310.service.GraphJSONhelper.Data_and_Labels;
import csci310.service.HomePageModule;
import csci310.service.*;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			DatabaseClient db = new DatabaseClient();
			PasswordAuthentication passAuth = new PasswordAuthentication();
			String hashedPass = passAuth.hash("test2test", null, null);
			db.createUser("test2", hashedPass);//Here atm for testing purposes as no way to create user
			int result = 0;
			
			//Takes POST parameters and parses them into JSON String
			String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			HttpSession session = request.getSession();
			//Parse JSON string into a useable JSON object
			//JSON object can be used to extract username and password
			JSONObject jo = new JSONObject(requestBody);
			String uname = jo.getString("username");
			String password =jo.getString("password");
			//Passes password and username into the database with the password authentication class to be hashed
			result = db.getUser(passAuth, uname, password);
			System.out.println(result);
			
			//Username is incorrect
			if(result == 0) {
				session.setAttribute("login", false);
			}
			//Valid username and password
			else if(result >= 1) {
				session.setAttribute("login", true);
				session.setAttribute("loginID", uname);
				
								
				User u = new User(uname,result);
				
				HomePageModule Current_user_module = new HomePageModule(u);
				
				Portfolio Current_user_view_portfolio = db.getViewedStocks(result);
				
				ArrayList<String> user_view_stocks_graph_info = new ArrayList<String>();
				ArrayList<String> Labels = null;
				boolean first_time = true; //need to set labels only once
				
				for(Stock s: Current_user_view_portfolio.getPortfolio())
				{
					
					GraphJSONhelper G = new GraphJSONhelper();
					Data_and_Labels DnL = G.StockGraphInfo(s.getTicker(), ResolutionGetter.Month(), 1572566400, 1601942400); //hard coded dates rn, need to change
					user_view_stocks_graph_info.add(DnL.Data_Json);
					
					if(first_time)
					{
						Labels = new ArrayList<String>(DnL.Labels);
					}
					
				}
				
				session.setAttribute("GraphData", user_view_stocks_graph_info);
				session.setAttribute("GraphLabels", Labels);
				
			}
			//Password is incorrect for user
			else{
				session.setAttribute("login", false);
			}
			try {
				PrintWriter pw = response.getWriter();
				pw.write("" + result);
				pw.flush();
			} catch (IOException e) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				System.out.println("Error");
				return;
			}
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println("Error2");
		}
	}
}
