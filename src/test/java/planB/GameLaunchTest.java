package planB;

import core.AbstractTest;
import core.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.AvailablePages;
import pageObjects.planB.GamePage;
import pageObjects.planB.HomePage;
import pageObjects.planB.iFrames.GameIframe;
import pageObjects.planB.iFrames.LoginModal;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

@Features("Games")
@Stories("Game Launch")
public class GameLaunchTest extends AbstractTest{

    private static HomePage homePage = PageFactory.getPage(AvailablePages.home);
    private static LoginModal loginModal = LoginModal.get();
    private static GameIframe gameIframe = GameIframe.get();
    private static GamePage gamePage = new GamePage();

    @Test (groups = "desktop",enabled=true)
    public void guestLaunchDemoGame() {
        homePage.open();
        homePage.clickGameItemDemo(1);
        Assert.assertTrue(gameIframe.isLaunchedInDemoMode(), "Game launch in real mode failed");
    }

    @Test (groups = "desktop",enabled=true)
    public void userLaunchRealGame() {
        homePage.open();
        homePage.login(userData.getUsername(), userData.getPassword());
        homePage.clickGameItemReal(1);
        Assert.assertTrue(gameIframe.isLaunchedInRealMode(), "Game launch in real mode failed");
        Assert.assertTrue(gamePage.isBalanceDisappeared(), "Balance hiding when game is launched failed");
    }

    @Test (groups = "desktop",enabled=true)
    public void guestLaunchRealGameAndLogin() {
        homePage.open();
        homePage.clickGameItemReal(1);
        loginModal.login(userData.getUsername(), userData.getPassword());
        Assert.assertTrue(gameIframe.isLaunchedInRealMode(), "Game launch in real mode failed");
        Assert.assertTrue(gamePage.isBalanceDisappeared(), "Balance hiding when game is launched failed");
    }

    @Test (groups = "desktop",enabled=true)
    public void balanceIsShownAfterGameClosing() {
        homePage.open();
        homePage.login(userData.getUsername(), userData.getPassword());
        homePage.clickGameItemReal(1);
        Assert.assertFalse(gamePage.isBalanceDisappeared(), "Balance hiding when game is launched failed");
        gamePage.clickLogo();
        Assert.assertTrue(gamePage.isBalanceVisible(), "Balance appearing when game is closed failed");
    }



}
