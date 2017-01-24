package planA.Tests;

import planA.Preconditions.PreconditionalSteps;
import core.AbstractTest;
import core.PageFactory;
import pageObjects.AvailablePages;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.planA.QuickBetPage;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

@Features("User")
@Stories("Quick Bet")
public class QuickBetTest extends AbstractTest {

    private static QuickBetPage quickBetPage = PageFactory.getPage(AvailablePages.quickBet);

    @BeforeClass(alwaysRun = true)
    public void prepareUserAndLogin (){
        PreconditionalSteps.prepareUserAndLogin(userData);
    }

    @Test (groups = {"desktop", "tablet", "mobile"}, priority = -1)
    public void areFieldsEmptyByDefaultTest(){
        quickBetPage.open();
        Assert.assertTrue(quickBetPage.areFieldsEmpty());
    }

    @Test (groups = {"desktop", "tablet", "mobile"})
    public void validInputTest(){

        quickBetPage.open();

        //success for 100 input in 1st field; 10 and 999999.99 - lower and upper boundaries
        quickBetPage.fillQuickbet1("100");
        quickBetPage.fillQuickbet2("10.00");
        quickBetPage.fillQuickbet3("999999.99");

        quickBetPage.clickSubmit();

        if(!isMobile) {
            Assert.assertTrue(quickBetPage.areSuccessMessagesDisplayed(1), "Success messages were not displayed");
        } else {
            Assert.assertTrue(quickBetPage.isMobilePopupDisplayed(), "Success message was not displayed");
        }



        //verify that values are saved
        quickBetPage.open();
        Assert.assertTrue(quickBetPage.isQuickbet1("100.00"),"Quickbet 1 was not saved");
        Assert.assertTrue(quickBetPage.isQuickbet2("10.00"),"Quickbet 2 was not saved");
        Assert.assertTrue(quickBetPage.isQuickbet3("999 999.99"),"Quickbet 3 was not saved");
    }

    @Test (groups = {"desktop", "tablet", "mobile"})
    public void invalidInputTest(){

        quickBetPage.open();

        //boundaries for invalid input
        quickBetPage.fillQuickbet2("9.99");
        quickBetPage.fillQuickbet3("1000000.00");

        quickBetPage.clickInfo();

        if (!isMobile) {
            Assert.assertTrue(quickBetPage.areErrorMessagesDisplayed(2), "Error messages were not displayed");
        }
        Assert.assertTrue(quickBetPage.isQuickbet2Invalid(),"Quickbet 2 field validation failed");
        Assert.assertTrue(quickBetPage.isQuickbet3Invalid(),"Quickbet 3 field validation failed");
    }


    @Test (groups = {"desktop", "tablet", "mobile"})
    public void inputFieldsUiTest(){

        quickBetPage.open();

        //verify currency labels and that inputs are right-aligned
        Assert.assertTrue(quickBetPage.getCurrency().equals("Kč"), "Currency label was incorrect");
        if(!isMobile) {
            Assert.assertTrue(quickBetPage.isInputRightAligned(), "Input was not right-aligned");
        }
    }

    @Test (groups = {"desktop", "tablet", "mobile"})
    public void validationRulesTest(){

        quickBetPage.open();

        //verify that 0 is cleared during formatting
        quickBetPage.fillQuickbet1("0.00");
        quickBetPage.clickInfo();
        Assert.assertTrue(quickBetPage.isQuickbet1(""), "0 was not cleared during formatting");

        //verify that alphabetical symbols are deleted during formatting
        quickBetPage.fillQuickbet1("AlphabeticalString");
        quickBetPage.clickInfo();
        Assert.assertTrue(quickBetPage.isQuickbet1(""),"Alphabetical symbols were not deleted during formatting");

        //verify rounding rules
        quickBetPage.fillQuickbet1("9.996");
        quickBetPage.clickInfo();
        Assert.assertTrue(quickBetPage.isQuickbet1("10.00"), "Rounding rules test failed");

        //verify the fractional part formatting
        quickBetPage.fillQuickbet1("11.0");
        quickBetPage.clickInfo();
        Assert.assertTrue(quickBetPage.isQuickbet1("11.00"), "Fractional part formatting test failed");

        //verify that , is substituted with .
        quickBetPage.fillQuickbet1("10,01");
        quickBetPage.clickInfo();
        Assert.assertTrue(quickBetPage.isQuickbet1("10.01"), "Comma substitution test failed");

        //verify that whitespaces are trimmed
        quickBetPage.fillQuickbet1("99  .  99  ");
        quickBetPage.clickInfo();
        Assert.assertTrue(quickBetPage.isQuickbet1("99.99"), "Trimmed whitespaces test failed");

        //verify formatting for big numbers
        quickBetPage.fillQuickbet1("1000000.00");
        quickBetPage.clickInfo();
        Assert.assertTrue(quickBetPage.isQuickbet1("1 000 000.00"), "Big numbers formatting test failed");

        //verify complex input formatting
        quickBetPage.fillQuickbet1("!asd12.32@d*(");
        quickBetPage.clickInfo();
        Assert.assertTrue(quickBetPage.isQuickbet1("12.32"), "Complex input formatting test failed");
    }

}
