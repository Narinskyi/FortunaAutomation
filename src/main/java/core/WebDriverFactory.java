package core;

import enums.ConfiguredBrowsers;
import org.openqa.selenium.WebDriver;
import utils.DataProvider;

public class WebDriverFactory {

    //private constructor
    private WebDriverFactory(){}

    private static WebDriverFactory instance = new WebDriverFactory();

    public static WebDriverFactory getInstance(){
        return instance;
    }

    //which browser is used now
    private static ConfiguredBrowsers browser = DataProvider.getBrowser();

    // thread local driver object for webdriver
    private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>()
    {
        @Override
        protected WebDriver initialValue()
        {
            return browser.getDriver();
        }
    };

    public WebDriver getDriver(){
        return driver.get();
    }

    void quitDriver(){
        driver.get().quit();
        driver.remove();
    }

}
