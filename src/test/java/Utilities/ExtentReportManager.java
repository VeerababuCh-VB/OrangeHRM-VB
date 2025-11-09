package Utilities;

import java.io.File;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.MediaEntityBuilder;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    // Initialize Extent Report
    public static void initReports() {
        try {
            String reportPath = "test-results/TestReport/AutomationReport.html";
            File reportDir = new File("test-results/TestReport");
            if (!reportDir.exists()) {
                reportDir.mkdirs();
            }

            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("User", System.getProperty("VEERABAU CHITTIMENU"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Create a new test entry
    public static void createTest(String testName) {
        System.out.println("Creating test: " + testName); // Debug line
        test.set(extent.createTest(testName));
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void logInfo(String message) {
        if (getTest() != null) getTest().info(message);
    }

    public static void logPass(String message) {
        if (getTest() != null) getTest().pass(message);
    }

    public static void logFail(String message) {
        if (getTest() != null) getTest().fail(message);
    }

    public static void logScreenshot(String message, String screenshotPath) {
        if (getTest() != null) {
            getTest().info(message, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }
    }

    public static void logPassWithScreenshot(String message, String screenshotPath) {
        if (getTest() != null) {
            getTest().pass(message, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }
    }

    public static void logFailWithScreenshot(String message, String screenshotPath) {
        if (getTest() != null) {
            getTest().fail(message, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }
    }

    // Flush report
    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }

    // Remove test instance
    public static void removeTest() {
        test.remove();
    }
}