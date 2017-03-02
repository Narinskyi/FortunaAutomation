package enums;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
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
                    return new RemoteWebDriver(host,getFirefoxCapabilities());
                case chrome:
                    return new RemoteWebDriver(host,getChromeCapabilities());
                case edge:
                    return new RemoteWebDriver(host,DesiredCapabilities.edge());
                case ie:
                    return new RemoteWebDriver(host,getIECapabilities());
                case mobileEmulatorChrome:
                    return new RemoteWebDriver(host,getChromeMobileCapabilities("Google Nexus 5"));
                case tabletEmulatorChrome:
                    return new RemoteWebDriver(host,getChromeTabletCapabilities());
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
                    return new FirefoxDriver(getFirefoxCapabilities());
                case chrome:
                    return new ChromeDriver(getChromeCapabilities());
                case edge:
                    return new EdgeDriver();
                case ie:
                    return new InternetExplorerDriver(getIECapabilities());
                case mobileEmulatorChrome:
                    return new ChromeDriver(getChromeMobileCapabilities("Google Nexus 5"));
                case tabletEmulatorChrome:
                    return new ChromeDriver(getChromeTabletCapabilities());
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

    private DesiredCapabilities getChromeCapabilities() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--enable-bundled-ppapi-flash");
        options.addArguments("--disable-npapi");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        return capabilities;
    }

    private DesiredCapabilities getChromeTabletCapabilities(){

        Map<String, Object> mobileEmulation = new HashMap<>();

        String userAgent = "Mozilla/5.0 (iPad; U; CPU OS 3_2 like Mac OS X; en-us) AppleWebKit/531.21.10 " +
                "(KHTML, like Gecko) Version/4.0.4 Mobile/7B334b Safari/531.21.10";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-agent=" + userAgent);
        options.addArguments("window-size=1039,859");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        return capabilities;
    }

    private DesiredCapabilities getChromeMobileCapabilities(String deviceName){

        Map<String, Object> mobileEmulation = new HashMap<>();

        mobileEmulation.put("deviceName", deviceName);

        Map<String, Object> chromeOptions = new HashMap<>();
        chromeOptions.put("mobileEmulation", mobileEmulation);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        return capabilities;
    }

    private DesiredCapabilities getFirefoxCapabilities() {
        System.setProperty("webdriver.gecko.driver", "src/drivers/geckodriver.exe");
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
        FirefoxProfile profile = new FirefoxProfile();
        desiredCapabilities.setCapability(FirefoxDriver.PROFILE, profile);
        return desiredCapabilities;
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

           WebDriver driver = new RemoteWebDriver(new URL("http://172.29.46.171:4444/wd/hub"), ConfiguredBrowsers.chrome.getChromeTabletCapabilities());

            //WebDriver driver = new InternetExplorerDriver(ConfiguredBrowsers.ie.getIECapabilities());
            driver.navigate().to("http://wpl-licensee25-public.ptdev.eu");
            driver.wait(10000);
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
