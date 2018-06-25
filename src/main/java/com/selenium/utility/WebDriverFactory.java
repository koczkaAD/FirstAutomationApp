package com.selenium.utility;

import com.selenium.enums.WebDriverEnum;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by Dóra on 2018.06.25..
 */
public class WebDriverFactory {


    public static WebDriver createWebdriver(WebDriverEnum webDriverEnum) {
        WebDriver driver = null;

        switch (webDriverEnum) {
            case FIREFOX:
                driver = getFirefox();
                break;
            case HEADLESSCHROME:
                driver = getHeadlessChrome();
                break;
            case CHROME:
                driver = getChrome();
                break;
        }
        driver.manage().window().maximize();

        return driver;
    }

    private static WebDriver getFirefox() {
        System.setProperty("webdriver.gecko.driver", "C:\\APPS\\geckodriver\\geckodriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("firefox_binary", "C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        capabilities.setCapability("marionette", false);

        return new FirefoxDriver(capabilities);
    }

    private static WebDriver getChrome() {
        System.setProperty("webdriver.chrome.driver", "C:\\APPS\\chromedriver\\chromedriver.exe");

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--incognito");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        return new ChromeDriver(capabilities);
    }

    private static WebDriver getHeadlessChrome() {
        System.setProperty("webdriver.chrome.driver", "C:\\APPS\\chromedriver\\chromedriver.exe");

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--headless");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        return new ChromeDriver(capabilities);
    }


}
