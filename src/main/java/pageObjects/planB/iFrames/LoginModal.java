package pageObjects.planB.iFrames;

import org.openqa.selenium.By;
import springConstructors.UserData;
import utils.Driver;

public class LoginModal implements Iframe{


    private static final By MODAL_IFRAME = By.cssSelector(".fn-popup-loader iframe");
    private static final By INPUT_USERNAME = By.name("login");
    private static final By INPUT_PASSWORD = By.name("password");
    private static final By BUTTON_CLOSE = By.cssSelector(".fn-close");
    private static final By BUTTON_SUBMIT = By.cssSelector("button[type='submit']");

    private LoginModal(){}

    public void enterUsername(String username) {
        in();
        Driver.inputTextToField(INPUT_USERNAME, username);
        out();
    }

    public void enterPassword(String password) {
        in();
        Driver.inputTextToField(INPUT_PASSWORD, password);
        out();
    }

    public void clickSubmit() {
        in();
        Driver.click(BUTTON_SUBMIT);
        out();
    }

    public void login(UserData userData) {
        enterUsername(userData.getUsername());
        enterPassword(userData.getPassword());
        clickSubmit();
    }

    //to be removed - after introduction of registration steps, when UserData is available
    public void login (String username, String password){
        enterUsername(username);
        enterPassword(password);
        clickSubmit();
    }

    public void closeModal() { Driver.click(BUTTON_CLOSE);}

    public boolean isOpened(){
        return Driver.isElementPresent(MODAL_IFRAME);
    }

    @Override
    public void in(){
        Driver.switchToFrame(MODAL_IFRAME);
    }

    public static LoginModal get() {
        return new LoginModal();
    }

}
