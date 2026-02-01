package com.framework.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

// Cucumber Runner Configuration
@CucumberOptions(
        features = "src/test/resources/features",   // path to feature files
        glue = {
                "com.framework.steps",               // step definitions
                "com.framework.hooks"                // hooks
        },
        plugin = {
                "pretty",
                "html:target/cucumber-report.html",
                "json:target/cucumber-report.json"
        },
        monochrome = true,
        tags = "@smoke"                              // optional: run only scenarios with @smoke tag
)
public class TestRunner extends AbstractTestNGCucumberTests {

    // Enables parallel execution of scenarios if needed
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
