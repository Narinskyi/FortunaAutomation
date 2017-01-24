package pageObjects.planA;

import enums.Platform;
import org.openqa.selenium.By;
import springConstructors.UserData;
import utils.Driver;
import java.util.concurrent.ThreadLocalRandom;

//temporary solution
public class FortunaextdevPage extends AbstractMyAccountPage {

    private static final By INPUT_USERNAME_XP = By.name("username");
    private static final By INPUT_PASSWORD_XP = By.name("password");
    private static final By BUTTON_SIGN_IN_XP = By.xpath("//input[@type='submit']");
    private static final By ICON_SIGNED_IN_XP = By.id("welcome_id");
    private static final By BUTTON_ADD_SLIP_XP = By.xpath("(//button[@name='add-to-slip'][not(contains(@disabled,'disabled'))])[5]");
    private static final By INPUT_STAKE_XP = By.cssSelector("input.stake");
    private static final By BUTTON_PLACE_SLIP_XP = By.name("place_slip");
    private static final By BUTTON_CONFIRM_SLIP_XP = By.name("confirm_slip_placed");

    private static final By MOBILE_SIGN_IN = By.cssSelector("a.do-login");
    private static final By MOBILE_SIGNED_IN_ICON = By.cssSelector("a.go-account");


    public void openFortunaextdev() {
        Driver.openPage("http://fortunacz-fortunaextdev.custenv.geneity.co.uk");
    }

    private void loginWithCredentials(String username, String password) {

        if (isPlatform(Platform.mobile)){
            Driver.click(MOBILE_SIGN_IN);
            Driver.inputTextToField(INPUT_USERNAME_XP, username);
            Driver.inputTextToField(INPUT_PASSWORD_XP, password);
            Driver.click(BUTTON_SIGN_IN_XP);
            Driver.waitForElementVisibility(MOBILE_SIGNED_IN_ICON);

        } else {
            Driver.inputTextToField(INPUT_USERNAME_XP, username);
            Driver.inputTextToField(INPUT_PASSWORD_XP, password);
            Driver.click(BUTTON_SIGN_IN_XP);
            Driver.waitForElementVisibility(ICON_SIGNED_IN_XP);
        }

    }

    public void loginWithDefaultCredentials(UserData userData){
        loginWithCredentials(userData.getUsername(), userData.getPassword());
    }


    public void makeBets(int howMany){

        for (int i=0; i<howMany; i++) {

            Driver.click(BUTTON_ADD_SLIP_XP);
            Driver.waitForElementVisibility(INPUT_STAKE_XP);
            Driver.inputTextToField(INPUT_STAKE_XP,
                    Integer.toString(ThreadLocalRandom.current().nextInt(70, 100 + 1)));
            Driver.waitForElementVisibility(BUTTON_PLACE_SLIP_XP);
            Driver.click(BUTTON_PLACE_SLIP_XP);
            Driver.waitForElementVisibility(BUTTON_CONFIRM_SLIP_XP);

        }

    }
}
