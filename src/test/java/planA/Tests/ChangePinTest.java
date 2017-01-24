package planA.Tests;

import planA.Preconditions.PreconditionalSteps;
import core.AbstractTest;
import core.PageFactory;
import pageObjects.AvailablePages;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.planA.ChangePinPage;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

@Features("User")
@Stories("Change Pin")
public class ChangePinTest extends AbstractTest {
    
    private static ChangePinPage pinPage = PageFactory.getPage(AvailablePages.changePin);

    @BeforeClass(alwaysRun = true)
    public void prepareUserAndLogin (){
        PreconditionalSteps.prepareUserAndLogin(userData);
    }

    @Test (groups = {"desktop", "tablet", "mobile"})
    public void emptyFieldsTest(){

        pinPage.open();

        //submit all empty fields
        pinPage.clickSubmit();

        if (!isMobile) {
            Assert.assertTrue(pinPage.areErrorMessagesDisplayed(3), "Error message was not displayed");
        }
        Assert.assertTrue(pinPage.isCurrentPasswordInvalid(), "Current password validation failed");
        Assert.assertTrue(pinPage.isNewPinInvalid(), "New pin validation failed");
        Assert.assertTrue(pinPage.isRetypeNewPinInvalid(), "Retype new validation failed");

        System.out.println("=================End of all empty fields Test=================");


        //submit two empty fields
        pinPage.fillCurrentPassword("password");
        pinPage.clickSubmit();

        if (!isMobile) {
            Assert.assertTrue(pinPage.areErrorMessagesDisplayed(2), "Error message was not displayed");
        }
        Assert.assertTrue(pinPage.isCurrentPasswordValid(), "Current password validation failed");
        Assert.assertTrue(pinPage.isNewPinInvalid(), "New pin validation failed");
        Assert.assertTrue(pinPage.isRetypeNewPinInvalid(), "Retype new validation failed");
        System.out.println("=================End of two empty fields Test=================");

        //submit one empty field
        pinPage.fillNewPin("1234");
        pinPage.clickSubmit();
        if (!isMobile) {
            Assert.assertTrue(pinPage.areErrorMessagesDisplayed(1), "Error message was not displayed");
        }
        Assert.assertTrue(pinPage.isCurrentPasswordValid(), "Current password validation failed");
        Assert.assertTrue(pinPage.isNewPinValid(), "New pin validation failed");
        Assert.assertTrue(pinPage.isRetypeNewPinInvalid(), "Retype new validation failed");
        System.out.println("=================End of one empty field Test=================");
    }

    @Test (groups = {"desktop", "tablet", "mobile"})
    public void notMatchingFieldsTest(){

        pinPage.open();

        pinPage.fillCurrentPassword("password");
        pinPage.fillRetypePin("1234");
        pinPage.fillNewPin("4321");

        pinPage.clickSubmit();

        if (!isMobile) {
            Assert.assertTrue(pinPage.areErrorMessagesDisplayed(1), "Error message was not displayed");
        }
        Assert.assertTrue(pinPage.isCurrentPasswordValid(), "Current password validation failed");
        Assert.assertTrue(pinPage.isNewPinValid(), "New pin validation failed");
        Assert.assertTrue(pinPage.isRetypeNewPinInvalid(), "Retype new validation failed");
    }

    @Test (groups = {"desktop", "tablet", "mobile"})
    public void currentPasswordIsInvalidTest(){

        pinPage.open();

        pinPage.fillCurrentPassword("invalid");
        pinPage.fillNewPin("1234");
        pinPage.fillRetypePin("1234");

        pinPage.clickSubmit();

        //current password field should be cleared on submit of invalid password
        if (!isMobile) {
            Assert.assertTrue(pinPage.isErrorMessageDisplayed(), "Error message was not displayed");
            Assert.assertTrue(pinPage.isCurrentPasswordEmpty(), "Current password was not empty");
            Assert.assertTrue(pinPage.isNewPinValid(), "New pin validation failed");
            Assert.assertTrue(pinPage.isRetypeNewPinValid(), "Retype new validation failed");
        } else {
            Assert.assertTrue(pinPage.isMobilePopupDisplayed(), "Error message was not displayed");
        }

    }


    @Test (groups = {"desktop", "tablet", "mobile"})
    public void validationRulesTest(){

        //validation rules: pin should consist of 4 digits, strictly
        pinPage.open();

        //invalid of normal length
        pinPage.fillNewPin("1 !)");
        pinPage.clickInfo();
        if (!isMobile) {
            Assert.assertTrue(pinPage.areErrorMessagesDisplayed(1), "Error message was not displayed");
        }
        Assert.assertTrue(pinPage.isNewPinInvalid(), "New pin validation failed");
        System.out.println("=================End of invalid pin Test=================");

        //too short valid (3 symbols)
        pinPage.fillNewPin("123");
        pinPage.clickInfo();
        if (!isMobile) {
            Assert.assertTrue(pinPage.areErrorMessagesDisplayed(1), "Error message was not displayed");
        }
        Assert.assertTrue(pinPage.isNewPinInvalid(), "New pin validation failed");
        System.out.println("=================End of too short valid pin Test=================");

        //too long valid (5 symbols)
        pinPage.fillNewPin("12345");
        pinPage.clickInfo();
        if (!isMobile) {
            Assert.assertTrue(pinPage.areErrorMessagesDisplayed(1), "Error message was not displayed");
        }
        Assert.assertTrue(pinPage.isNewPinInvalid(), "New pin validation failed");
        System.out.println("=================End of too long valid pin Test=================");

        //valid (4 symbols)
        pinPage.fillNewPin("1234");
        pinPage.clickInfo();
        if (!isMobile) {
            Assert.assertTrue(pinPage.isNewPinValid(), "New pin validation failed");
        }
        System.out.println("=================End of valid pin Test=================");

    }

    @Test (groups = {"desktop", "tablet", "mobile"})
    public void pinVisibilityIconTest(){

        //verify that pin becomes visible once icon is clicked
        pinPage.open();

        pinPage.fillCurrentPassword("1234");
        pinPage.clickNewPinVisiblityIcon();
        Assert.assertTrue(pinPage.isNewPinVisible(), "Pin visibility test failed");

    }

    @Test (groups = {"desktop", "tablet", "mobile"})
    public void invalidPinTest(){

        //valid hardcoded pin is 1111, hence need to verify the invalid one
        pinPage.open();

        pinPage.fillNewPin("1234");
        pinPage.fillRetypePin("1234");
        pinPage.fillCurrentPassword(userData.getPassword());
        pinPage.clickSubmit();

        //all fields should be cleared on success
        if (!isMobile) {
            Assert.assertTrue(pinPage.isErrorMessageDisplayed(), "Error message was not displayed");
            Assert.assertTrue(pinPage.isNewPinValid(), "New pin validation failed");
            Assert.assertTrue(pinPage.isRetypeNewPinValid(), "Retype new validation failed");
            Assert.assertTrue(pinPage.isCurrentPasswordEmpty(), "Current password was not empty");
        } else {
            Assert.assertTrue(pinPage.isMobilePopupDisplayed(), "Error message was not displayed");
        }

    }

    @Test (groups = {"desktop", "tablet", "mobile"})
    public void positiveFlowTest() {

        //valid hardcoded pin is 1111
        pinPage.open();

        pinPage.fillNewPin("1111");
        pinPage.fillRetypePin("1111");
        pinPage.fillCurrentPassword(userData.getPassword());
        pinPage.clickSubmit();

        //all fields should be cleared on success
        if (!isMobile) {
            Assert.assertTrue(pinPage.areSuccessMessagesDisplayed(1), "Success message was not displayed");
            Assert.assertTrue(pinPage.isNewPinEmpty(), "New pin was not empty");
            Assert.assertTrue(pinPage.isRetypeNewPinEmpty(), "Retype new pin was not empty");
            Assert.assertTrue(pinPage.isCurrentPasswordEmpty(), "Current password was not empty");
        } else {
            Assert.assertTrue(pinPage.isMobilePopupDisplayed(), "Success message was not displayed");
        }
    }

}
