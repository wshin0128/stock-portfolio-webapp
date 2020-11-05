Feature: Adding stocks to the portfolio and viewed stocks lists
  
  Scenario: Successfully adding a stock to the viewed list
    Given I am on the home page
    When I click Add Stock in the Viewed Stocks box
    And I enter a valid Ticker symbol in the View Stocks popup
    And I enter a valid number of shares in the View Stocks popup
    And I enter a valid purchase date in the View Stocks popup
    And I enter a valid sell date in the View Stocks popup
    And I click View Stock
    Then the stock should be added to the Viewed Stocks list
    
  Scenario: Adding a stock with an invalid ticker symbol to the viewed list
    Given I am on the home page
    When I click Add Stock in the Viewed Stocks box
    And I enter an invalid Ticker symbol in the View Stocks popup
    And I enter any number of shares in the View Stocks popup
    And I enter any valid purchase date in the View Stocks popup
    And I enter any valid sell date in the View Stocks popup
    And I click View Stock
    Then I should see the error "Your ticker is invalid" in the View Stocks popup
    
  Scenario: Adding a stock with an invalid number of shares to the viewed list
    Given I am on the home page
    When I click Add Stock in the Viewed Stocks box
    And I enter a valid Ticker symbol in the View Stocks popup
    And I enter an invalid number of shares in the View Stocks popup
    And I enter any valid purchase date in the View Stocks popup
    And I enter any valid sell date in the View Stocks popup
    And I click View Stock
    Then I should see the error "You must buy at least one share" in the View Stocks popup
    
  Scenario: Adding a stock with an invalid date range to the viewed list
    Given I am on the home page
    When I click Add Stock in the Viewed Stocks box
    And I enter a valid Ticker symbol in the View Stocks popup
    And I enter a valid number of shares in the View Stocks popup
    And I enter any valid purchase date in the View Stocks popup
    And I enter a sell date before the purchase date in the View Stocks popup
    And I click View Stock
    Then I should see the error "Your sell date must be after your buy date" in the View Stocks popup
    
  Scenario: Showing Upload CSV Popup
    Given I am on the home page
    When I click Import CSV
    Then I should see an Upload File button
    
  Scenario: Successfully adding stocks to Portfolio using CSV file
    Given I am on the home page
    When I click Import CSV
    And I upload a valid CSV file
    And I click Upload File
    Then the stocks in the CSV should be added to the Portfolio list
    
  Scenario: Adding stocks to Portfolio using an incorrectly formatted CSV file
    Given I am on the home page
    When I click Import CSV
    And I upload an invalid CSV file
    And I click Upload File
    Then I should see an error