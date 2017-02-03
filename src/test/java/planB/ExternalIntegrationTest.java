package planB;

import core.AbstractTest;
import core.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AvailablePages;
import pageObjects.planB.HomePage;
import utils.Driver;

/**
 * Created by tysa on 31.01.2017.
 */
public class ExternalIntegrationTest extends AbstractTest {

    HomePage homePage = PageFactory.getPage(AvailablePages.home);

    @Test(groups={"desktop"})
    public void depositButtonInHeaderRedirect(){
        homePage.open();
        homePage.login(userData.getUsername(), userData.getPassword());
        homePage.clickDeposit();
        Assert.assertTrue(Driver.isNewTabOpened(), "deposit tab wasn't opened");
    }

}
