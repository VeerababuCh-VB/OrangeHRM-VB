package Utilities;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.AfterAll;

public class ReportHooks {

    @BeforeAll
    public static void beforeAll() {
        ExtentReportManager.initReports();
    }

    @AfterAll
    public static void afterAll() {
        ExtentReportManager.flushReports();
    }
}
