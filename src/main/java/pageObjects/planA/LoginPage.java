package pageObjects.planA;

import enums.Platform;
import org.openqa.selenium.By;
import springConstructors.UserData;
import utils.DataProvider;
import utils.Driver;

public class LoginPage extends AbstractMyAccountPage {

    private static final By INPUT_USERNAME = By.cssSelector("input.fn-user-name");
    private static final By INPUT_PASSWORD = By.name("password");
    private static final By EYE_ICON = By.cssSelector(".fn-toggle-password-visibility");
    private static final String EYE_ICON_CSS = ".fn-toggle-password-visibility";
    private static final By LABEL_REMEMBER_ME = By.cssSelector("label[for=rememberme]");
    private static final By CHECKBOX_REMEMBER_ME = By.id("rememberme");
    private static final By BUTTON_LOGIN = By.cssSelector(".fn-login-btn");
    private static final By LINK_FORGOTTEN_PASSWORD = By.xpath("//a[@href='/forgotten-password']");
    private static final By LINK_REGISTER = By.xpath("//a[@href='/register-step-1']");

    private static final By VALIDATION_USERNAME = By.cssSelector(".field_name_username");
    private static final By VALIDATION_PASSWORD = By.cssSelector(".field_name_password");

    private static final By BUTTON_ACCEPT_TC = By.cssSelector("span.fn-accept");

    public void login(UserData userData) {
        open();
        enterUsername(userData.getUsername());
        enterPassword(userData.getPassword());
        clickLogin();
        clickAcceptTC();
        isDashboardPageOpened();
    }

    public void enterUsername(String username) {
        Driver.inputTextToField(INPUT_USERNAME, username);
    }

    public void clearUsername() {
        Driver.clearField(INPUT_USERNAME);
    }

    public String getUsernameInputText() {
        return Driver.getAttribute(INPUT_USERNAME, "value");
    }

    public void enterPassword(String password) {
        Driver.inputTextToField(INPUT_PASSWORD, password);
    }

    public boolean isRememberMeChecked() {
        return Driver.isCheckboxChecked(CHECKBOX_REMEMBER_ME);
    }

    public void clickRememberMe() {
        Driver.click(LABEL_REMEMBER_ME);
    }

    public void clickLogin() {
        Driver.click(BUTTON_LOGIN);
    }

    public void clickForgottenPassword() {
        Driver.click(LINK_FORGOTTEN_PASSWORD);
    }

    public void clickRegister() {
        Driver.click(LINK_REGISTER);
    }

    public void clickAcceptTC() { Driver.click(BUTTON_ACCEPT_TC);}

    public String getUsernamePlaceholder() {
        return Driver.getAttribute(INPUT_USERNAME, "placeholder");
    }

    public String getPasswordPlaceholder() {
        return Driver.getAttribute(INPUT_PASSWORD, "placeholder");
    }

    public void clickEyeIcon() {
        if (DataProvider.getCurrentPlatform().equals(Platform.tablet)) {
            Driver.tap(EYE_ICON_CSS);
        } else {
            Driver.click(EYE_ICON);
        }
    }

    public boolean isUsernameInvalid() {
        return isFieldInvalid(VALIDATION_USERNAME);
    }

    public boolean isPasswordInvalid() {
        return isFieldInvalid(VALIDATION_PASSWORD);
    }

    public boolean isPasswordVisible() {

        String passwordVisibilityType = Driver.getAttribute(INPUT_PASSWORD, "type");

        switch (passwordVisibilityType) {

            case "password":
                return false;
            case "text":
                return true;
            default:
                return false;
        }
    }

    public boolean isForgottenPasswordPageOpened(){
        return Driver.getURLSuffix().equals("forgotten-password");
    }

    public boolean isRegisterPageOpened() {
        return Driver.isNewTabOpened("register-step-1");
    }

}
