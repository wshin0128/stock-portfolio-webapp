Feature: Sign Up Page
	Scenario: Sign in with username already taken
		Given I am on the sign up page
		When I enter an invalid username "username" that already exists
		And I enter a password
		Then I should see the error message "Username already taken!"
	
	Scenario: Successful register
		Given I am on the sign up page
		When I enter a valid username "user" that does not exist
		And I enter a valid password "pass"
		Then I should land on the homepage