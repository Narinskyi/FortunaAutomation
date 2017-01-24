package pageObjects.planB;

import org.openqa.selenium.By;
import utils.Driver;

public class HomePage extends AbstractCasinoPage {

    private static final By BUTTON_LOGIN = By.cssSelector(".fn-login");
    private static final By BUTTON_SIGN_UP = By.cssSelector(".btn_action_sign-up");
    private static final By USER_INFO = By.cssSelector(".user-info-replacer");
    private static final By INPUT_SEARCH_GAME = By.cssSelector(".fn-search-input");
    private static final By BUTTON_PLAY_REAL = By.cssSelector(".fn-launch-game:nth-of-type(2)");
    private static final String GAME_ITEM = "li.gamesinfo__item:nth-of-type";


    public void clickLogin() {
        Driver.click(BUTTON_LOGIN);
    }

    public void clickGameItem(int whichOne) {
        Driver.click(By.cssSelector(GAME_ITEM+"("+whichOne+")"));
    }

    public void waitForUserInfo(){
        Driver.waitForElementVisibility(USER_INFO);
    }

    public void clickRealPlay() {Driver.click(BUTTON_PLAY_REAL);}

}
