Feature: The webapp resizes responsively
  Scenario: Navbar still visible on homepage
    Given I am on the home page
    When I resize to mobile dimensions
    Then the navbar should still be visible
    
  Scenario: Sign out button still visible on homepage
    Given I am on the home page
    When I resize to mobile dimensions
    Then the signout button should still be visible
 
  Scenario: Graph container still visible on homepage
    Given I am on the home page
    When I resize to mobile dimensions
    Then the graph container should still be visible

  Scenario: Portfolio list still visible on homepage
    Given I am on the home page
    When I resize to mobile dimensions
    Then the portfolio list should still be visible
    
  Scenario: Viewed stocks list still visible on homepage
    Given I am on the home page
    When I resize to mobile dimensions
    Then the viewed stocks list should still be visible