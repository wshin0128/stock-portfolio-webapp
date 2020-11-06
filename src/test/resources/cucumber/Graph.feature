Feature: Graph
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
    
   Scenario: Zoom in button  
    Given I am on the home page
    When I click the zoom in button of the home page.
    Then the graph should zoom in on the home page.
      
   Scenario: Zoom out button  
    Given I am on the home page
    When I click the zoom out button of the home page.
    Then the graph should zoom out on the home page.
    
   Scenario: Custom range working  
    Given I am on the home page
    When I click the custom range button.
    And I enter proper start,end dates n submit
    Then the graph should re-adjust on the home page.
    
   Scenario: Custom range incorrect input  
    Given I am on the home page
    When I click the custom range button.
    And I enter incorrect start,end dates n submit
    Then I should see an error message.
    
    
    

    
    