package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/Features",
    glue = {"StepDefinitions", "Utilities"},
    plugin = {
        "rerun:target/rerun.txt"
    },
    monochrome = true,
    dryRun = false,
    tags = "@AddEmployeeScenario"
)
public class TestNGTestRunner extends AbstractTestNGCucumberTests { }
