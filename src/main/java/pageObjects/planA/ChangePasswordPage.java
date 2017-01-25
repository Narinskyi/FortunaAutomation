package pageObjects.planA;


import enums.Platform;
import org.openqa.selenium.By;
import utils.DataProvider;
import utils.Driver;

public class ChangePasswordPage extends AbstractMyAccountPage {

    private static final By INPUT_CURRENT_PASSWORD = By.name("oldPassword");
    private static final By VALIDATION_CURRENT_PASSWORD = By.cssSelector(".field_name_old-password");
    private static final By INPUT_NEW_PASSWORD = By.name("password");
    private static final By VALIDATION_NEW_PASSWORD = By.cssSelector(".field_name_new-password");
    private static final By INPUT_RETYPE_NEW_PASSWORD = By.name("passwordVerify");
    private static final By VALIDATION_RETYPE_NEW_PASSWORD = By.cssSelector(".field_name_confirm-new-password");
    private static final By CURRENT_PASSWORD_VISIBILITY_ICON = By.xpath("(//div[contains(@class, 'fn-toggle-password-visibility')])[1]");
    private static final String CURRENT_PASSWORD_VISIBILITY_ICON_CSS = ".fn-toggle-password-visibility";
    private static final By INFO_AREA = By.cssSelector("div.fn-wc-container");

    public void fillCurrentPassword(String input){
        Driver.clearAndInputTextToField(INPUT_CURRENT_PASSWORD,input);
    }

    public void fillNewPassword(String input){
        Driver.clearAndInputTextToField(INPUT_NEW_PASSWORD,input);
    }

    public void fillRetypePassword(String input){
        Driver.clearAndInputTextToField(INPUT_RETYPE_NEW_PASSWORD,input);
    }

    public void clickCurrentPassVisiblityIcon(){
        if (DataProvider.getCurrentPlatform().equals(Platform.tablet)){
            Driver.tap(CURRENT_PASSWORD_VISIBILITY_ICON_CSS);
        } else {
            Driver.click(CURRENT_PASSWORD_VISIBILITY_ICON);
        }
    }

    public boolean isCurrentPasswordVisible(){

        String passwordVisibilityType = Driver.getAttribute(INPUT_CURRENT_PASSWORD, "type");
        switch (passwordVisibilityType) {

            case "password":
                return false;
            case "text":
                return true;

            default: return false;
        }
    }

    public boolean isCurrentPasswordInvalid(){
        return isFieldInvalid(VALIDATION_CURRENT_PASSWORD);
    }

    public boolean isNewPasswordInvalid(){
        return isFieldInvalid(VALIDATION_NEW_PASSWORD);
    }

    public boolean isRetypeNewPasswordInvalid(){
        return isFieldInvalid(VALIDATION_RETYPE_NEW_PASSWORD);
    }

    public boolean isCurrentPasswordValid(){
        return isFieldValid(VALIDATION_CURRENT_PASSWORD);
    }

    public boolean isNewPasswordValid(){
        return isFieldValid(VALIDATION_NEW_PASSWORD);
    }

    public boolean isRetypeNewPasswordValid(){
        return isFieldValid(VALIDATION_RETYPE_NEW_PASSWORD);
    }

    public void clickInfo(){
        Driver.click(INFO_AREA);
    }
    
}
