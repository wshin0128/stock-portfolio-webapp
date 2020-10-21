Feature: Adding and Removing stocks from homepage
  Scenario: Successfully adding a stock to the portfolio list
    Given I am on the home page
    When I click Add Stock in the Portfolio box
    And I enter a valid Ticker symbol
    And I enter a valid number of shares
    And I enter a valid purchase date
    And I enter a valid sell date
    And I click "Add Stock"
    Then the stock should be added to the Portfolio list
    
  Scenario: Adding a stock with an invalid ticker symbol to the portfolio list
    Given I am on the home page
    When I click Add Stock in the Portfolio box
    And I enter an invalid Ticker symbol
    And I enter any number of shares
    And I enter any valid purchase date
    And I enter any valid sell date
    And I click "Add Stock"
    Then I should see the error "Your ticker is invalid"
    
  Scenario: Adding a stock with an invalid number of shares to the portfolio list
    Given I am on the home page
    When I click Add Stock in the Portfolio box
    And I enter a valid Ticker symbol
    And I enter an invalid number of shares
    And I enter any valid purchase date
    And I enter any valid sell date
    And I click "Add Stock"
    Then I should see the error "You must buy at least one share"
    
  Scenario: Adding a stock with an invalid date range to the portfolio list
    Given I am on the home page
    When I click Add Stock in the Portfolio box
    And I enter a valid Ticker symbol
    And I enter a valid number of shares
    And I enter any valid purchase date
    And I enter a sell date before the purchase date
    And I click "Add Stock"
    Then I should see the error "Your sell date must be after your buy date"
    
  Scenario: Successfully adding a stock to the viewed list
    Given I am on the home page
    When I click Add Stock in the Viewed Stocks box
    And I enter a valid Ticker symbol
    And I enter a valid number of shares
    And I enter a valid purchase date
    And I enter a valid sell date
    And I click "Add Stock"
    Then the stock should be added to the Viewed Stocks list
    
  Scenario: Adding a stock with an invalid ticker symbol to the viewed list
    Given I am on the home page
    When I click Add Stock in the Portfolio box
    And I enter an invalid Ticker symbol
    And I enter any number of shares
    And I enter any valid purchase date
    And I enter any valid sell date
    And I click "Add Stock"
    Then I should see the error "Your ticker is invalid"
    
  Scenario: Adding a stock with an invalid number of shares to the viewed list
    Given I am on the home page
    When I click Add Stock in the Portfolio box
    And I enter a valid Ticker symbol
    And I enter an invalid number of shares
    And I enter any valid purchase date
    And I enter any valid sell date
    And I click "Add Stock"
    Then I should see the error "You must buy at least one share"
    
  Scenario: Adding a stock with an invalid date range to the viewed list
    Given I am on the home page
    When I click Add Stock in the Portfolio box
    And I enter a valid Ticker symbol
    And I enter a valid number of shares
    And I enter any valid purchase date
    And I enter a sell date before the purchase date
    And I click "Add Stock"
    Then I should see the error "Your sell date must be after your buy date"
  
  Scenario: Removing a stock from the portfolio list
    Given I am on the home page
    When I click the trash icon on a stock row in the Portfolio list
    Then the stock should be removed from the Portfolio list
  
  Scenario: Removing a stock from the viewed list
    Given I am on the home page
    When I click the trash icon on a stock row in the Viewed Stocks list
    Then the stock should be removed from the Viewed Stocks list
    
  Scenario: Successfully adding stocks to Portfolio using CSV file
    Given I am on the home page
    When I click Import CSV
    And I upload a valid CSV file
    And I click "Upload File"
    Then the stocks in the CSV should be added to the Portfolio list
    
  Scenario: Adding stocks to Portfolio using an incorrectly formatted CSV file
    Given I am on the home page
    When I click Import CSV
    And I upload a CSV file
    And I click "Upload File"
    Then I should see an error