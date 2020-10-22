package cucumber;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertTrue;

/**
 * Step definitions for Cucumber tests.
 */
public class StepDefinitions {
	private static final String ROOT_URL = "http://localhost:8080/";

	private final WebDriver driver = new ChromeDriver();

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
		Thread.sleep(3000);
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
	    Thread.sleep(1000);
	}
	
	@Given("I am on the home page")
	public void i_am_on_the_home_page() throws InterruptedException {
	    driver.get(ROOT_URL+"homepage.jsp");
	    Thread.sleep(1000);
	}
	
	@When("I click on the sign out button")
	public void i_click_on_the_sign_out_button() throws InterruptedException {
	    Thread.sleep(1000);
	    driver.findElement(By.xpath("/html/body/div[1]/div/a")).click();
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
	    Thread.sleep(1000);
	}

	@Then("I should be taken to sign up")
	public void i_should_be_taken_to_sign_up() throws InterruptedException {
		Thread.sleep(3000);
		assertTrue(driver.getCurrentUrl().equalsIgnoreCase(ROOT_URL+"signup.jsp"));
		Thread.sleep(3000);
	}
	
/*************************************************************************/
	
	@Given("I am on the sign up page")
	public void i_am_on_the_sign_up_page() {
		driver.get(ROOT_URL+"signup.jsp");
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
		WebElement searchButton = driver.findElement(By.className("sign-in-button"));
	    searchButton.click();
	    Thread.sleep(1000);
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



  
	/*************************************************************************/


	
	@When("I click Add Stock in the Portfolio box")
	public void i_click_Add_Stock_in_the_Portfolio_box() throws InterruptedException {
		WebElement addStockButton = driver.findElement(By.xpath("//*[@id=\"add-stock-button\"]"));
		addStockButton.click();
		Thread.sleep(1000);
	}

	@Then("I should see a popup dialogue to add a stock")
	public void i_should_see_a_popup_dialogue_to_add_a_stock() throws InterruptedException {
		Thread.sleep(3000);
	    WebElement addStockPopup = driver.findElement(By.xpath("//*[@id='add-stock-modal']"));
	    assertTrue(addStockPopup.getCssValue("display") != "none");
	}

	@When("I click View Stock in the Viewed Stocks box")
	public void i_click_View_Stock_in_the_Viewed_Stocks_box() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#viewed-container .add-stocks-container > .button")).click();
		Thread.sleep(1000);
	}

	@Then("I should see a popup dialogue to view a stock")
	public void i_should_see_a_popup_dialogue_to_view_a_stock() throws InterruptedException {
		Thread.sleep(3000);
		WebElement viewStockPopup = driver.findElement(By.xpath("//*[@id=\"view-stock-modal\"]"));
	    assertTrue(viewStockPopup.getCssValue("display") != "none");
	}

	@When("I click the trash icon on a stock row in the Portfolio box")
	public void i_click_the_trash_icon_on_a_stock_row_in_the_Portfolio_box() throws InterruptedException {
		Thread.sleep(1000);
		WebElement deleteStockButton = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/table/tbody/tr[1]/td[4]/a"));
		deleteStockButton.click();
		Thread.sleep(1000);
	}

	@Then("the stock should be removed from the Portfolio box")
	public void the_stock_should_be_removed_from_the_Portfolio_box() throws InterruptedException {
		Thread.sleep(1000);
		WebElement stockRow = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/table/tbody/tr[1]"));
		assertTrue(stockRow == null);
	}

	@When("I click the trash icon on a stock row in the Viewed Stocks box")
	public void i_click_the_trash_icon_on_a_stock_row_in_the_Viewed_Stocks_box() throws InterruptedException {
		Thread.sleep(1000);
		WebElement deleteStockButton = driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/table/tbody/tr[1]/td[4]/a"));
		deleteStockButton.click();
		Thread.sleep(1000);
	}

	@Then("the stock should be removed from the Viewed Stocks box")
	public void the_stock_should_be_removed_from_the_Viewed_Stocks_box() throws InterruptedException {
		Thread.sleep(1000);
		WebElement stockRow = driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/table/tbody/tr[1]"));
		assertTrue(stockRow == null);
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
	
	@When("I click Import CSV")
	public void i_click_Import_CSV() throws InterruptedException {
		WebElement addStockButton = driver.findElement(By.xpath("//*[@id=\"import-stock-button\"]"));
		addStockButton.click();
		Thread.sleep(1000);
	}
	
	@Then("I should see a popup dialogue to import a csv file")
	public void i_should_see_a_popup_dialogue_to_import_a_csv_file() throws InterruptedException {
	    Thread.sleep(3000);
	    WebElement viewImportPopup = driver.findElement(By.xpath("//*[@id=\"import-stock-modal\"]"));
	    assertTrue(viewImportPopup.getCssValue("display") != "none");
	}

	@After()
	public void after() {
		driver.quit();
	}
}
