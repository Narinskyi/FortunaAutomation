package planA.Tests;

import core.AbstractTest;
import core.PageFactory;
import pageObjects.AvailablePages;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.planA.DashboardPage;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

@Features("User")
@Stories("Dashboard")
public class DashboardTest extends AbstractTest {

    private static DashboardPage dashboardPage = PageFactory.getPage(AvailablePages.dashboard);

    @BeforeClass(alwaysRun = true)
    public void loginWithExisitingUser (){
        dashboardPage.loginWithExisitingUser(userData);
    }

    @Test (groups = {"desktop", "tablet", "mobile"})
    public void userDataTest(){
        dashboardPage.open();

        //verify that actual user data is displayed
        Assert.assertTrue(dashboardPage.getName().equals(userData.getFirstName()), "Name was incorrect");
        Assert.assertTrue(dashboardPage.getSurname().equals(userData.getLastName()), "Surname was incorrect");
        Assert.assertTrue(dashboardPage.getUsername().equals(userData.getUsername()), "Username was incorrect");
    }

    @Test (groups = {"desktop", "tablet", "mobile"})
    public void defaultBalanceTest(){
        dashboardPage.open();

        //verify the balance and currency
        Assert.assertTrue(dashboardPage.getBalance().contains("Kč"), "Currency label was incorrect");
        Assert.assertTrue(dashboardPage.getBalance().contains("0.00"), "Balance Test failed");
    }

    @Test (groups = {"desktop", "tablet"})
    public void buttonsLinksTest() {

        dashboardPage.open();
        Assert.assertTrue(dashboardPage.isMessagesLinkCorrect(AvailablePages.inbox), "Inbox button Test failed");
        Assert.assertTrue(dashboardPage.isDepositLinkCorrect(AvailablePages.deposit), "Deposit button Test failed");
        Assert.assertTrue(dashboardPage.isWithdrawLinkCorrect(AvailablePages.withdraw), "Withdraw button Test failed");
        Assert.assertTrue(dashboardPage.isEditProfileLinkCorrect(AvailablePages.personalSettings), "Edit profile button Test failed");
        Assert.assertTrue(dashboardPage.isShowPrematchLinkCorrect(AvailablePages.prematchBetslipHistory), "Show Prematch button Test failed");
        Assert.assertTrue(dashboardPage.isShowLiveLinkCorrect(AvailablePages.liveBetslipHistory), "Show Live button Test failed");
    }

    @Test (groups = {"desktop", "tablet"})
    public void noMessagesButtonTest(){
        dashboardPage.open();
        Assert.assertTrue(dashboardPage.getMessagesButtonLabel().equals("Messages"), "Message counter Test failed");
    }

    @Test (groups = {"mobile"})
    public void noMessagesCounterTest(){
        dashboardPage.open();
        Assert.assertTrue(dashboardPage.getMessagesCounter()==0, "Message counter Test failed");
    }

    @Test (groups = {"desktop", "tablet"})
    public void tablesAreEmptyByDefaultTest() {
        dashboardPage.open();
        Assert.assertTrue(dashboardPage.areTablesEmpty());
    }
}
