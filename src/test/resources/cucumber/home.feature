Feature: Home Page
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