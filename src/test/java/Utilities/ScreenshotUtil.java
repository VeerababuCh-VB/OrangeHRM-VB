package Utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

	public static String captureScreenshot(WebDriver driver, String screenshotName) {
	    String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	    String folder = "test-results/TestReport/screenshots/";
	    String filename = screenshotName + "_" + timestamp + ".png";
	    String fullPath = folder + filename;

	    File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    try {
	        Files.createDirectories(Paths.get(folder));
	        Files.copy(src.toPath(), Paths.get(fullPath));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Return relative path from report to screenshot
	    return "screenshots/" + filename;
	
    }

    public static String generateCaption(String stepDescription) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return stepDescription + " | Captured at: " + timestamp;
    }

    public static void captureAndLog(WebDriver driver, String stepDescription, String screenshotName) {
        String path = captureScreenshot(driver, screenshotName);
        String caption = generateCaption(stepDescription);
        ExtentReportManager.logScreenshot(caption, path);
    }

    public static void captureAndLogPass(WebDriver driver, String stepDescription, String screenshotName) {
        String path = captureScreenshot(driver, screenshotName);
        String caption = generateCaption(stepDescription);
        ExtentReportManager.logPassWithScreenshot(caption, path);
    }

    public static void captureAndLogFail(WebDriver driver, String stepDescription, String screenshotName) {
        String path = captureScreenshot(driver, screenshotName);
        String caption = generateCaption(stepDescription);
        ExtentReportManager.logFailWithScreenshot(caption, path);
    }
}