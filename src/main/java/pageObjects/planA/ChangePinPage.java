package pageObjects.planA;

import enums.Platform;
import org.openqa.selenium.By;
import utils.DataProvider;
import utils.Driver;

public class ChangePinPage extends AbstractMyAccountPage {

    private static final By INPUT_NEW_PIN = By.name("pin");
    private static final By VALIDATION_NEW_PIN = By.cssSelector(".field_name_new-pin");
    private static final By INPUT_RETYPE_NEW_PIN = By.name("pinVerify");
    private static final By VALIDATION_RETYPE_NEW_PIN = By.cssSelector(".field_name_confirm-new-pin");
    private static final By INPUT_CURRENT_PASSWORD = By.name("password");
    private static final By VALIDATION_CURRENT_PASSWORD = By.cssSelector(".field_name_password");
    private static final By NEW_PIN_VISIBILITY_ICON = By.xpath("(//div[contains(@class, 'fn-toggle-password-visibility')])[1]");
    private static final String NEW_PIN_VISIBILITY_ICON_CSS = ".fn-toggle-password-visibility";
    private static final By INFO_AREA = By.cssSelector("div.fn-wc-container");


    public void fillNewPin(String input){
        Driver.clearAndInputTextToField(INPUT_NEW_PIN,input);
    }

    public void fillRetypePin(String input){
        Driver.clearAndInputTextToField(INPUT_RETYPE_NEW_PIN,input);
    }

    public void fillCurrentPassword(String input){
        Driver.clearAndInputTextToField(INPUT_CURRENT_PASSWORD,input);
    }

    public void clickNewPinVisiblityIcon(){
        if (DataProvider.getCurrentPlatform().equals(Platform.tablet)) {
            Driver.tap(NEW_PIN_VISIBILITY_ICON_CSS);
        } else {
            Driver.click(NEW_PIN_VISIBILITY_ICON);
        }
    }

    public boolean isNewPinVisible(){

        String pinVisibilityType = Driver.getAttribute(INPUT_NEW_PIN, "type");
        switch (pinVisibilityType) {

            case "password":
                return false;
            case "text":
                return true;

            default: return false;
        }
    }

    public boolean isNewPinInvalid(){
        return isFieldInvalid(VALIDATION_NEW_PIN);
    }

    public boolean isNewPinValid(){
        return isFieldValid(VALIDATION_NEW_PIN);
    }

    public boolean isNewPinEmpty(){
        return Driver.getElementText(INPUT_NEW_PIN).isEmpty();
    }

    public boolean isRetypeNewPinInvalid(){
        return isFieldInvalid(VALIDATION_RETYPE_NEW_PIN);
    }

    public boolean isRetypeNewPinValid(){
        return isFieldValid(VALIDATION_RETYPE_NEW_PIN);
    }

    public boolean isRetypeNewPinEmpty(){
        return Driver.getElementText(INPUT_RETYPE_NEW_PIN).isEmpty();
    }

    public boolean isCurrentPasswordValid(){
        return isFieldValid(VALIDATION_CURRENT_PASSWORD);
    }

    public boolean isCurrentPasswordInvalid(){
        return isFieldInvalid(VALIDATION_CURRENT_PASSWORD);
    }

    public boolean isCurrentPasswordEmpty(){
        return Driver.getElementText(INPUT_CURRENT_PASSWORD).isEmpty();
    }

    public void clickInfo(){
        Driver.click(INFO_AREA);
    }
}
