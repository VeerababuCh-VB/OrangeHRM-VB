package StepDefinitions;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import Utilities.ExtentReportManager;
import Utilities.PropertiesReader;
import Utilities.ScreenshotUtil;
import Utilities.WaitUtil;

import io.cucumber.java.en.*;
import io.cucumber.java.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageFactory.Login_PF;

public class OrangeHRMSteps_PF {

	private static WebDriver driver;
	private Login_PF login_Test;
	private WaitUtil waitUtil;
	
	@Before
	public void beforeScenario(Scenario scenario) {
		String rawUri = scenario.getUri().toString().replace("\\", "/");
		String[] parts = rawUri.split("/");
		String featureFile = parts[parts.length - 1];
		String featureName = featureFile.replace(".feature", "");
		String testName = featureName + " - " + scenario.getName();
		ExtentReportManager.logInfo("Starting scenario: " + testName);
	}

	@Given("Login Orange HRM Page")
	public void login_orange_hrm_page() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		waitUtil = new WaitUtil(driver, 10);
		login_Test = new Login_PF(driver);

		String url = PropertiesReader.get("baseUrl");
		driver.get(url);
		driver.navigate().refresh();

		waitUtil.waitForPageLoad();
		boolean titleLoaded = waitUtil.waitForTitle("OrangeHRM");

		if (titleLoaded) {
			waitUtil.waitForStableElement(login_Test.getBrandingElement());
			ScreenshotUtil.captureAndLogPass(driver, "Page title validated after refresh", "Title_Validation");

			String expectedURL = PropertiesReader.get("Current_URL");
			String currentURL = driver.getCurrentUrl();
			String currentTitle = driver.getTitle();

			if (currentURL.equals(expectedURL) && currentTitle.equals("OrangeHRM")) {
				ExtentReportManager.logPass("Current Page URL: " + currentURL);
				ExtentReportManager.logPass("Current Page Title: " + currentTitle);
			} else {
				ExtentReportManager.logFail("Current Page URL not matched with expected URL");
				ExtentReportManager.logFail("Current Page Title not matched with expected Title");
			}
		} else {
			ScreenshotUtil.captureAndLogFail(driver, "Unexpected page title after refresh", "Title_Validation_Failure");
		}
	}

	@When("User Enter HRM {string} and HRM {string}")
	public void user_enter_hrm_user_name_and_hrm_password(String Username, String Password) {

		waitUtil.waitForStableElement(login_Test.getUsernameField());
		login_Test.enterUserName(Username);

		waitUtil.waitForStableElement(login_Test.getPasswordField());
		login_Test.enterPassword(Password);
		ScreenshotUtil.captureAndLog(driver, "Entered username and password", "Entered_Credentials");
	}

	@And("User Clicks on Login button")
	public void user_clicks_on_login_button() {
		waitUtil.waitForStableElement(login_Test.getLoginButton());
		login_Test.clickLoginButton();

		waitUtil.waitForPageLoad();
		waitUtil.waitForStableElement(login_Test.getPimButton());
		ScreenshotUtil.captureAndLog(driver, "Clicked login button", "Clicked_Login");
	}
	@Then("User is navigate to the PIM page")
	public void user_is_navigate_to_the_pim_page() {
		try {
			// Navigate to PIM
			waitUtil.waitForStableElement(login_Test.getPimButton());
			login_Test.clickPim();

			waitUtil.waitForPageLoad();
			waitUtil.waitForStableElement(login_Test.getPimHeader());
			ScreenshotUtil.captureAndLogPass(driver, "Navigated to PIM page", "Pim_Page");

			// Click Add Employee
			if (login_Test.addbtn.isDisplayed() && login_Test.addbtn.isEnabled()) {
				login_Test.addbtn.click();
				waitUtil.waitForStableElement(login_Test.getaddHeader());
				ScreenshotUtil.captureAndLogPass(driver, "Navigated to Add Employee Page", "Add_Employee_Page");
			} else {
				ExtentReportManager.logFail("Add button is not visible or not enabled.");
			}
		} catch (Exception e) {
			ScreenshotUtil.captureAndLogFail(driver, "Exception during PIM navigation: " + e.getMessage(), "PIM_Navigation_Error");
		}
	}

	@Then("Add Employye details in PIM page with {string} and {string}")
	public void add_employee_details_in_pim_page(String FirstName, String LastName) throws InterruptedException {
	    login_Test.savebtn.click();

	    WebElement actualfnerrorElement = waitUtil.waitForErrorElementVisible(login_Test.firstNameError);
	    WebElement actuallnerrorElement = waitUtil.waitForErrorElementVisible(login_Test.lastNameError);
	    String actualfnerror = actualfnerrorElement.getText();
	    String actuallnerror = actuallnerrorElement.getText();

	    validateErrorMessage(actualfnerror, "Required");
	    validateErrorMessage(actuallnerror, "Required");
	    ScreenshotUtil.captureAndLog(driver, "Mandatory Fields Error Captured", "Captured_Error");

	    if (login_Test.firstNameInput.isEnabled() && login_Test.lastNameInput.isEnabled()) {
	        login_Test.firstNameInput.clear();
	        login_Test.firstNameInput.sendKeys(FirstName);
	        login_Test.lastNameInput.clear();
	        login_Test.lastNameInput.sendKeys(LastName);

	        Random random = new Random();
	        int number = 10000 + random.nextInt(90000);
	        String result = "VB" + number;
	        if (login_Test.idinput.isDisplayed() && login_Test.idinput.isEnabled()) {
	        	waitUtil.clearAndType(login_Test.idinput, result);
	            ExtentReportManager.logPass("Entered all details successfully");
	            ScreenshotUtil.captureAndLogPass(driver, "All mandatory details entered", "Entered_All_Details");
	            waitUtil.waitForElementVisible(login_Test.savebtn);
	            login_Test.savebtn.click();
	            Thread.sleep(5000);
	            ScreenshotUtil.captureAndLogPass(driver, "Successfully Clicked Save Button", "Save Button Clicked");
	        } else {
	            ExtentReportManager.logFail("Not able to enter all details");
	            ScreenshotUtil.captureAndLogFail(driver, "Unable to enter mandatory details", "Not_Entered_All_Details");
	        }
	    }
	}

	@And("User should be logout")
	public void user_should_be_logout() {
		try {
			waitUtil.waitForStableElement(login_Test.getLogoutIcon());
			login_Test.getLogoutIcon().click();
			ScreenshotUtil.captureAndLog(driver, "Clicked logout button", "Clicked_Logout");
			waitUtil.waitForStableElement(login_Test.getLogoutButton());
			login_Test.clickLogoutButton();

			// Wait for Logo to confirm logout redirect
			waitUtil.waitForElementVisible(login_Test.getCompanyLogo());

			if (login_Test.getCompanyLogo().isDisplayed()) {
				ScreenshotUtil.captureAndLogPass(driver, "Logout successful, company logo visible", "Logout_Success");
				ExtentReportManager.logInfo("User successfully logged out and redirected.");
			} else {
				ScreenshotUtil.captureAndLogFail(driver, "Company logo not visible after logout", "Logout_Logo_Missing");
			}
		} catch (Exception e) {
			ScreenshotUtil.captureAndLogFail(driver, "Logout exception: " + e.getMessage(), "Logout_Exception");
			ExtentReportManager.logFail("Logout failed due to exception: " + e.getMessage());
		}
	}
	@After
	public void afterScenario(Scenario scenario) {
		if (scenario.isFailed()) {
			ScreenshotUtil.captureAndLogFail(driver, "Scenario failed: " + scenario.getName(), "Scenario_Failure");
			ExtentReportManager.logFail("Scenario failed: " + scenario.getName());
		}
		if (driver != null) {
			driver.quit();
		}
	}
	public void validateErrorMessage(String actualErrorMsg, String expectedErrorMsg) {
		if (actualErrorMsg.equals(expectedErrorMsg)) {
			ExtentReportManager.logPass("Validation Passed: " + actualErrorMsg);
		} else {
			ExtentReportManager.logFail("Validation Failed: Expected [" + expectedErrorMsg + "] but got [" + actualErrorMsg + "]");
		}
	}

}