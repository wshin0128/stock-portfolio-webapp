package csci310.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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

import csci310.service.DatabaseClient;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			DatabaseClient db = new DatabaseClient();
			db.createUser("test1", "test2test");//Here atm for testing purposes as no way to create user
			int result = 0;
			String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			HttpSession session = request.getSession();
			JSONObject jo = new JSONObject(requestBody);
			String uname = jo.getString("username");
			String password =jo.getString("password");
			
			result = db.getUser(uname, password);
			System.out.println(result);
			
			if(result == 0) {
				session.setAttribute("login", false);
			}
			else if(result >= 1) {
				session.setAttribute("login", true);
				session.setAttribute("loginID", uname);
			}
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