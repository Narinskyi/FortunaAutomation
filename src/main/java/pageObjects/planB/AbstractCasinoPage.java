package pageObjects.planB;

import org.openqa.selenium.By;
import pageObjects.planA.AbstractPage;
import pageObjects.planB.iFrames.LoginModal;
import utils.Driver;

public abstract class AbstractCasinoPage extends AbstractPage {

    private int someInt;
    private static final By BUTTON_SUBMIT = By.cssSelector("button[type='submit']");
    private static final By BUTTON_LOGIN = By.cssSelector(".fn-login");
    public static final By BALANCE = By.cssSelector(".fn-main-header-user-balance");
    private static final By USERNAME = By.cssSelector(".user-info-replacer");
    private static final By LOGO = By.cssSelector(".main-header__logo");
    private static final By LOGOUT = By.cssSelector(".btn_type_logout.fn-logout");

    public void waitForUsername(){
        Driver.waitForElementVisibility(USERNAME);
    }

    public void clickLogin() {
        Driver.click(BUTTON_LOGIN);
    }

    public void clickBalance() {
        Driver.click(BALANCE);
    }

    public boolean isBalanceVisible(){
        return Driver.isElementVisible(BALANCE, 0);
    }

    public boolean isBalanceDisappeared(){
        Driver.waitForElementToDisappear(BALANCE, 3);
        return !isBalanceVisible();
    }

    public void clickLogo(){
        Driver.click(LOGO);
    }

    public void login (String username, String password){
        clickLogin();
        LoginModal loginModal = LoginModal.get();
        loginModal.login(username, password);
        waitForUsername();
    }

    public void logout(){
        Driver.click(LOGOUT);
        LogoutPopup logoutPopup = LogoutPopup.get();
        logoutPopup.clickAcceptButton();
        closeAllPopups();
    }

    public void closeAllPopups(){
        while(Driver.isElementVisible(AbstractCasinoPopup.ROOT, 2)){
            AbstractCasinoPopup.close();
        }
    }

    public AbstractCasinoPopup test (){
        LogoutPopup test = (LogoutPopup) new AbstractCasinoPopup();
        test.get();
        int someInt = this.someInt;
        return null;
    }
}
