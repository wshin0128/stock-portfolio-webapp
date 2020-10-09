Feature: Adding and Removing stocks from homepage
  Scenario: Showing add stock popup for Portfolio
    Given I am on the home page
    When I click Add Stock in the Portfolio box
    Then I should see a popup dialogue to add a stock
    
  Scenario: Showing view stock popup for Viewed Stocks
    Given I am on the home page
    When I click View Stock in the Viewed Stocks box
    Then I should see a popup dialogue to view a stock
  
  Scenario: Removing a stock from portfolio
    Given I am on the home page
    When I click the trash icon on a stock row in the Portfolio box
    Then the stock should be removed from the Portfolio box
  
  Scenario: Removing a stock from viewed stocks
    Given I am on the home page
    When I click the trash icon on a stock row in the Viewed Stocks box
    Then the stock should be removed from the Viewed Stocks box
    
  Scenario: Adding stocks to Portfolio using CSV file
    Given I am on the home page
    When I click Import CSV
    Then I should see a popup dialogue to import a csv file