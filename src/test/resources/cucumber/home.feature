Feature: Home Page
  Scenario: Looking at the home page
    Given I am on the home page
    When I click on the sign out button
    Then I should be signed out and taken back to the sign in page
    
