package pageObjects.ims;

import org.openqa.selenium.By;
import pageObjects.planA.AbstractPage;
import springConstructors.IMSData;
import utils.Driver;

public class IMSLoginPage extends AbstractPage {

    private static final By INPUT_USERNAME = By.id("username");
    private static final By INPUT_PASSWORD = By.id("password");
    private static final By BUTTON_LOGIN = By.id("loginButton");

    public void loginToIMS(IMSData imsData) {
        Driver.inputTextToField(INPUT_USERNAME, imsData.getImsAdminLogin());
        Driver.inputTextToField(INPUT_PASSWORD, imsData.getImsAdminPass());
        Driver.click(BUTTON_LOGIN);
    }
}
