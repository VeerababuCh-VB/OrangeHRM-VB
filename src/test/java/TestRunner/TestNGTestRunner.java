package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(
    features = "src/test/resources/Features",  
    glue = {"StepDefinitions", "Utilities"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports/cucumber.html",
        "json:target/cucumber-reports/cucumber.json",
        "rerun:target/rerun.txt"
    },
    monochrome = true,
    dryRun = false,
    tags = "@AddEmployeeScenario"
)
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

    
}
