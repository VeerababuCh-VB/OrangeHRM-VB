package Utilities;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class WaitUtil {

	private WebDriverWait wait;

	public WaitUtil(WebDriver driver, int timeoutSeconds) {
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
	}

	// Wait for visibility using locator
	public WebElement waitForVisibility(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	// Wait for clickability using locator
	public WebElement waitForClickability(By locator) {
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	// Wait for page title to match
	public boolean waitForTitle(String expectedTitle) {
		return wait.until(ExpectedConditions.titleIs(expectedTitle));
	}

	// Wait for page to fully load
	public void waitForPageLoad() {
		wait.until(driver -> ((JavascriptExecutor) driver)
				.executeScript("return document.readyState").equals("complete"));
	}

	// Wait for specific text to appear in element
	public boolean waitForTextInElement(By locator, String text) {
		return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
	}

	// Wait for a stable WebElement (already located via PageFactory)
	public void waitForStableElement(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	public boolean waitForElementClickable(WebElement element) {

		wait.until(ExpectedConditions.elementToBeClickable(element));
		return element.isEnabled();
	}

	public boolean waitForElementVisible(WebElement element) {

		wait.until(ExpectedConditions.visibilityOf(element));
		return element.isDisplayed();

	}

	public WebElement waitForErrorElementVisible(WebElement element) {
	    wait.until(ExpectedConditions.visibilityOf(element));
	    return element;
	}
}