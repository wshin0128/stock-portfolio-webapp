package cucumber;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step definitions for Cucumber tests.
 */
public class StepDefinitions {
	private static final String ROOT_URL = "https://localhost:8443/";

	private WebDriver driver;
	
	@Before()
	public void before() {
		ChromeOptions capability = new ChromeOptions();
		capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capability.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
		driver = new ChromeDriver(capability);
	}

	@Given("I am on the index page")
	public void i_am_on_the_index_page() {
		driver.get(ROOT_URL);
	}

	@When("I click the link {string}")
	public void i_click_the_link(String linkText) throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.linkText(linkText)).click();
	}

	@Then("I should see header {string}")
	public void i_should_see_header(String header) throws InterruptedException {
		Thread.sleep(1000);
		assertTrue(driver.findElement(By.cssSelector("h2")).getText().contains(header));
	}
	
	@Then("I should see text {string}")
	public void i_should_see_text(String text) throws InterruptedException {
		Thread.sleep(1000);
		assertTrue(driver.getPageSource().contains(text));
	}
	
	@Given("I am on the sign in page")
	public void i_am_on_the_sign_in_page() {
	    driver.get(ROOT_URL+"signIn.jsp");
	}

	@When("I enter an valid username {string}")
	public void i_enter_an_valid_username(String string) throws InterruptedException {
		WebElement queryBox = driver.findElement(By.id("username"));
		queryBox.sendKeys(string);
	}
	
	@When("I enter the correct password {string}")
	public void i_enter_the_correct_password(String string) {
		WebElement queryBox = driver.findElement(By.id("pass"));
		queryBox.sendKeys(string);
	}
	
	@Then("I should be taken to the home page")
	public void i_should_be_taken_to_the_home_page() throws InterruptedException {
		Thread.sleep(7000);
		assertTrue(driver.getCurrentUrl().equalsIgnoreCase(ROOT_URL+"homepage.jsp"));
		Thread.sleep(3000);
	}
	
	@When("I enter an incorrect password {string}")
	public void i_enter_an_incorrect_password(String string) {
		WebElement queryBox = driver.findElement(By.id("pass"));
		queryBox.sendKeys(string);
	}
	@Then("I should see the error {string}")
	public void i_should_see_the_error(String string) throws InterruptedException {
		Thread.sleep(1000);
		WebElement errorBox = driver.findElement(By.id("Merror"));
		assertTrue(string.equalsIgnoreCase(errorBox.getText()));
	}
	
	@When("I enter an invalid username {string}")
	public void i_enter_an_invalid_username(String string) {
		WebElement queryBox = driver.findElement(By.id("username"));
		queryBox.sendKeys(string);
	}
	@When("I enter any password")
	public void i_enter_any_password() {
		WebElement queryBox = driver.findElement(By.id("pass"));
		queryBox.sendKeys("test2test");
	}
	
	@When("I click submit on login")
	public void i_click_submit_on_login() throws InterruptedException {
		WebElement searchButton = driver.findElement(By.id("b"));
	    searchButton.click();
	    Thread.sleep(7000);
	}
	
	@Given("I am on the home page")
	public void i_am_on_the_home_page() throws InterruptedException {
	    driver.get(ROOT_URL+"signIn.jsp");
	    WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("test2");
		WebElement password = driver.findElement(By.id("pass"));
		password.sendKeys("test2test");
		WebElement searchButton = driver.findElement(By.id("b"));
	    searchButton.click();
	    Thread.sleep(8000);
	}
	
	@When("I click on the sign out button")
	public void i_click_on_the_sign_out_button() throws InterruptedException {
	    Thread.sleep(1000);
	    driver.findElement(By.id("signOutB")).click();
	    Thread.sleep(1000);
	}
	@Then("I should be signed out and taken back to the sign in page")
	public void i_should_be_signed_out_and_taken_back_to_the_sign_in_page() {
		assertTrue(driver.getCurrentUrl().equalsIgnoreCase("http://localhost:8080/signIn.jsp"));
	}
	
	@When("I redirect to sign in")
	public void i_redirect_to_sign_in() {
		driver.get(ROOT_URL+"signIn.jsp");
	}

	@When("I redirect to sign up")
	public void i_redirect_to_sign_up() {
		driver.get(ROOT_URL+"signup.jsp");
	}

	@When("I click on the sign up link")
	public void i_click_on_the_sign_up_link() throws InterruptedException {
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"signUpLink\"]/a"));
	    searchButton.click();
	    Thread.sleep(5000);
	}

	@Then("I should be taken to sign up")
	public void i_should_be_taken_to_sign_up() throws InterruptedException {
		Thread.sleep(3000);
		assertTrue(driver.getCurrentUrl().equalsIgnoreCase(ROOT_URL+"signup.jsp"));
		Thread.sleep(3000);
	}
	
	@When("I wait {int} minute")
	public void i_wait_minute(Integer int1) throws InterruptedException {
	    Thread.sleep(60000 * int1 + 1);
	}

	@Then("I should see the unlock message {string}")
	public void i_should_see_the_unlock_message(String string) throws InterruptedException {
		Thread.sleep(1000);
		WebElement errorBox = driver.findElement(By.id("Munlock"));
		assertTrue(string.equalsIgnoreCase(errorBox.getText()));
	}
	
/**
 * @throws InterruptedException ***********************************************************************/
	
	@Given("I am on the sign up page")
	public void i_am_on_the_sign_up_page() throws InterruptedException {
		driver.get(ROOT_URL+"signup.jsp");
		Thread.sleep(3000);
	}
	
	@When("I enter an already used username in SU")
	public void i_enter_an_already_used_username_in_SU() {
		WebElement queryBox = driver.findElement(By.id("username"));
		queryBox.sendKeys("test2");
	}

	@When("I enter a password in SU")
	public void i_enter_a_password_in_SU() {
		WebElement queryBox = driver.findElement(By.id("password"));
		queryBox.sendKeys("test2test");
	}

	@When("I enter a confirm password in SU")
	public void i_enter_a_confirm_password_in_SU() {
		WebElement queryBox = driver.findElement(By.id("confirmpassword"));
		queryBox.sendKeys("test2test");
	}

	@When("I click submit in SU")
	public void i_click_submit_in_SU() throws InterruptedException {
		WebElement searchButton = driver.findElement(By.id("b"));
	    searchButton.click();
	    Thread.sleep(7000);
	}

	@Then("I should see the SU error {string}")
	public void i_should_see_the_SU_error(String string) throws InterruptedException {
		Thread.sleep(1000);
		WebElement errorBox = driver.findElement(By.id("Merror"));
		assertTrue(string.equalsIgnoreCase(errorBox.getText()));
	}

	@When("I enter a valid username that does not exist in SU")
	public void i_enter_a_valid_username_that_does_not_exist_in_SU() {
		int random = (int)(Math.random() * 10001) + 10000;
		WebElement queryBox = driver.findElement(By.id("username"));
		queryBox.sendKeys(""+ random);
	}


	@Then("I should land on the sign in page")
	public void i_should_land_on_the_sign_in_page() throws InterruptedException {
		Thread.sleep(3000);
		assertTrue(driver.getCurrentUrl().equalsIgnoreCase(ROOT_URL+"signIn.jsp"));
		Thread.sleep(3000);
	}

	@When("I enter a valid username in SU")
	public void i_enter_a_valid_username_in_SU() {
		WebElement queryBox = driver.findElement(By.id("username"));
		queryBox.sendKeys("test");
	}


	@When("I enter a non-matching password in confirm password in SU")
	public void i_enter_a_non_matching_password_in_confirm_password_in_SU() {
		WebElement queryBox = driver.findElement(By.id("confirmpassword"));
		queryBox.sendKeys("test2test222");
	}
	
	@When("I click the cancel button in SU")
	public void i_click_the_cancel_button_in_SU() throws InterruptedException {
		WebElement searchButton = driver.findElement(By.id("c"));
	    searchButton.click();
	    Thread.sleep(7000);
	}



  
	/**
	 * @throws InterruptedException ***********************************************************************/


	@When("I click Add Stock in the Portfolio box")
	public void i_click_Add_Stock_in_the_Portfolio_box() throws InterruptedException {
		WebElement addStockButton = driver.findElement(By.id("add-stock-button"));
		addStockButton.click();
		Thread.sleep(1000);
	}

	@When("I enter a valid Ticker symbol in the Add Stocks popup")
	public void i_enter_a_valid_Ticker_symbol_in_the_Add_Stocks_popup() {
		WebElement addStockTicker = driver.findElement(By.cssSelector("#add-stock-form #ticker"));
	    addStockTicker.sendKeys("AMZN");
	}

	@When("I enter a valid number of shares in the Add Stocks popup")
	public void i_enter_a_valid_number_of_shares_in_the_Add_Stocks_popup() {
		WebElement addStockShares = driver.findElement(By.cssSelector("#add-stock-form #shares"));
		addStockShares.sendKeys("2");
	}

	@When("I enter a valid purchase date in the Add Stocks popup")
	public void i_enter_a_valid_purchase_date_in_the_Add_Stocks_popup() {
		WebElement addStockBuyDate = driver.findElement(By.cssSelector("#add-stock-modal #date-purchased"));
		addStockBuyDate.sendKeys("2020\t1010");
	}

	@When("I enter a valid sell date in the Add Stocks popup")
	public void i_enter_a_valid_sell_date_in_the_Add_Stocks_popup() {
		WebElement addStockSellDate = driver.findElement(By.cssSelector("#add-stock-modal #date-sold"));
		addStockSellDate.sendKeys("2020\t1015");
	}

	@When("I click Add Stock")
	public void i_click_Add_Stock() {
		WebElement addStockSubmit = driver.findElement(By.cssSelector("#add-stock-submit"));
		addStockSubmit.click();
	}

	@Then("the stock should be added to the Portfolio list")
	public void the_stock_should_be_added_to_the_Portfolio_list() throws InterruptedException {
		Thread.sleep(3000);
		WebElement checkStock = driver.findElement(By.xpath("//*[text()[contains(.,'Amazon.com Inc')]]"));
		assertTrue(checkStock != null);
	}

	@When("I enter an invalid Ticker symbol in the Add Stocks popup")
	public void i_enter_an_invalid_Ticker_symbol_in_the_Add_Stocks_popup() {
		WebElement addStockTicker = driver.findElement(By.cssSelector("#add-stock-form #ticker"));
	    addStockTicker.sendKeys("ADSFSWDZFD");
	}

	@When("I enter any number of shares in the Add Stocks popup")
	public void i_enter_any_number_of_shares_in_the_Add_Stocks_popup() {
		WebElement addStockShares = driver.findElement(By.cssSelector("#add-stock-modal #shares"));
		addStockShares.sendKeys("2");
	}

	@When("I enter any valid purchase date in the Add Stocks popup")
	public void i_enter_any_valid_purchase_date_in_the_Add_Stocks_popup() {
		WebElement addStockBuyDate = driver.findElement(By.cssSelector("#add-stock-modal #date-purchased"));
		addStockBuyDate.sendKeys("2020\t1010");
	}

	@When("I enter any valid sell date in the Add Stocks popup")
	public void i_enter_any_valid_sell_date_in_the_Add_Stocks_popup() {
		WebElement addStockSellDate = driver.findElement(By.cssSelector("#add-stock-modal #date-sold"));
		addStockSellDate.sendKeys("2020\t1015");
	}

	@Then("I should see the error {string} in the Add Stocks popup")
	public void i_should_see_the_error_in_the_Add_Stocks_popup(String string) throws InterruptedException {
		Thread.sleep(3000);
		WebElement errorMessage = driver.findElement(By.cssSelector("#add-stock-modal .error-msg"));
		assertTrue(string.equalsIgnoreCase(errorMessage.getText()));
	}

	@When("I enter an invalid number of shares in the Add Stocks popup")
	public void i_enter_an_invalid_number_of_shares_in_the_Add_Stocks_popup() {
		WebElement addStockShares = driver.findElement(By.cssSelector("#add-stock-form #shares"));
		addStockShares.sendKeys("-2");
	}

	@When("I enter a sell date before the purchase date in the Add Stocks popup")
	public void i_enter_a_sell_date_before_the_purchase_date_in_the_Add_Stocks_popup() {
		WebElement addStockSellDate = driver.findElement(By.cssSelector("#add-stock-modal #date-sold"));
		addStockSellDate.sendKeys("2020\t1009");
	}

	@When("I click Add Stock in the Viewed Stocks box")
	public void i_click_Add_Stock_in_the_Viewed_Stocks_box() throws InterruptedException {
		WebElement addStockButton = driver.findElement(By.id("view-stock-button"));
		addStockButton.click();
		Thread.sleep(1000);
	}

	@When("I enter a valid Ticker symbol in the View Stocks popup")
	public void i_enter_a_valid_Ticker_symbol_in_the_View_Stocks_popup() {
		WebElement addStockTicker = driver.findElement(By.cssSelector("#view-stock-modal #ticker"));
	    addStockTicker.sendKeys("AMZN");
	}

	@When("I enter a valid number of shares in the View Stocks popup")
	public void i_enter_a_valid_number_of_shares_in_the_View_Stocks_popup() {
		WebElement addStockShares = driver.findElement(By.cssSelector("#view-stock-modal #shares"));
		addStockShares.sendKeys("2");
	}

	@When("I enter a valid purchase date in the View Stocks popup")
	public void i_enter_a_valid_purchase_date_in_the_View_Stocks_popup() {
		WebElement addStockBuyDate = driver.findElement(By.cssSelector("#view-stock-modal #date-purchased"));
		addStockBuyDate.sendKeys("2020\t1010");
	}

	@When("I enter a valid sell date in the View Stocks popup")
	public void i_enter_a_valid_sell_date_in_the_View_Stocks_popup() {
		WebElement addStockSellDate = driver.findElement(By.cssSelector("#view-stock-modal #date-sold"));
		addStockSellDate.sendKeys("2020\t1015");
	}

	@When("I click View Stock")
	public void i_click_View_Stock() {
		WebElement viewStockSubmit = driver.findElement(By.id("view-stock-submit"));
		viewStockSubmit.click();
	}

	@Then("the stock should be added to the Viewed Stocks list")
	public void the_stock_should_be_added_to_the_Viewed_Stocks_list() throws InterruptedException {
		Thread.sleep(3000);
		WebElement checkStock = driver.findElement(By.xpath("//*[text()[contains(.,'Amazon.com Inc')]]"));
		assertTrue(checkStock != null);
	}

	@When("I enter an invalid Ticker symbol in the View Stocks popup")
	public void i_enter_an_invalid_Ticker_symbol_in_the_View_Stocks_popup() {
		WebElement addStockTicker = driver.findElement(By.cssSelector("#view-stock-modal #ticker"));
	    addStockTicker.sendKeys("ADSFSWDZFD");
	}

	@When("I enter any number of shares in the View Stocks popup")
	public void i_enter_any_number_of_shares_in_the_View_Stocks_popup() {
		WebElement addStockShares = driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/div/div/div/div[2]/form/div[2]/input"));
		addStockShares.sendKeys("2");
	}

	@When("I enter any valid purchase date in the View Stocks popup")
	public void i_enter_any_valid_purchase_date_in_the_View_Stocks_popup() {
		WebElement addStockBuyDate = driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/div/div/div/div[2]/form/div[3]/input"));
		addStockBuyDate.sendKeys("2020\t1010");
	}

	@When("I enter any valid sell date in the View Stocks popup")
	public void i_enter_any_valid_sell_date_in_the_View_Stocks_popup() {
		WebElement addStockBuyDate = driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/div/div/div/div[2]/form/div[4]/input"));
		addStockBuyDate.sendKeys("2020\t1015");
	}

	@Then("I should see the error {string} in the View Stocks popup")
	public void i_should_see_the_error_in_the_View_Stocks_popup(String string) throws InterruptedException {
		Thread.sleep(3000);
		WebElement errorMessage = driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/div/div/div/div[2]/form/div[5]/span"));
		assertTrue(string.equalsIgnoreCase(errorMessage.getText()));
	}

	@When("I enter an invalid number of shares in the View Stocks popup")
	public void i_enter_an_invalid_number_of_shares_in_the_View_Stocks_popup() {
		WebElement addStockShares = driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/div/div/div/div[2]/form/div[2]/input"));
		addStockShares.sendKeys("-2");
	}

	@When("I enter a sell date before the purchase date in the View Stocks popup")
	public void i_enter_a_sell_date_before_the_purchase_date_in_the_View_Stocks_popup() {
		WebElement addStockBuyDate = driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/div/div/div/div[2]/form/div[4]/input"));
		addStockBuyDate.sendKeys("2020\t1005");
	}

	@When("I click the trash icon on a stock row in the Portfolio list")
	public void i_click_the_trash_icon_on_a_stock_row_in_the_Portfolio_list() {
		WebElement trashButton = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/table/tbody/tr[1]/td[4]/a"));
	    trashButton.click();
	}

	@Then("the stock should be removed from the Portfolio list")
	public void the_stock_should_be_removed_from_the_Portfolio_list() {
	    // TODO
	}

	@When("I click the trash icon on a stock row in the Viewed Stocks list")
	public void i_click_the_trash_icon_on_a_stock_row_in_the_Viewed_Stocks_list() {
		WebElement trashButton = driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/table/tbody/tr[1]/td[4]/a"));
	    trashButton.click();
	}

	@Then("the stock should be removed from the Viewed Stocks list")
	public void the_stock_should_be_removed_from_the_Viewed_Stocks_list() {
		// TODO
	}

	@When("I click Import CSV")
	public void i_click_Import_CSV() {
		WebElement importButton = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div/button[2]"));
		importButton.click();
	}
	
	@Then("I should see an Upload File button")
	public void i_should_see_an_upload_file_button() throws InterruptedException {
		Thread.sleep(3000);
		WebElement uploadFileButton = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div/div[2]/div/div[2]/form/button"));
		assertTrue(uploadFileButton != null);
	}

	@When("I upload a valid CSV file")
	public void i_upload_a_valid_CSV_file() {
		WebElement uploadFile = driver.findElement(By.id("csvImport"));
		File file = new File("stockTests/stock1.csv");
		uploadFile.sendKeys(file.getAbsolutePath());
	}

	@When("I click Upload File")
	public void i_click_Upload_File() {
		WebElement uploadFileButton = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div/div[2]/div/div[2]/form/button"));
	    uploadFileButton.click();
	}

	@Then("the stocks in the CSV should be added to the Portfolio list")
	public void the_stocks_in_the_CSV_should_be_added_to_the_Portfolio_list() {
		WebElement checkStock1 = driver.findElement(By.xpath("//*[text()[contains(.,'MSFT')]]"));
		assertTrue(checkStock1 != null);
		WebElement checkStock2 = driver.findElement(By.xpath("//*[text()[contains(.,'TSLA')]]"));
		assertTrue(checkStock2 != null);
		WebElement checkStock3 = driver.findElement(By.xpath("//*[text()[contains(.,'AAPL')]]"));
		assertTrue(checkStock3 != null);
	}

	@When("I upload an invalid CSV file")
	public void i_upload_an_invalid_CSV_file() {
		WebElement uploadFile = driver.findElement(By.id("csvImport"));
		File file = new File("stockTests/stock2.csv");
		uploadFile.sendKeys(file.getAbsolutePath());
	}

	@Then("I should see an error")
	public void i_should_see_an_error() throws InterruptedException {
		Thread.sleep(3000);
	    WebElement errorMessage = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/span"));
	    String error = errorMessage.getText();
	    assertTrue(error.contains("Row 1: requires minimum of 5 parameters."));
	    assertTrue(error.contains("Row 2, Col A: stock name is a required field."));
	    assertTrue(error.contains("Row 2, Col B: stock ticker is a required field."));
	    assertTrue(error.contains("Row 2, Col C: stock quantity must be a Integer."));
	    assertTrue(error.contains("Row 2, Col D: stock buy date must be a Long."));
	    assertTrue(error.contains("Row 3, Col A: stock name is a required field."));
	    assertTrue(error.contains("Row 3, Col B: stock ticker is a required field."));
	    assertTrue(error.contains("Row 2, Col C: stock quantity must be a Integer."));
	    assertTrue(error.contains("Row 4, Col C: stock quantity must be a Integer."));
	    assertTrue(error.contains("Row 4, Col D: stock buy date must be a Long."));
	    assertTrue(error.contains("Row 4, Col E: stock sell date must be a Long."));
	    assertTrue(error.contains("Row 5, Col C: stock quantity must be a Integer."));
	    assertTrue(error.contains("Row 6, Col D: stock buy date must be a Long."));
	    assertTrue(error.contains("Row 6, Col E: stock sell date must be a Long."));
	}

	@When("I click the {int} week button of the home page.")
	public void i_click_the_week_button_of_the_home_page(Integer int1) {
	    WebElement button = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/form/div/input[2]"));
	    button.click();
	}

	@Then("the graph should re-adjust on the home page.")
	public void the_graph_should_re_adjust_on_the_home_page() {
	    // TODO
	}

	@When("I click the {int} month button of the home page.")
	public void i_click_the_month_button_of_the_home_page(Integer int1) {
		WebElement button = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/form/div/input[3]"));
	    button.click();
	}

	@When("I click the {int} year button of the home page.")
	public void i_click_the_year_button_of_the_home_page(Integer int1) {
		WebElement button = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/form/div/input[4]"));
	    button.click();
	}

	@When("I click the {int} day button of the home page.")
	public void i_click_the_day_button_of_the_home_page(Integer int1) {
		WebElement button = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/form/div/input[1]"));
	    button.click();
	}

	@When("{int} seconds of inactivity occurs")
	public void seconds_of_inactivity_occurs(Integer int1) throws InterruptedException {
		int ms = int1 * 1000;
		Thread.sleep(ms + 7000);
	}

	@Then("I should see an alert that I am being logged out")
	public void i_should_see_an_alert_that_I_am_being_logged_out() {
		boolean alert;
	    try {
	    	driver.switchTo().alert();
	    	alert = true;
	    } catch (NoAlertPresentException nape) {
	    	alert = false;
	    }
	    assertTrue(alert);
	}

	@Given("I am not logged in")
	public void i_am_not_logged_in() throws InterruptedException {
		driver.get(ROOT_URL+"signIn.jsp");
		Thread.sleep(3000);
	}

	@When("I attempt to access {string}")
	public void i_attempt_to_access(String string) {
		driver.get(string);
	}

	@Then("I should be redirected to the sign in page")
	public void i_should_be_redirected_to_the_sign_in_page() throws InterruptedException {
		Thread.sleep(3000);
		assertTrue(driver.getCurrentUrl().equalsIgnoreCase(ROOT_URL+"signIn.jsp"));
		Thread.sleep(3000);
	}
	
	
	
	
	
	
	
	

	@When("I resize to mobile dimensions")
	public void i_resize_to_mobile_dimensions() throws InterruptedException {
		Dimension mobile = new Dimension(480,800);
		driver.manage().window().setSize(mobile);
		Thread.sleep(3000);
	}
	@Then("the navbar should still be visible")
	public void the_navbar_should_still_be_visible() throws InterruptedException {
		assertTrue(driver.findElements( By.className("navbar") ).size() != 0);
	}
	@Then("the signout button should still be visible")
	public void the_signout_button_should_still_be_visible(){
		assertTrue(driver.findElements( By.cssSelector(".navbar .button") ).size() != 0);
	}
	@Then("the graph container should still be visible")
	public void the_graph_container_should_still_be_visible() {
		assertTrue(driver.findElements( By.id("graph-container") ).size() != 0);
	}
	@Then("the portfolio list should still be visible")
	public void the_portfolio_list_should_still_be_visible() {
		assertTrue(driver.findElements( By.id("portfolio-container") ).size() != 0);
	}
	@Then("the viewed stocks list should still be visible")
	public void the_viewed_stocks_list_should_still_be_visible() {
		assertTrue(driver.findElements( By.id("viewed-container") ).size() != 0);
	}
	
	@Then("the login form should still be visible in SI")
	public void the_login_form_should_still_be_visible_in_SI() {
		assertTrue(driver.findElements( By.id("logInForm") ).size() != 0);
	}

	@Then("the button should still be visible in SI")
	public void the_button_should_still_be_visible_in_SI() {
		assertTrue(driver.findElements( By.id("b") ).size() != 0);
	}

	@Then("the submit button should still be visible in SU")
	public void the_submit_button_should_still_be_visible_in_SU() {
		assertTrue(driver.findElements( By.id("b") ).size() != 0);
	}

	@Then("the cancel button should still be visible in SU")
	public void the_cancel_button_should_still_be_visible_in_SU() {
		assertTrue(driver.findElements( By.id("c") ).size() != 0);
	}

	@Then("the reg form should still be visible in SU")
	public void the_reg_form_should_still_be_visible_in_SU() {
		assertTrue(driver.findElements( By.id("register") ).size() != 0);
	}


	@After()
	public void after() {
		driver.quit();
	}
}
