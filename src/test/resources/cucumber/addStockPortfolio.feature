Feature: Adding stocks to the portfolio and viewed stocks lists
  Scenario: Successfully adding a stock to the portfolio list
    Given I am on the home page
    When I click Add Stock in the Portfolio box
    And I enter a valid Ticker symbol in the Add Stocks popup
    And I enter a valid number of shares in the Add Stocks popup
    And I enter a valid purchase date in the Add Stocks popup
    And I enter a valid sell date in the Add Stocks popup
    And I click Add Stock
    Then the stock should be added to the Portfolio list
    
  Scenario: Adding a stock with an invalid ticker symbol to the portfolio list
    Given I am on the home page
    When I click Add Stock in the Portfolio box
    And I enter an invalid Ticker symbol in the Add Stocks popup
    And I enter any number of shares in the Add Stocks popup
    And I enter any valid purchase date in the Add Stocks popup
    And I enter any valid sell date in the Add Stocks popup
    And I click Add Stock
    Then I should see the error "Illegal ticker symbol" in the Add Stocks popup
    
  Scenario: Adding a stock with zero shares to the portfolio list
    Given I am on the home page
    When I click Add Stock in the Portfolio box
    And I enter a valid Ticker symbol in the Add Stocks popup
    And I enter zero shares in the Add Stocks popup
    And I enter any valid purchase date in the Add Stocks popup
    And I enter any valid sell date in the Add Stocks popup
    And I click Add Stock
    Then I should see the error "Negative or zero values of quantity" in the Add Stocks popup
    
  Scenario: Adding a stock with negative shares to the portfolio list
    Given I am on the home page
    When I click Add Stock in the Portfolio box
    And I enter a valid Ticker symbol in the Add Stocks popup
    And I enter a negative number of shares in the Add Stocks popup
    And I enter any valid purchase date in the Add Stocks popup
    And I enter any valid sell date in the Add Stocks popup
    And I click Add Stock
    Then I should see the error "Negative or zero values of quantity" in the Add Stocks popup
    
   Scenario: Adding a stock without a purchase date to the portfolio list
    Given I am on the home page
    When I click Add Stock in the Portfolio box
    And I enter a valid Ticker symbol in the Add Stocks popup
    And I enter a valid number of shares in the Add Stocks popup
    And I do not enter a purchase date in the Add Stocks popup
    And I enter any valid sell date in the Add Stocks popup
    And I click Add Stock
    Then I should see the error "Purchase date is required" in the Add Stocks popup
    
  Scenario: Adding a stock with a sold date and no purchase date to the portfolio list
    Given I am on the home page
    When I click Add Stock in the Portfolio box
    And I enter a valid Ticker symbol in the Add Stocks popup
    And I enter a valid number of shares in the Add Stocks popup
    And I do not enter a purchase date in the Add Stocks popup
    And I enter any valid sell date in the Add Stocks popup
    And I click Add Stock
    Then I should see the error "Sold date with no purchase date" in the Add Stocks popup
    
  Scenario: Adding a stock with a sold date prior to purchase date to the portfolio list
    Given I am on the home page
    When I click Add Stock in the Portfolio box
    And I enter a valid Ticker symbol in the Add Stocks popup
    And I enter a valid number of shares in the Add Stocks popup
    And I enter any valid purchase date in the Add Stocks popup
    And I enter a sell date before the purchase date in the Add Stocks popup
    And I click Add Stock
    Then I should see the error "Sold date prior to purchase date" in the Add Stocks popup
    
  Scenario: Showing the Add Stock button
    Given I am on the home page
    When I click Add Stock in the Portfolio box
    Then I should see the Add Stock button in the Add Stocks popup
    
  Scenario: Showing the Add Stock button
    Given I am on the home page
    When I click Add Stock in the Portfolio box
    Then I should see the Cancel button in the Add Stocks popup