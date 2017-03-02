package planB;

import core.AbstractTest;
import core.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AvailablePages;
import pageObjects.planB.GamePage;
import pageObjects.planB.HomePage;
import springConstructors.UserData;
import utils.Driver;

/**
 * Created by tysa on 31.01.2017.
 */
public class LogoutTest extends AbstractTest {

    HomePage homePage = PageFactory.getPage(AvailablePages.home);
    GamePage gamePage = PageFactory.getPage(AvailablePages.game);

    @Test(groups= {"desktop"})
    public void logoutFromGame(){
        homePage.open();
        homePage.login(userData.getUsername(), userData.getPassword());
        homePage.clickGameItemReal(1);
        new GamePage().logout();
        Assert.assertTrue(homePage.isGamePortletPresent(), "user wasn't navigated to home page after logout");
    }
}
