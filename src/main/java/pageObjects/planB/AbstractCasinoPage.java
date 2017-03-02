package pageObjects.planB;

import org.openqa.selenium.By;
import pageObjects.planA.AbstractPage;
import pageObjects.planB.iFrames.LoginModal;
import utils.Driver;

public abstract class AbstractCasinoPage extends AbstractPage {

    private static final By BUTTON_SUBMIT = By.cssSelector("button[type='submit']");
    private static final By BUTTON_LOGIN = By.cssSelector(".fn-login");
    public static final By BALANCE = By.cssSelector(".fn-main-header-user-balance:not(.hidden)");
    private static final By USERNAME = By.cssSelector(".user-info-replacer");
    private static final By LOGO = By.cssSelector(".main-header__logo");
    private static final By LOGOUT = By.cssSelector(".btn_type_logout.fn-logout");
    private static final By BUTTON_DEPOSIT = By.cssSelector(".btn.btn_action_deposit");

    public void waitForUsername(){
        Driver.waitForElementVisibility(USERNAME);
    }

    public void clickLogin() {
        Driver.click(BUTTON_LOGIN);
    }

    public void clickBalance() {
        Driver.click(BALANCE);
    }

    public void clickDeposit(){
        Driver.click(BUTTON_DEPOSIT);
    }

    public boolean isBalanceVisible(){
        return Driver.isElementVisible(BALANCE, 0);
    }

    public boolean isBalanceDisappeared(){
       return Driver.isElementDisappeared(BALANCE, 3);
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
    @Override
    public void open(){
        super.open();
        if (Driver.isElementVisible(LOGOUT, 2)){
            logout();
        }
    }
}
