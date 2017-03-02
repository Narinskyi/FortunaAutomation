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
        double balanceInHeader = homePage.getBalance();
        homePage.clickBalance();
        double totalBalanceInPopup = balancePopup.getSumBalance();
        Assert.assertEquals(balanceInHeader, totalBalanceInPopup, "Balance in header differs from total balance in popup");
    }


}
