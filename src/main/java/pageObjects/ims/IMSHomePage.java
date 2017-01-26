package pageObjects.ims;

import org.openqa.selenium.By;
import pageObjects.planA.AbstractPage;
import springConstructors.IMSData;
import utils.Driver;

public class IMSHomePage extends AbstractPage {

    private static final By WELCOME_CONTAINER = By.id("welcomeDescContainer");
    private static final By INPUT_SMARTSEARCH = By.id("smartSearchField");
    private static final By BUTTON_SEARCH = By.id("search-submit");
    private static final By USERNAME_LINK = By.cssSelector("td.username a");

    public boolean isOpened() {
        return Driver.isElementVisible(WELCOME_CONTAINER);
    }

    public void findPlayer(String username) {
        Driver.inputTextToField(INPUT_SMARTSEARCH, username);
        Driver.click(BUTTON_SEARCH);
        Driver.click(USERNAME_LINK);
    }

}
