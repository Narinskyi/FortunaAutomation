package pageObjects.planA;

import org.openqa.selenium.By;
import utils.DataProvider;
import utils.Driver;

public class RegistrationPage extends AbstractMyAccountPage {

    private static final By INPUT_FIRSTNAME = By.name("firstname");
    private static final By INPUT_SURENAME = By.name("lastname");
    private static final By INPUT_USERNAME = By.name("userName");
    private static final By INPUT_MOBILE = By.name("cellphone");
    private static final By INPUT_EMAIL = By.name("email");
    private static final By SELECT_DAY = By.id("day");
    private static final By SELECT_MONTH = By.id("month");
    private static final By SELECT_YEAR = By.id("year");
    private static final By SELECT_MOBILE = By.id("date");
    private static final By BUTTON_CREATE_MY_ACCOUNT = By.cssSelector("button.fn-submit");

    public void registerUser(){
        Driver.inputTextToField(INPUT_FIRSTNAME, DataProvider.getUserData().getFirstName());
        Driver.inputTextToField(INPUT_SURENAME, DataProvider.getUserData().getLastName());
        Driver.inputTextToField(INPUT_USERNAME, DataProvider.getUserData().getUsername());
        Driver.inputTextToField(INPUT_MOBILE, DataProvider.getUserData().getMobile());
        Driver.inputTextToField(INPUT_EMAIL, DataProvider.getUserData().getEmail());
        switch (DataProvider.getCurrentPlatform()) {
            case desktop:
                Driver.setDropdownOptionByValue(SELECT_DAY, DataProvider.getUserData().getBirthDay());
                Driver.setDropdownOptionByValue(SELECT_MONTH, DataProvider.getUserData().getBirthMonth());
                Driver.setDropdownOptionByValue(SELECT_YEAR, DataProvider.getUserData().getBirthYear());
                break;
            case tablet:
            case mobile:
                Driver.inputTextToField(SELECT_MOBILE, DataProvider.getUserData().getBirthDay()+
                        DataProvider.getUserData().getBirthMonth()+
                        DataProvider.getUserData().getBirthYear());
                break;
        }

        Driver.click(BUTTON_CREATE_MY_ACCOUNT);
    }
}
