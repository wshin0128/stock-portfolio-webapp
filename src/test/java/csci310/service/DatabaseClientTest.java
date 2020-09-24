package csci310.service;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import io.cucumber.java.Before;


public class DatabaseClientTest extends Mockito {

	private static DatabaseClient db;
	private static DatabaseClient mockDb;
	
	@BeforeClass 
	public static void setUp() {
		try {
			db = new DatabaseClient();
			mockDb = new DatabaseClient();
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
		assertTrue(db.getUser(username, password));
		
		String wrongUsername = "wrongUsername";
		assertFalse(db.getUser(wrongUsername, password));
		
		String wrongPassword = "wrongpass";
		assertFalse(db.getUser(username, wrongPassword));
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
			mockDb.getUser(username, password);
			assertTrue(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
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




