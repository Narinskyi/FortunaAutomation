package pageObjects.planA;

import core.PageFactory;
import pageObjects.AvailablePages;
import org.openqa.selenium.By;
import springConstructors.UserData;
import utils.Driver;

public abstract class AbstractMyAccountPage extends AbstractPage {

    private static final By ERROR_MESSAGE = By.cssSelector(".message.error");
    private static final By ERROR_MESSAGE_BULLET = By.cssSelector(".message.error-tooltip");
    private static final By SUCCESS_MESSAGE = By.cssSelector(".message.success");
    private static final By POPUP_MOBILE = By.cssSelector(".popup");
    private static final By BUTTON_CLOSE_POPUP_MOBILE = By.cssSelector(".fn-close");
    private static final By BUTTON_SUBMIT = By.cssSelector("button[type='submit']");

    public boolean areSuccessMessagesDisplayed(int howMany){
        return Driver.areSeveralElementsVisible(SUCCESS_MESSAGE, howMany);
    }

    public boolean areErrorMessagesDisplayed(int howMany){
        return Driver.areSeveralElementsVisible(ERROR_MESSAGE_BULLET, howMany);
    }

    //in case when message has no bullets
    public boolean isErrorMessageDisplayed(){
        return Driver.isElementVisible(ERROR_MESSAGE);
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

    public void logout(){
        LogoutPage logoutPage = PageFactory.getPage(AvailablePages.logout);
        logoutPage.open();
        logoutPage.logout();
    }

    public void login(UserData userData) {
        LoginPage loginPage = PageFactory.getPage(AvailablePages.login);
        loginPage.open();
        loginPage.login(userData);
    }

    public void loginWithExisitingUser(UserData userData){
        LoginPage loginPage = PageFactory.getPage(AvailablePages.login);
        loginPage.open();
        loginPage.enterUsername(userData.getUsername());
        loginPage.enterPassword(userData.getPassword());
        clickSubmit();
        isDashboardPageOpened();
    }

    public boolean isDashboardPageOpened() {
        DashboardPage dashboardPage = PageFactory.getPage(AvailablePages.dashboard);
        return dashboardPage.isAccountInfoDisplayed();
    }

    public void quietLogout(){
        Driver.executeJavascript("require('auth').quietLogout();");
    }

}
