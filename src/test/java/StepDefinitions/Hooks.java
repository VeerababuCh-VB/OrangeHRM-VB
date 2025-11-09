package StepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import Utilities.ExtentReportManager;

public class Hooks {

    @Before
    public void beforeScenario(Scenario scenario) {
        String testName = scenario.getName();

        //Prevent duplicate test creation
        if (ExtentReportManager.getTest() == null) {
            ExtentReportManager.createTest(testName);
        }

        ExtentReportManager.logInfo("Scenario Started: " + testName);
    }

    @After
    public void afterScenario(Scenario scenario) {
        String name = scenario.getName();

        if (scenario.isFailed()) {
            ExtentReportManager.logFail("Scenario Failed: " + name);
        } else {
            ExtentReportManager.logPass("Scenario Passed: " + name);
        }

        //Remove thread instance (mandatory for parallel!)
        ExtentReportManager.removeTest();
    }
}
