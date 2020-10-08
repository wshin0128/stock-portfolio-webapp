package csci310.model;

import lombok.Getter;
import lombok.Setter;

@Getter
// @Setter
public class User {
	private String username;
	private String password;
	private Portfolio portfolio;
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
}
