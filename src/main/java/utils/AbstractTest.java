package utils;

import architecture.WebDriverFactory;
import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.logging.Logger;

public class AbstractTest {

    protected static Logger log = Logger.getAnonymousLogger();

    @BeforeClass
    public static void start(){

        //start browser, specified in .properties file
        WebDriverFactory.startDriver(DataProvider.getBrowser());
        log.info("Browser started");
    }

    @AfterClass
    public static void stop(){
        WebDriverFactory.quitDriver();
        log.info("Browser stopped");
    }

    public static void failTest(String message) {

        stop();
        log.severe(message);
        Assert.fail(message);

    }

}

