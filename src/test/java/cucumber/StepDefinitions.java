package cucumber;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertTrue;

/**
 * Step definitions for Cucumber tests.
 */
public class StepDefinitions {
	private static final String ROOT_URL = "http://localhost:8080/";

	private final WebDriver driver = new ChromeDriver();

	@Given("I am on the sign in page")
	public void i_am_on_the_sign_in_page() {
	    driver.get(ROOT_URL+"signIn.jsp");
	}

	@When("I enter an valid username {string}")
	public void i_enter_an_valid_username(String string) throws InterruptedException {
		Thread.sleep(2000);
	}
	@When("I enter the correct password {string}")
	public void i_enter_the_correct_password(String string) {
	    
	}
	@Then("I should be taken to the home page")
	public void i_should_be_taken_to_the_home_page() {

	}
	
	@When("I enter an incorrect password {string}")
	public void i_enter_an_incorrect_password(String string) {
	    
	}
	@Then("I should see the error {string}")
	public void i_should_see_the_error(String string) {
	}
	
	@When("I enter an invalid username {string}")
	public void i_enter_an_invalid_username(String string) {

	}
	@When("I enter any password")
	public void i_enter_any_password() {

	}
	
	@Given("I am on the home page")
	public void i_am_on_the_home_page() throws InterruptedException {
	    driver.get(ROOT_URL+"homepage.jsp");
	    Thread.sleep(1000);
	}
	
	@When("I click on the sign out button")
	public void i_click_on_the_sign_out_button() throws InterruptedException {
	    Thread.sleep(1000);
	}
	@Then("I should be signed out and taken back to the sign in page")
	public void i_should_be_signed_out_and_taken_back_to_the_sign_in_page() {

	}
  
  /*************************************************************************/
  
  @Given("I am on the sign up page")
	public void i_am_on_the_sign_up_page() {
		driver.get(ROOT_URL+"signup.jsp");
	}
	
	@When("I enter an invalid username {string} that already exists")
	public void i_enter_an_invalid_username_that_already_exists(String string) {
	    
	}
	@When("I enter a password")
	public void i_enter_a_password() {
	    
	}
	@Then("I should see the error message {string}")
	public void i_should_see_the_error(String string) {
	    
	}
  
	/*************************************************************************/

	@When("I enter a valid username {string} that does not exist")
	public void i_enter_a_valid_username_that_does_not_exist(String string) {
	   
	}
	@When("I enter a valid password {string}")
	public void i_enter_a_valid_password(String string) {
	    
	}
	@Then("I should land on the homepage")
	public void i_should_land_on_the_homepage() {
	    
	}

	@After()
	public void after() {
		driver.quit();
	}
}
