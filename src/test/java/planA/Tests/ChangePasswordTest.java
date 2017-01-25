package planA.Tests;

import planA.Preconditions.PreconditionalSteps;
import core.AbstractTest;
import core.PageFactory;
import pageObjects.AvailablePages;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.planA.ChangePasswordPage;
import ru.yandex.qatools.allure.annotations.*;

@Features("User")
@Stories("Change Password")
public class ChangePasswordTest extends AbstractTest {

    private ChangePasswordPage passwordPage = PageFactory.getPage(AvailablePages.changePassword);

    @BeforeClass(alwaysRun = true)
    public void prepareUserAndLogin(){
        PreconditionalSteps.prepareUserAndLogin(userData);
    }

    @Test (groups = {"desktop", "tablet", "mobile"})
    public void emptyFieldsTest(){

        passwordPage.open();

        //submit all empty fields
        passwordPage.clickSubmit();

        if (!isMobile) {
            Assert.assertTrue(passwordPage.areErrorMessagesDisplayed(3), "Error message was not displayed");
        }
        Assert.assertTrue(passwordPage.isCurrentPasswordInvalid(), "Current password validation failed");
        Assert.assertTrue(passwordPage.isNewPasswordInvalid(), "New password validation failed");
        Assert.assertTrue(passwordPage.isRetypeNewPasswordInvalid(), "Retype new password validation failed");

        System.out.println("=================End of all empty fields Test=================");


        //submit two empty fields
        passwordPage.fillCurrentPassword("password");
        passwordPage.clickSubmit();

        if (!isMobile) {
            Assert.assertTrue(passwordPage.areErrorMessagesDisplayed(2), "Error message was not displayed");
        }
        Assert.assertTrue(passwordPage.isCurrentPasswordValid(), "Current password validation failed");
        Assert.assertTrue(passwordPage.isNewPasswordInvalid(), "New password validation failed");
        Assert.assertTrue(passwordPage.isRetypeNewPasswordInvalid(), "Retype new password validation failed");

        System.out.println("=================End of two empty fields Test=================");

        //submit one empty field

        passwordPage.fillNewPassword("password");
        passwordPage.clickSubmit();

        if (!isMobile) {
            Assert.assertTrue(passwordPage.areErrorMessagesDisplayed(1), "Error message was not displayed");
        }
        Assert.assertTrue(passwordPage.isCurrentPasswordValid(), "Current password validation failed");
        Assert.assertTrue(passwordPage.isNewPasswordValid(), "New password validation failed");
        Assert.assertTrue(passwordPage.isRetypeNewPasswordInvalid(), "Retype new password validation failed");

        System.out.println("=================End of one empty field Test=================");
    }

    @Test (groups = {"desktop", "tablet", "mobile"})
    public void notMatchingFieldsTest(){

        passwordPage.open();

        passwordPage.fillCurrentPassword("password");
        passwordPage.fillRetypePassword("shouldMatch");
        passwordPage.fillNewPassword("butDoesNot");

        passwordPage.clickSubmit();

        if (!isMobile) {
            Assert.assertTrue(passwordPage.areErrorMessagesDisplayed(1), "Error message was not displayed");
        }
        Assert.assertTrue(passwordPage.isCurrentPasswordValid(), "Current password validation failed");
        Assert.assertTrue(passwordPage.isNewPasswordValid(), "New password validation failed");
        Assert.assertTrue(passwordPage.isRetypeNewPasswordInvalid(), "Retype new password validation failed");
    }

    @Test (groups = {"desktop", "tablet", "mobile"})
    public void currentPasswordIsInvalidTest(){

        passwordPage.open();

        passwordPage.fillCurrentPassword("invalid");
        passwordPage.fillNewPassword("password");
        passwordPage.fillRetypePassword("password");

        passwordPage.clickSubmit();

        if (!isMobile) {
            Assert.assertTrue(passwordPage.isErrorMessageDisplayed(), "Error message was not displayed");
            Assert.assertTrue(passwordPage.isCurrentPasswordValid(), "Current password validation failed");
            Assert.assertTrue(passwordPage.isNewPasswordValid(), "New password validation failed");
            Assert.assertTrue(passwordPage.isRetypeNewPasswordValid(), "Retype new password validation failed");
        } else {
            Assert.assertTrue(passwordPage.isMobilePopupDisplayed(), "Error message was not displayed");
        }
    }

    @Test (groups = {"desktop", "tablet", "mobile"})
    public void currentPasswordMatchesNewPasswordTest(){

        //verify the flow when new password is the same as current
        passwordPage.open();

        String password = userData.getPassword();

        passwordPage.fillCurrentPassword(password);
        passwordPage.fillNewPassword(password);
        passwordPage.fillRetypePassword(password);

        passwordPage.clickSubmit();

        if (!isMobile) {
            Assert.assertTrue(passwordPage.isErrorMessageDisplayed(), "Error message was not displayed");
        } else {
            Assert.assertTrue(passwordPage.isMobilePopupDisplayed(), "Error message was not displayed");
        }

    }

    @Test (groups = {"desktop", "tablet", "mobile"})
    public void validationRulesTest(){

        //validation rules: password should have from 5 to 10 symbols, allowed: A-Za-z0-9.@_#$'&+()*`;!?,-
        passwordPage.open();

        //invalid of normal length
        passwordPage.fillNewPassword("<^^>н т");
        passwordPage.clickInfo();

        if (!isMobile) {
            Assert.assertTrue(passwordPage.areErrorMessagesDisplayed(1), "Error message was not displayed");
        }
        Assert.assertTrue(passwordPage.isNewPasswordInvalid(), "New password validation failed");

        System.out.println("=================End of invalid password Test=================");

        //too short valid (4 symbols)
        passwordPage.fillNewPassword("AZaz");
        passwordPage.clickInfo();
        if (!isMobile) {
            Assert.assertTrue(passwordPage.areErrorMessagesDisplayed(1), "Error message was not displayed");
        }
        Assert.assertTrue(passwordPage.isNewPasswordInvalid(), "New password validation failed");

        System.out.println("=================End of too short valid password Test=================");

        //too long valid (11 symbols)
        passwordPage.fillNewPassword("&+()*`;!?,-");
        passwordPage.clickInfo();

        if (!isMobile) {
            Assert.assertTrue(passwordPage.areErrorMessagesDisplayed(1), "Error message was not displayed");
        }
        Assert.assertTrue(passwordPage.isNewPasswordInvalid(), "New password validation failed");

        System.out.println("=================End of too long valid password Test=================");

        //lower boundary valid (5 symbols)
        passwordPage.fillNewPassword("@_#'$");
        passwordPage.clickInfo();
        Assert.assertTrue(passwordPage.isNewPasswordValid(), "New password validation failed");

        System.out.println("=================End of lower boundary valid password Test=================");


        //higher boundary valid (10 symbols)
        passwordPage.fillNewPassword("A-Za-z0-9.");
        passwordPage.clickInfo();
        Assert.assertTrue(passwordPage.isNewPasswordValid(), "New password validation failed");

        System.out.println("=================End of top boundary valid password Test=================");

    }

    @Test (groups = {"desktop", "tablet", "mobile"})
    public void passwordVisibilityIconTest(){

        //verify that password becomes visible once icon is clicked
        passwordPage.open();

        passwordPage.fillCurrentPassword("password");
        passwordPage.clickCurrentPassVisiblityIcon();
        Assert.assertTrue(passwordPage.isCurrentPasswordVisible(), "Password visibility test failed");

    }

    //runs last since it changes the password
    @Test (groups = {"desktop", "tablet", "mobile"}, priority=1)
    public void positiveFlowTest(){

        String newPassword="Password2";

        passwordPage.open();

        //fill the fields with valid data
        passwordPage.fillCurrentPassword(userData.getPassword());
        //save new password to userdata
        userData.setPassword(newPassword);
        passwordPage.fillNewPassword(userData.getPassword());
        passwordPage.fillRetypePassword(userData.getPassword());

        passwordPage.clickSubmit();

        if (!isMobile) {
            Assert.assertTrue(passwordPage.areSuccessMessagesDisplayed(1), "Success message was not displayed");
            Assert.assertTrue(passwordPage.isCurrentPasswordValid(), "Current password validation failed");
            Assert.assertTrue(passwordPage.isNewPasswordValid(), "New password validation failed");
            Assert.assertTrue(passwordPage.isRetypeNewPasswordValid(), "Retype new password validation failed");
        } else {
            Assert.assertTrue(passwordPage.isMobilePopupDisplayed(), "Error message was not displayed");
        }

        System.out.println("=================End of change password page interactions=================");

        //try to logout and then login again with new password (it's saved in user data)
        passwordPage.logout();
        passwordPage.open();
        passwordPage.loginWithExisitingUser(userData);

        Assert.assertTrue(passwordPage.isDashboardPageOpened(), "Login with new password failed");

    }

    @Test(groups = {"desktop", "tablet", "mobile"}, priority = 2)
    public void changingToPreviouslyUsedPasswordTest(){

        //verify that it's impossible to use the password that was used before
        //!test implies that previous positive test was successful
        passwordPage.open();

        passwordPage.fillCurrentPassword(userData.getPassword());
        passwordPage.fillNewPassword("Password1");
        passwordPage.fillRetypePassword("Password1");

        passwordPage.clickSubmit();

        if (!isMobile) {
            Assert.assertTrue(passwordPage.isErrorMessageDisplayed(), "Error message was not displayed");
        } else {
            Assert.assertTrue(passwordPage.isMobilePopupDisplayed(), "Error message was not displayed");
        }
    }

}
