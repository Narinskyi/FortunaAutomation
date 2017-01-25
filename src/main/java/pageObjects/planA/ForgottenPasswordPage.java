package pageObjects.planA;

import org.openqa.selenium.By;
import utils.DataProvider;
import utils.Driver;

public class ForgottenPasswordPage extends AbstractMyAccountPage {

    private static final By ROOT = By.cssSelector(".forgotten-password-page");
    private static final By SELECT_DAY = By.id("day");
    private static final By SELECT_MONTH = By.id("month");
    private static final By SELECT_YEAR = By.id("year");
    private static final By SELECT_MOBILE = By.name("date");
    private static final By INPUT_USERNAME = By.name("username");
    private static final By LINK_LOGIN = By.cssSelector(".forgotten-password-page__login-link a");
    private static final By LINK_REGISTER = By.cssSelector(".forgotten-password-page__register-link a");
    private static final By LINK_CONTACT_US_MOBILE = By.cssSelector(".fn-wc-container a");
    private static final By CONFIRMATION_MESSAGE = By.cssSelector(".fn-confirmation-information");
    private static final By RECAPTCHA = By.cssSelector(".fn-recaptcha>div");
    private static final By LOGIN_PAGE_DIV = By.cssSelector("div.login-page");

    private static final By VALIDATION_BIRTHDAY = By.cssSelector(".fieldset_name_birthdate");
    private static final By VALIDATION_USERNAME = By.cssSelector(".fn-username");

    //today is 18
    public void enterValidBirthday() {

        switch (DataProvider.getCurrentPlatform()) {
            case desktop:
                int year = Integer.valueOf(DataProvider.DateUtils.getCurrentYear())-18;

                Driver.setDropdownOptionByValue(SELECT_DAY, DataProvider.DateUtils.getCurrentDay());
                Driver.setDropdownOptionByValue(SELECT_MONTH, DataProvider.DateUtils.getCurrentMonth());
                Driver.setDropdownOptionByValue(SELECT_YEAR, Integer.toString(year));
                break;

            case tablet:
            case mobile:
                Driver.inputTextToInvisibleField(SELECT_MOBILE, DataProvider.DateUtils.getSomeYearsAgo(18));
                break;
        }
    }

    //too young
    public void enterInvalidBirthday() {

        switch (DataProvider.getCurrentPlatform()) {
            case desktop:
                int year = Integer.valueOf(DataProvider.DateUtils.getCurrentYear()) - 17;

                Driver.setDropdownOptionByValue(SELECT_DAY, DataProvider.DateUtils.getCurrentDay());
                Driver.setDropdownOptionByValue(SELECT_MONTH, DataProvider.DateUtils.getCurrentMonth());
                Driver.setDropdownOptionByValue(SELECT_YEAR, Integer.toString(year));
                break;

            case tablet:
            case mobile:
                Driver.inputTextToInvisibleField(SELECT_MOBILE, DataProvider.DateUtils.getSomeYearsAgo(17));
                break;
        }
    }

    public void enterUsername(String username) {
        Driver.inputTextToField(INPUT_USERNAME, username);
    }

    public void clickLoginLink() {
        Driver.click(LINK_LOGIN);
    }

    public void clickRegisterLink() {
        Driver.click(LINK_REGISTER);
    }

    public void clickContactUsMobileLink() {
        Driver.click(LINK_CONTACT_US_MOBILE);
    }


    public boolean isConfirmationInfoVisible(){
        return Driver.isElementVisible(CONFIRMATION_MESSAGE);
    }

    public boolean isReCaptchaVisible(){
        return Driver.isElementVisible(RECAPTCHA);
    }

    public boolean isBirthdayInvalid(){
        return isFieldInvalid(VALIDATION_BIRTHDAY);
    }

    public boolean isUsernameInvalid(){
        return isFieldInvalid(VALIDATION_USERNAME);
    }

    public boolean isLoginPageOpened(){
        return Driver.isElementPresent(LOGIN_PAGE_DIV);
    }

    public boolean isContactUsMobilePageOpened(){
        return Driver.getURLSuffix().equals("contact-us-mobile");
    }

    public boolean isRegistrationPage() {
        return Driver.getURLSuffix().equals("register-step-1");
    }

    public void openForgottenPasswordTabletPage(){
        Driver.openPage(DataProvider.getBaseUrl()+"forgotten-password-tablet");
    }

    public boolean isForgottenPasswordTabletPage(){
        return Driver.isElementVisible(ROOT);
    }

}
