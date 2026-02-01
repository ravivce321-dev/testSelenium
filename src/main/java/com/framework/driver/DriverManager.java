package com.framework.driver;

import com.framework.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class DriverManager {

    private static final Logger logger =
            LogManager.getLogger(DriverManager.class);

    private static final ThreadLocal<WebDriver> driver =
            new ThreadLocal<>();

    private DriverManager() {
        // prevent instantiation
    }

    public static void initDriver() {

        String browser = ConfigReader.getProperty("browser");
        logger.info("Initializing browser: " + browser);

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver.set(new ChromeDriver());
        } else {
            throw new RuntimeException("Unsupported browser: " + browser);
        }

        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(
                Duration.ofSeconds(
                        Integer.parseInt(
                                ConfigReader.getProperty("implicit.wait")
                        )
                )
        );
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void navigateToUrl() {
        String url = ConfigReader.getProperty("base.url");
        logger.info("Navigating to URL: " + url);
        getDriver().get(url);
    }

    public static void quitDriver() {
        if (getDriver() != null) {
            logger.info("Quitting browser");
            getDriver().quit();
            driver.remove();
        }
    }
}
