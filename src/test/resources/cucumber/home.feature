Feature: Home Page
  Scenario: Looking at the home page
    Given I am on the home page
    When I click on the sign out button
    Then I should be signed out and taken back to the sign in page
    
  Scenario: Timing out user
    Given I am on the home page
    When 120 seconds of inactivity occurs
    Then I should see an alert that I am being logged out

  Scenario: Adjust date to view past performance  
    Given I am on the home page
    When I click the 1 week button of the home page.
    Then the graph should re-adjust on the home page.
 
   Scenario: Adjust date to view past performance 2  
    Given I am on the home page
    When I click the 1 month button of the home page.
    Then the graph should re-adjust on the home page.

   Scenario: Adjust date to view past performance 3  
    Given I am on the home page
    When I click the 1 year button of the home page.
    Then the graph should re-adjust on the home page.
    
   Scenario: Adjust date to view past performance 4  
    Given I am on the home page
    When I click the 1 day button of the home page.
    Then the graph should re-adjust on the home page.