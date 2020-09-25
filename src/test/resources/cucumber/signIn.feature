Feature: Sign in Page
  Scenario: Sign in with username that does not exist
    Given I am on the sign in page
    When I enter an invalid username "username"
    And I enter any password
    Then I should see the error "No user with this username exists"
    
  Scenario: Sign in with valid username but invalid password
    Given I am on the sign in page
    When I enter an valid username "username1"
    And I enter an incorrect password "password"
    Then I should see the error "The password you entered is invalid"
    
  Scenario: Successful Sign in
    Given I am on the sign in page
    When I enter an valid username "username1"
    And I enter the correct password "password1"
    Then I should be taken to the home page