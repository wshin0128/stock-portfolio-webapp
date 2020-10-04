package csci310.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {

	@Test
	public void testUser() {
		User testUser = new User("jdoe","password");
		assertTrue("Usernames do not match", testUser.getUsername() == "jdoe");
		assertTrue("Passwords do not match", testUser.getPassword() == "password");
	}

}
