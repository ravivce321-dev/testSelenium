package com.framework.steps;

import com.framework.driver.DriverManager;
import com.framework.pages.LoginPage;
import io.cucumber.java.en.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class LoginSteps {

    private static final Logger logger = LogManager.getLogger(LoginSteps.class);

    private LoginPage loginPage;

    @Given("user is on login page")
    public void user_is_on_login_page() {
        logger.info("User is on login page");
        // Get the driver from DriverManager (Hooks already initialized it)
        loginPage = new LoginPage(DriverManager.getDriver());
    }

    @When("user logs in")
    public void user_logs_in() {
        logger.info("Entering login credentials");
        loginPage.enterUsername("admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLogin();
    }

    @Then("login successful")
    public void login_successful() {
        logger.info("Verifying login success");
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login validation failed");
    }
}
