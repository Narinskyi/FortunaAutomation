package enums;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.DataProvider;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public enum ConfiguredBrowsers {

    firefox,
    chrome,
    edge,
    ie,
    mobileEmulatorChrome,
    tabletEmulatorChrome,
    phantomJS;

    public WebDriver getDriver(){

        //if grid = true, use RemoteWebDriver
        boolean useGrid = DataProvider.shouldUseGrid();

        //get host of the grid hub
        URL host = DataProvider.getHubURL();

        //if grid is turned on - use RemoteWebDriver
        if (useGrid) {
            switch (this) {
                case firefox:
                    new RemoteWebDriver(host,DesiredCapabilities.firefox());
                case chrome:
                    return new RemoteWebDriver(host,DesiredCapabilities.chrome());
                case edge:
                    return new RemoteWebDriver(host,DesiredCapabilities.edge());
                case ie:
                    return new RemoteWebDriver(host,getIECapabilities());
                case mobileEmulatorChrome:
                    return new RemoteWebDriver(host,getChromeMobileCapabilities("Google Nexus 5"));
                case tabletEmulatorChrome:
                    return new RemoteWebDriver(host,getChromeMobileCapabilities("Apple iPad"));
                case phantomJS:
                    return new RemoteWebDriver(host, DesiredCapabilities.phantomjs());
                default:
                    throw new RuntimeException("Incorrect browser was specified in .properties file");
            }
        }

        //if grid is turned off - use local instance of WebDriver
        else {

            //local drivers initialization (for remote set in selenium server run config)
            initDrivers();

            switch (this) {
                case firefox:
                    return new FirefoxDriver();
                case chrome:
                    return new ChromeDriver();
                case edge:
                    return new EdgeDriver();
                case ie:
                    new InternetExplorerDriver(getIECapabilities());
                case mobileEmulatorChrome:
                    return new ChromeDriver(getChromeMobileCapabilities("Google Nexus 5"));
                case tabletEmulatorChrome:
                    return new ChromeDriver(getChromeMobileCapabilities("Apple iPad"));
                case phantomJS:
                    return new PhantomJSDriver();
                default:
                    throw new RuntimeException("Incorrect browser was specified in .properties file");
            }
        }
    }

    //set path values for configured drivers
    private static void initDrivers(){

        System.setProperty("webdriver.gecko.driver", "src/drivers/geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
        System.setProperty("webdriver.edge.driver", "src/drivers/MicrosoftWebDriver.exe");
        System.setProperty("webdriver.ie.driver", "src/drivers/IEDriverServer.exe");
        System.setProperty("phantomjs.binary.path", "src/drivers/phantomjs.exe");

    }

    private DesiredCapabilities getChromeMobileCapabilities(String deviceName){

        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", deviceName);

        Map<String, Object> chromeOptions = new HashMap<>();
        chromeOptions.put("mobileEmulation", mobileEmulation);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        return capabilities;
    }

    private DesiredCapabilities getIECapabilities(){
        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        capabilities.setCapability("nativeEvents", false);
        capabilities.setCapability("ignoreZoomSetting", true);
        capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);

        return capabilities;
    }

    public static void main(String[] args) {

        try {
            initDrivers();
            DesiredCapabilities capabilities = DesiredCapabilities.edge();

            WebDriver driver = new RemoteWebDriver(new URL("http://172.29.46.171:4444/wd/hub"), capabilities);
            driver.navigate().to("http://wpl-licensee25-public.ptdev.eu");
            driver.quit();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

}
