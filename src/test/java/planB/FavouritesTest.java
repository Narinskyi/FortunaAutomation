package planB;

import core.AbstractTest;
import core.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AvailablePages;
import pageObjects.planB.HomePage;

/**
 * Created by tysa on 26.01.2017.
 */
public class FavouritesTest extends AbstractTest {

    private static HomePage homePage = PageFactory.getPage(AvailablePages.home);

    @Test(groups = {"desktop"})
    public void favouritesCategoryAppearsAfterLogin(){
        homePage.open();
        Assert.assertFalse(homePage.isFavouriteCategoryPresent(), "Favourites are present for guest");
        homePage.login(userData.getUsername(), userData.getPassword());
        Assert.assertTrue(homePage.isFavouriteCategoryPresent(), "Favourites are absent for user");
    }

    @Test(groups = {"desktop"})
    public void favouritesCategoryDisappearsAfterLogout(){
        homePage.open();
        homePage.login(userData.getUsername(), userData.getPassword());
        Assert.assertTrue(homePage.isFavouriteCategoryPresent(), "Favourites are absent for user");
        homePage.logout();
        Assert.assertTrue(homePage.isFavouriteCategoryDisappeared(), "Favourites are present for guest after logout");
    }

    @Test (groups = {"desktop"})
    public void addToFavourites(){
        homePage.open();
        homePage.login(userData.getUsername(), userData.getPassword());
        homePage.openFavouritesCategory();
        homePage.removeAllFavourites();
        homePage.openTopGamesCategory();
        homePage.addGameToFavourites("whk");
        homePage.openFavouritesCategory();
        Assert.assertTrue(homePage.isFavouriteGamePresent("whk"), "Game isn't present into favourites after adding to favourites");
    }
}
