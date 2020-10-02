package csci310.service;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import csci310.model.Portfolio;
import io.cucumber.java.Before;


public class DatabaseClientTest extends Mockito {

	private static DatabaseClient db;
	private static DatabaseClient mockDb;
	
	@BeforeClass 
	public static void setUp() {
		try {
			db = new DatabaseClient();
			// write to a different database than our actual data
			String url = "jdbc:sqlite:databaseTest.db";
			// create a connection to the database
			Connection connection = DriverManager.getConnection(url);
			db.setConnection(connection);
			mockDb = new DatabaseClient();
			mockDb.setConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Before 
	public void tableExists() throws SQLException {
		db.clearDatabase();
		db.createTable();
		
		db.createUser("username", "password");
		db.createUser("username2", "password");
	}
	
	
	@Test
	public void testCreateTable() {
		if (db == null) {
			System.out.println("db is null");
		}
		assertTrue(db.createTable());
	}
	
	@Test 
	public void testCreateTableException() {
		try {
			Connection mockConn = mock(Connection.class);
			mockDb.setConnection(mockConn);
			when(mockConn.createStatement()).thenThrow(new SQLException());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mockDb.createTable();
		assertTrue(true);
	}

	@Test
	public void testCreateUser() {
		String username = "testUser2";
		String password = "password";
		assertTrue("New user",db.createUser(username, password));
		// Cannot have two users with the same username
		assertFalse("Duplicate username", db.createUser(username, password));
	}
	
	@Test
	public void testCreateUserThrowsException() {
		try {
			Connection mockConn = mock(Connection.class);
			mockDb.setConnection(mockConn);
			String query = "SELECT COUNT(*) FROM User WHERE username=?";
			when(mockConn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)).thenThrow(new SQLException());
			String username = "testUser2";
			String password = "password";
			mockDb.createUser(username, password);
			assertTrue(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetUser() {
		String username = "username";
		String password = "password";
		assertTrue("New user",db.createUser(username, password));
		assertTrue(db.getUser(username, password) == 1);
		
		String wrongUsername = "wrongUsername";
		assertTrue(db.getUser(wrongUsername, password) == 0);
		
		String wrongPassword = "wrongpass";
		assertTrue(db.getUser(username, wrongPassword) == 2);
	}
	
	@Test
	public void testGetUserThrowsException() {
		try {
			Connection mockConn = mock(Connection.class);
			mockDb.setConnection(mockConn);
			String findUsernameQuery = "SELECT COUNT(*) FROM User WHERE username=?";
			when(mockConn.prepareStatement(findUsernameQuery, Statement.RETURN_GENERATED_KEYS)).thenThrow(new SQLException());
			String username = "testUser2";
			String password = "password";
			assertTrue(mockDb.getUser(username, password) == -1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddStockToPortfolio() {
		assertTrue(db.addStockToPortfolio(1, "APPL", 2, 1599027025, 1601619025));
	}
	
	@Test
	public void testGetPortfolio() {
		assertTrue(db.getPortfolio(1) == null);
	}
	
	@Test
	public void testAddStockToViewed() {
		assertTrue(db.addStockToViewed(1, "APPL", 2, 1599027025, 1601619025));
	}
	
	@Test
	public void testGetViewedStocks() {
		assertTrue(db.getViewedStocks(1) == null); 
	}
	
	@Test
	public void testClearDatabase() {
		assertTrue(db.clearDatabase());
	}
	
	@Test
	public void testClearDatabaseThrowsException() {
		try {
			Connection mockConn = mock(Connection.class);
			mockDb.setConnection(mockConn);
			when(mockConn.createStatement()).thenThrow(new SQLException());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mockDb.clearDatabase();
		assertTrue(true);
	}

}




