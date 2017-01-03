package Tests;

import Preconditions.PreconditionalSteps;
import core.AbstractTest;
import core.PageFactory;
import enums.AvailablePages;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.NotificationsPage;

public class NotificationsTest extends AbstractTest {

    private static NotificationsPage notificationsPage = PageFactory.getPage(AvailablePages.notifications);

    @BeforeClass(alwaysRun = true)
    public void prepareUserAndLogin (){
        PreconditionalSteps.prepareUserAndLogin();
    }

    @Test(groups = {"desktop", "tablet", "mobile"})
    public void checkAllTest(){
        notificationsPage.open();
        notificationsPage.checkAllCheckboxes();
        notificationsPage.clickSubmit();
        Assert.assertTrue(notificationsPage.areSuccessMessagesDisplayed(1), "Success message was not displayed");
    }

    @Test(groups = {"desktop", "tablet", "mobile"})
    public void uncheckAllTest(){
        notificationsPage.open();
        notificationsPage.uncheckAllCheckboxes();
        notificationsPage.clickSubmit();
        Assert.assertTrue(notificationsPage.areSuccessMessagesDisplayed(1), "Success message was not displayed");
    }
}
