package pageObjects.planB;

import org.openqa.selenium.By;
import utils.Driver;

/**
 * Created by tysa on 27.01.2017.
 */
public class AbstractCasinoPopup {

    public static final By ROOT = By.cssSelector(".popup");
    public static final By ACCEPT_BUTTON = By.cssSelector(".popup-modal__button_type_accept");
    public static final By DECLINE_BUTTON = By.cssSelector(".popup-modal__button_type_decline");
    public static final By CLOSE_BUTTON = By.cssSelector(".fn-close");

    public void clickAcceptButton(){
        Driver.click(ACCEPT_BUTTON);
    }

    public void clickDeclineButton(){
        Driver.click(DECLINE_BUTTON);
    }

    public static void close(){
        Driver.click(CLOSE_BUTTON);
    }

}
