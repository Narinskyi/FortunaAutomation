package core;

import enums.Platform;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import reporting.CustomTestListener;
import springConstructors.IMSData;
import springConstructors.UserData;
import utils.DataProvider;
import java.lang.reflect.Method;
import java.util.logging.Logger;

@Listeners({CustomTestListener.class})
@ContextConfiguration(locations={"/spring-config.xml"})
public abstract class AbstractTest extends AbstractTestNGSpringContextTests{

    private static Logger log = Logger.getAnonymousLogger();

    //used to distinguish tests
    protected static boolean isDesktop = DataProvider.getCurrentPlatform().equals(Platform.desktop);
    protected static boolean isTablet = DataProvider.getCurrentPlatform().equals(Platform.tablet);
    protected static boolean isMobile = DataProvider.getCurrentPlatform().equals(Platform.mobile);

    //cloned user data that's available from actual tests
    protected UserData userData;

    @Autowired
    @Qualifier("userData")
    private UserData springUserData;

    @Autowired
    @Qualifier("imsData")
    private IMSData imsData;

    @BeforeClass(alwaysRun = true)
    public void start(){
        //clone user data for each test class
        userData = getClonedUserData();

        //save its default state
        DataProvider.setUserData(userData);
        DataProvider.setIMSData(imsData);

        //start browser, specified in .properties file (redundant since also called in WebDriverUtils - left for clarity)
        WebDriverFactory.getInstance().getDriver();
        log.info("Browser started");
    }

    @AfterClass(alwaysRun = true)
    public void stop(){
        WebDriverFactory.getInstance().quitDriver();
        log.info("Browser stopped");
    }

    protected void restart() {
        this.stop();
        this.start();
    }

    public static void failTest(String message) {
        log.severe(message);
        Assert.fail(message);
    }

    //provide a cloned object for every test class
    public UserData getClonedUserData() {
        return springUserData.clone();
    }
}

