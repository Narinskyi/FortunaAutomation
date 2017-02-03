package pageObjects.planB;

import org.openqa.selenium.By;
import utils.Driver;

public class HomePage extends AbstractCasinoPage {

    private static final By BUTTON_SIGN_UP = By.cssSelector(".btn_action_sign-up");
    private static final By BUTTON_DEPOSIT = By.cssSelector(".btn.btn_action_deposit");
    private static final By GAMES_PORTLET = By.cssSelector(".portlet__content_type_games-info");
    private static final By INPUT_SEARCH_GAME = By.cssSelector(".fn-search-input");
    private static final String BUTTON_PLAY_REAL = "span.fn-launch-game:nth-of-type(2)";
    private static final String BUTTON_PLAY_DEMO = "span.fn-launch-game:nth-of-type(3)";
    private static final String GAME_ITEM = "li.gamesinfo__item";
    private static final By FAVOURITES_CATEGORY = By.cssSelector(".gamesinfo__game-category-favorites");
    private static final By TOP_GAMES_CATEGORY = By.cssSelector(".gamesinfo__game-category-top-games");
    private static final String FAVOURITES_ICON_ACTIVE = ".fn-favorites.active";
    private static final String FAVOURITES_ICON_NOT_ACTIVE = ".fn-favorites:not(.active)";
    private static final String FAVOURITES_ICON_ANY = ".fn-favorites";

    public String getBalance(){
        return Driver.getElementText(BALANCE);
    }

    public String clickDeposit(){
        return Driver.getElementText(BUTTON_DEPOSIT);
    }

    public void clickGameIcon(int whichOne) {
        Driver.click(By.cssSelector(GAME_ITEM+ ":nth-of-type" + "("+whichOne+")"));
    }

    public void clickGameItemDemo(int whichOne) {
        Driver.hover(By.cssSelector(GAME_ITEM + ":nth-of-type" + "("+whichOne+")"));
        Driver.click(By.cssSelector(GAME_ITEM + ":nth-of-type" + "(" + whichOne + ") " + BUTTON_PLAY_DEMO));
    }

    public void clickGameItemReal(int whichOne) {
        Driver.hover(By.cssSelector(GAME_ITEM + ":nth-of-type" + "("+whichOne+")"));
        Driver.click(By.cssSelector(GAME_ITEM + ":nth-of-type" + "("+whichOne+") " + BUTTON_PLAY_REAL));
    }

    public boolean isFavouriteCategoryPresent(){
        return Driver.isElementVisible(FAVOURITES_CATEGORY, 3);
    }

    public boolean isFavouriteCategoryDisappeared(){
        return Driver.isElementDisappeared(FAVOURITES_CATEGORY, 3);
    }

    public void openFavouritesCategory(){
        Driver.click(FAVOURITES_CATEGORY);
    }

    public void openTopGamesCategory(){
        Driver.click(TOP_GAMES_CATEGORY);
    }

    public void removeAllFavourites(){
        while(Driver.isElementVisible(By.cssSelector(GAME_ITEM), 0)){
            Driver.click(By.cssSelector(FAVOURITES_ICON_ACTIVE));
        }
    }

    public void addGameToFavourites(String gameCode){
        Driver.click(By.cssSelector(FAVOURITES_ICON_NOT_ACTIVE + "[data-game-code=" + gameCode + "]"));
    }

    public boolean isFavouriteGamePresent(String gameCode){
        return Driver.isElementVisible(By.cssSelector(GAME_ITEM + "> div > div[data-game-code=" + gameCode + "]"));
    }

    public boolean isGamePortletPresent(){
        return Driver.isElementVisible(GAMES_PORTLET);
    }

    public LogoutPopup test (){
      return null;
    }

    public void test2 (){
        this.test();
    }

}
