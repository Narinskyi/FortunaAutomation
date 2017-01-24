package planB;

import core.AbstractTest;
import core.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.AvailablePages;
import pageObjects.planB.HomePage;
import pageObjects.planB.iFrames.Game;
import pageObjects.planB.iFrames.LoginModal;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

@Features("Games")
@Stories("Game Launch")
@Test(enabled=false)
public class GameLaunchTest extends AbstractTest{

    private static HomePage homePage = PageFactory.getPage(AvailablePages.home);
    private static LoginModal loginModal = LoginModal.get();
    private static Game game = Game.get();

    @Test (groups = "desktop")
    public void guestLaunchTest() {
        homePage.open();
        homePage.clickGameItem(1);
        Assert.assertTrue(loginModal.isOpened(), "Login modal was not opened");
    }

    @Test (groups = "desktop")
    public void userLaunchTest() {
        homePage.open();
        homePage.clickLogin();
        loginModal.login("testpb07", "playtech");
        homePage.waitForUserInfo();
        homePage.clickGameItem(1);
        Assert.assertTrue(game.isLaunchedInRealMode(), "Game launch in real mode failed");
    }

}
