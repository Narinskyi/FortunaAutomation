package pageObjects;

import org.openqa.selenium.By;
import utils.Driver;

public abstract class AbstractFortunaPage extends AbstractPage {

    private static final By ERROR_MESSAGE = By.cssSelector(".message.error");
    private static final By SUCCESS_MESSAGE = By.cssSelector(".message.success");
    private static final By POPUP_MOBILE = By.cssSelector(".popup");
    private static final By BUTTON_CLOSE_POPUP_MOBILE = By.cssSelector(".fn-close");
    private static final By BUTTON_SUBMIT = By.cssSelector("button[type='submit']");

    public boolean areSuccessMessagesDisplayed(int howMany){
        return Driver.areSeveralElementsVisible(SUCCESS_MESSAGE, howMany);
    }

    public boolean areErrorMessagesDisplayed(int howMany){
        return Driver.areSeveralElementsVisible(ERROR_MESSAGE, howMany);
    }

    public boolean isFieldValid (By locator) {
        return Driver.getAttribute(locator, "class").contains("valid");
    }

    public boolean isFieldInvalid (By locator) {
        return Driver.getAttribute(locator, "class").contains("invalid");
    }

    public boolean isMobilePopupDisplayed(){
        return Driver.isElementVisible(POPUP_MOBILE);
    }

    public void closeMobilePopup(){
        Driver.click(BUTTON_CLOSE_POPUP_MOBILE);
    }

    public void clickSubmit(){
        Driver.click(BUTTON_SUBMIT);
    }



}
