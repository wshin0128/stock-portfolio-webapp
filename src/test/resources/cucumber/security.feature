Feature: Security
  Scenario: Signing out from home page
    Given I am on the home page
    When I click on the sign out button
    Then I should be signed out and taken back to the sign in page
    
  Scenario: Timing out user
    Given I am on the home page
    When 120 seconds of inactivity occurs
    Then I should see an alert that I am being logged out
    
  Scenario: Attempting to access homepage while not logged in
    Given I am not logged in
    When I attempt to access "localhost:8080/homepage.jsp"
    Then I should be redirected to the sign in page