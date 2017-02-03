package planB;

import core.AbstractTest;
import core.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AvailablePages;
import pageObjects.planB.BalancePopup;
import pageObjects.planB.HomePage;

/**
 * Created by tysa on 31.01.2017.
 */
public class BalancesTest extends AbstractTest {

    HomePage homePage = PageFactory.getPage(AvailablePages.home);
    BalancePopup balancePopup = new BalancePopup();

    @Test(groups={"desktop"})
    public void compareBalances(){
        homePage.open();
        homePage.login(userData.getUsername(), userData.getPassword());
        String balanceInHeader = homePage.getBalance();
        homePage.clickBalance();
        String balanceInPopup = balancePopup.getBalance();
        Assert.assertEquals(balanceInHeader, balanceInPopup, "Balance in header differs from balance in popup");
    }


}
