package com.framework.hooks;

import com.framework.driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void setUp() {
        // Initialize driver and navigate to URL before each scenario
        DriverManager.initDriver();
        DriverManager.navigateToUrl();
    }

    @After
    public void tearDown() {
        // Quit driver after each scenario
        DriverManager.quitDriver();
    }
}
