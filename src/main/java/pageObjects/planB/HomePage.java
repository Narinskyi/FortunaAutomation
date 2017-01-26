package pageObjects.planB;

import org.openqa.selenium.By;
import utils.Driver;

public class HomePage extends AbstractCasinoPage {

    private static final By BUTTON_SIGN_UP = By.cssSelector(".btn_action_sign-up");
    private static final By INPUT_SEARCH_GAME = By.cssSelector(".fn-search-input");
    private static final String BUTTON_PLAY_REAL = "span.fn-launch-game:nth-of-type(2)";
    private static final String BUTTON_PLAY_DEMO = "span.fn-launch-game:nth-of-type(3)";
    private static final String GAME_ITEM = "li.gamesinfo__item:nth-of-type";
    private static final By FAVOURITES_CATEGORY = By.cssSelector(".gamesinfo__game-category-favorites");

    public void clickGameIcon(int whichOne) {
        Driver.click(By.cssSelector(GAME_ITEM+"("+whichOne+")"));
    }

    public void clickGameItemDemo(int whichOne) {
        Driver.hover(By.cssSelector(GAME_ITEM+"("+whichOne+")"));
        Driver.click(By.cssSelector(GAME_ITEM+"("+whichOne+") " + BUTTON_PLAY_DEMO));
    }

    public void clickGameItemReal(int whichOne) {
        Driver.hover(By.cssSelector(GAME_ITEM+"("+whichOne+")"));
        Driver.click(By.cssSelector(GAME_ITEM+"("+whichOne+") " + BUTTON_PLAY_REAL));
    }

    public boolean isFavouriteCategoryPresent(){
        return Driver.isElementVisible(FAVOURITES_CATEGORY, 3);
    }

}
