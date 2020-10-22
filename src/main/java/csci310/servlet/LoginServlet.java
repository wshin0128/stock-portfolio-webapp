package csci310.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.HTTP;
import org.json.JSONArray;
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
				session.setAttribute("username", uname);
				session.setAttribute("userID", result);
				
				long curr_time = System.currentTimeMillis() / 1000L;
				
				Calendar date= Calendar.getInstance();
			    date.add(Calendar.MONTH, -12); 
			    
			    //12 months before now
			    long start_time =  date.getTimeInMillis() / 1000L;
				
			    //creating new user object and setting respective portfolio
				User u = new User(uname,result);
				u.setPortfolio(db.getPortfolio(result));
				
				// Will use this later to find change %
				HomePageModule Current_user_module = new HomePageModule(u);
				
				// This is the portfolio of the user's view stocks
				Portfolio Current_user_view_portfolio = db.getViewedStocks(result);
				
				//I store date and value for users portfolio value in the portfolio info map
				GraphingModule GMM = new GraphingModule();
				Map<Date, Double> portfolio_info = GMM.getPortfolioValue(db.getPortfolio(result), ResolutionGetter.Month(), start_time,curr_time); //hard coded dates and resolution rn, need to change
				
				// I parse the map and create a properly formatted JSON
				GraphJSONhelper GJH = new GraphJSONhelper();
				String main_portfolio_json = GJH.Total_portfolio_Info(portfolio_info);
				
				// This array list stores all the JSONs that need to be drawn on the graph
				ArrayList<String> user_view_stocks_graph_info = new ArrayList<String>();	
				if(db.getPortfolio(result).getSize() <=0) //if the user has no portfolio, then dont append portfolio info into graph
				{
					System.out.println("empty portfolio");
					
				}
				else
				{
				user_view_stocks_graph_info.add(main_portfolio_json);
				}
				
				
				String Labels = "";
				boolean first_time = true; //need to set labels only once. The helper function returns a pair of Data points JSON and Labels JSON.
				
				//Fills the formatted JSON of Graphing point array list with all Data points of all viewed stocks
				for(Stock s: Current_user_view_portfolio.getPortfolio())
				{
					
					GraphJSONhelper G = new GraphJSONhelper();
					Data_and_Labels DnL = G.StockGraphInfo(s.getTicker(), s.getQuantity(), ResolutionGetter.Month(), start_time, curr_time); //hard coded dates and resolution rn, need to change
					user_view_stocks_graph_info.add(DnL.Data_Json);
					
					if(first_time)
					{
						Labels = DnL.Labels;
					}
					
				}
				
				// change % part
				Double change_percent = Current_user_module.getChangePercentDouble();	
				change_percent = change_percent*100;
				
				//setting attributes 
				session.setAttribute("TodaysVal", Current_user_module.todayTotalDouble);
				
				session.setAttribute("ChangePercent", change_percent);
				
				String GraphData = new JSONArray(user_view_stocks_graph_info).toString();
				
				System.out.println("Graph data has: " + GraphData);
				System.out.println("Graph labsls has: " + Labels);
				

				//I created this for testing purpose when viewstock and portfolio tables were empty. Previously this is what it had:
				// This will only trigger when the user has no portfolio and new viewed stock
				//This will add 2 Tesla, Apple and IBM stocks to the view_list and portfolio
				// Right now: if the user has nothing it makes sure to not graph anything
				if(user_view_stocks_graph_info.size() <=0 && Labels.equals(""))
				{
					session.setAttribute("noGraph", "nograph");				
				}
				else //This executes when the user has atleast a portfolio or viewed stocks.
				{
				session.setAttribute("GraphData", GraphData);
				session.setAttribute("GraphLabels", Labels);
				}
        
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
