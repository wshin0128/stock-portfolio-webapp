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
				session.setAttribute("loginID", uname);
				
				long curr_time = System.currentTimeMillis() / 1000L;
				
				Calendar date= Calendar.getInstance();
			    date.add(Calendar.MONTH, -12); //12 months before now
			    long start_time =  date.getTimeInMillis() / 1000L;
								
				User u = new User(uname,result);
				
				u.setPortfolio(db.getPortfolio(result));
				
				HomePageModule Current_user_module = new HomePageModule(u);
				
				Portfolio Current_user_view_portfolio = db.getViewedStocks(result);
				
				GraphingModule GMM = new GraphingModule();
				
				Map<Date, Double> portfolio_info = GMM.getPortfolioValue(db.getPortfolio(result), ResolutionGetter.Month(), start_time,curr_time); //hard coded dates and resolution rn, need to change
				
				GraphJSONhelper GJH = new GraphJSONhelper();
				
				String main_portfolio_json = GJH.Total_portfolio_Info(portfolio_info);
				
				ArrayList<String> user_view_stocks_graph_info = new ArrayList<String>();
				
				user_view_stocks_graph_info.add(main_portfolio_json);
				
				String Labels = "";
				boolean first_time = true; //need to set labels only once
				
				
				
				for(Stock s: Current_user_view_portfolio.getPortfolio())
				{
					
					GraphJSONhelper G = new GraphJSONhelper();
					
					
					
					
					Data_and_Labels DnL = G.StockGraphInfo(s.getTicker(), ResolutionGetter.Month(), start_time, curr_time); //hard coded dates and resolution rn, need to change
					user_view_stocks_graph_info.add(DnL.Data_Json);
					
					if(first_time)
					{
						Labels = DnL.Labels;
					}
					
				}
				
				// change % part
				Double change_percent = Current_user_module.getChangePercentDouble();
				
				change_percent = change_percent*100;
				
//				Collection<Double> todaysValuetemp =  GMM.getPortfolioValue(db.getPortfolio(result), ResolutionGetter.Day(), curr_time-1,curr_time).values();
//				
//				ArrayList<Double> temp_placeholder = new ArrayList<>(todaysValuetemp);
//				
//				double Todays_value = temp_placeholder.get(0);
//
				session.setAttribute("TodaysVal", Current_user_module.todayTotalDouble);
				
				session.setAttribute("ChangePercent", change_percent);
				
				String GraphData = new JSONArray(user_view_stocks_graph_info).toString();
				
				System.out.println("Graph data has: " + GraphData);
				System.out.println("Graph labsls has: " + Labels);
				
				//rn there is nothing as the test user has no viewed stock and portfolio. FOr the moment, I am hard coding view stocks and portfolio 
				
				if(Labels.equals(""))
				{
					
					System.out.println("here");
					
					Stock TEST_STOCK_1 = new Stock("Apple Inc", "AAPL", 2, (int)start_time, (int)curr_time);
					Stock TEST_STOCK_2 = new Stock("Tesla Inc", "TSLA", 2, (int)start_time, (int)curr_time);
					Stock TEST_STOCK_3 = new Stock("IBM Inc", "IBM", 2, (int)start_time, (int)curr_time);
					
					Portfolio test_portfolio = new Portfolio();
					test_portfolio.addStock(TEST_STOCK_1);
					test_portfolio.addStock(TEST_STOCK_2);
					test_portfolio.addStock(TEST_STOCK_3);
					
					GraphingModule GM = new GraphingModule();
					
					Map<Date, Double> portfolio_info_test = GM.getPortfolioValue(test_portfolio, ResolutionGetter.Month(), start_time, curr_time); //hard coded dates and resolution rn, need to change

					GraphJSONhelper GH = new GraphJSONhelper();
					
					String portfolio_json = GH.Total_portfolio_Info(portfolio_info_test);
					
					ArrayList<String> view_stocks_test = new ArrayList<String>();
					
					view_stocks_test.add(portfolio_json);
					
					String Labels_test = "";
					boolean firstTime = true; //need to set labels only once
					
					for(Stock s: test_portfolio.getPortfolio())
					{
						
						GraphJSONhelper G = new GraphJSONhelper();
						Data_and_Labels DnL = G.StockGraphInfo(s.getTicker(), ResolutionGetter.Month(), start_time, curr_time); //hard coded dates rn.
						view_stocks_test.add(DnL.Data_Json);
						
						if(firstTime)
						{
							Labels_test = DnL.Labels;
							firstTime = false;
						}
						
					}
					
					String GraphDataTest = new JSONArray(view_stocks_test).toString();
					
					User Test_User = new User(uname,result);
					
					Test_User.setPortfolio(test_portfolio);
					
					HomePageModule Test_user_module = new HomePageModule(Test_User);
					
					double test_change_percent = Test_user_module.getChangePercentDouble();
					
					test_change_percent = test_change_percent * 100;
//					
//					Collection<Double> Test_todaysValuetemp =  GMM.getPortfolioValue(test_portfolio, ResolutionGetter.Day(), curr_time-1,curr_time).values();
//					
//					ArrayList<Double> Test_temp_placeholder = new ArrayList<>(Test_todaysValuetemp);
//					
//					double Test_Todays_value = Test_temp_placeholder.get(0);
//
					session.setAttribute("TodaysVal", Test_user_module.todayTotalDouble);
					
					System.out.println("Graph data has: " + GraphDataTest);
					System.out.println("Graph labsls has: " + Labels_test);
					
					session.setAttribute("ChangePercent", test_change_percent);
					session.setAttribute("GraphData", GraphDataTest);
					session.setAttribute("GraphLabels", Labels_test);
					
				}
				else {
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
