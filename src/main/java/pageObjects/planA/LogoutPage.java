package pageObjects.planA;

import org.openqa.selenium.By;
import utils.Driver;

public class LogoutPage extends AbstractMyAccountPage {

    private static final By BUTTON_LOGOUT = By.cssSelector("button.fn-logout");
    private static final By BUTTON_ARE_YOU_SURE = By.cssSelector(".fn-accept");
    private static final By CLOSE_LAST_POPUP = By.cssSelector(".fn-close");

    //TODO - changed due to duplicate button
    public void logout(){
        Driver.click(By.xpath("(//button)[2]"));
        Driver.click(BUTTON_ARE_YOU_SURE);
        Driver.click(CLOSE_LAST_POPUP);
    }
}
