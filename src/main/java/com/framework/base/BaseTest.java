package com.framework.base;

import com.framework.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class BaseTest {

    protected static WebDriver driver;
    protected static final Logger logger =
            LogManager.getLogger(BaseTest.class);

    /**
     * Initializes WebDriver based on browser from config.properties
     */
    public void initializeDriver() {

        String browser = ConfigReader.getProperty("browser");
        logger.info("Browser from config: {}", browser);

        if (browser == null) {
            throw new RuntimeException("Browser is not specified in config.properties");
        }

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            default:
                throw new RuntimeException("Unsupported browser: " + browser);
        }

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(
                        Integer.parseInt(
                                ConfigReader.getProperty("implicit.wait")
                        )
                )
        );

        logger.info("Browser initialized successfully");
    }

    /**
     * Navigates to application URL
     */
    public void navigateToApplication() {
        String url = ConfigReader.getProperty("base.url");

        if (url == null) {
            throw new RuntimeException("base.url is not defined in config.properties");
        }

        logger.info("Navigating to URL: {}", url);
        driver.get(url);
    }

    /**
     * Quits WebDriver safely
     */
    public void quitDriver() {
        if (driver != null) {
            logger.info("Closing browser");
            driver.quit();
            driver = null;
        }
    }

    /**
     * Returns active WebDriver instance
     */
    public WebDriver getDriver() {
        return driver;
    }
}
