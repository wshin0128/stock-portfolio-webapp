package csci310.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import csci310.model.Portfolio;

/**
 *
 * @author sqlitetutorial.net
 */
public class DatabaseClient {
	
	private Connection connection;
	
	public DatabaseClient() throws SQLException {
		// db parameters
		String url = "jdbc:sqlite:database.db";
		// create a connection to the database
		connection = DriverManager.getConnection(url);

		System.out.println("Connection to SQLite has been established.");
	}
	
	public void setConnection(Connection c) {
		connection = c;
	}
	
	public boolean createTable() {
		String createTable = "CREATE TABLE IF NOT EXISTS User ("
        		+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
        		+ "username TEXT NOT NULL,"
        		+ "password TEXT NOT NULL"
        		+ ");"; 
        Statement createTableStatement;
        try {
        	createTableStatement = connection.createStatement();
        	createTableStatement.executeUpdate(createTable); 
        	return true;
        } catch(SQLException e) {
        	e.printStackTrace();
        	return false;
        }
	}
	
	public boolean createUser(String username, String password) {
		try {
			boolean uniqueUsername = true;
			String query = "SELECT COUNT(*) FROM User WHERE username=?";
			PreparedStatement checkUsername = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			checkUsername.setString(1, username);
			ResultSet rs = checkUsername.executeQuery();
			while (rs.next()) {
				// if a user already exists with this username, this username is not unique
				uniqueUsername = (rs.getInt(1) == 0);
			}
			if (uniqueUsername) {
				String createUserQuery = "INSERT INTO User(username, password)"
										 + "VALUES(?,?);";
				PreparedStatement createUser = connection.prepareStatement(createUserQuery);
				createUser.setString(1, username);
				createUser.setString(2, password);
				createUser.executeUpdate();
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// Assuming that date objects will be stored as unix time 
	// Reference: https://stackoverflow.com/questions/2881321/how-to-insert-date-in-sqlite-through-java
	// Reference: https://stackoverflow.com/questions/3371326/java-date-from-unix-timestamp
	// Reference: https://stackoverflow.com/questions/17432735/convert-unix-time-stamp-to-date-in-java
	public boolean addStockToPortfolio(Integer userID, String tickerSymbol, int quantity, Integer dateBought, Integer dateSold) {
		return true;
	}
	
	public Portfolio getPortfolio(Integer userID) {
		return null; 
	}
	
	public boolean addStockToViewed(Integer userID, String tickerSymbol, int quantity, Integer dateBought, Integer dateSold) {
		return true;
	}
	
	public Portfolio getViewedStocks(Integer userID) {
		return null; 
	}
	
	public int getUser(String username, String password) {
		try {
			boolean usernameExists = false;
			boolean correctPassword = false;
			String findUsernameQuery = "SELECT COUNT(*) FROM User WHERE username=?";
			PreparedStatement findUsername = connection.prepareStatement(findUsernameQuery, Statement.RETURN_GENERATED_KEYS);
			findUsername.setString(1, username);
			ResultSet rs = findUsername.executeQuery();
			
			while(rs.next()) {
				// Validates that there exists a single user with this corresponding username
				usernameExists = (rs.getInt(1) == 1);
			} 
			
			if(usernameExists) {
				String passwordCheckQuery = "SELECT COUNT(*) FROM User WHERE username=? AND password=?";
				PreparedStatement passwordCheck = connection.prepareStatement(passwordCheckQuery);
				passwordCheck.setString(1, username);
				passwordCheck.setString(2, password);
				rs = passwordCheck.executeQuery();
				while(rs.next()) {
					correctPassword = (rs.getInt(1) == 1);
				}
				if(correctPassword) {
					// Password is valid (return 1)
					return 1;
				} else {
					// Password is invalid (return 2)
					return 2;
				}
			} else {
				// Username was not found (return 0)
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// SQLException (return -1)
			return -1;
		}
	}
	
	public boolean clearDatabase() {
		try {
			String clearCommand = "DELETE FROM 'User'";
			Statement clearDatabase = connection.createStatement();
			System.out.println("output: " + clearDatabase.executeUpdate(clearCommand));
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
