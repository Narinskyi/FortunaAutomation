package planA.Tests;

import planA.Preconditions.PreconditionalSteps;
import core.AbstractTest;
import core.PageFactory;
import pageObjects.AvailablePages;
import enums.Platform;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.planA.LoginPage;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import utils.DataProvider;

@Features("Guest")
@Stories("Login")
public class LoginTest extends AbstractTest {

    private static LoginPage loginPage = PageFactory.getPage(AvailablePages.login);

    private static final String INVALID_USERNAME = "invalid";
    private static final String INVALID_PASSWORD = "invalidPassword";

    @BeforeClass (alwaysRun = true)
    public void prepareUser(){
        PreconditionalSteps.prepareUser(userData);
    }

    @Test (groups = {"desktop", "tablet", "mobile"})
    public void uiElementsTest(){

        loginPage.open();
        loginPage.clickLogin();

        //verify header, placeholders and that remember me is unchecked by default
        if (DataProvider.getCurrentPlatform().equals(Platform.desktop)) {
            Assert.assertTrue(loginPage.getUsernamePlaceholder().contains("username/email"), "Username placeholder failed");
            Assert.assertTrue(loginPage.getPasswordPlaceholder().contains("password"), "Password placeholder failed");
        }
        Assert.assertTrue(loginPage.isUsernameInvalid(), "Username validation failed");
        Assert.assertTrue(loginPage.isPasswordInvalid(), "Password validation failed");
        Assert.assertTrue(!loginPage.isRememberMeChecked(), "Remember me unchecked by default failed");
    }


    @Test (groups = {"desktop", "tablet", "mobile"})
    public void passwordVisibilityIconTest(){

        loginPage.open();

        //verify that password is invisible by default
        loginPage.enterPassword(INVALID_PASSWORD);
        Assert.assertFalse(loginPage.isPasswordVisible(), "Password masking verification failed");

        //verify that password visibility function works
        loginPage.clickEyeIcon();
        Assert.assertTrue(loginPage.isPasswordVisible(), "Password visibility verification failed");

    }

    @Test(groups = {"desktop", "tablet", "mobile"})
    public void forgottenPasswordLinkTest(){

        loginPage.open();

        //verify forgotten password link
        loginPage.clickForgottenPassword();
        Assert.assertTrue(loginPage.isForgottenPasswordPageOpened(), "Forgotten password link verification failed");

    }

    @Test(groups = {"desktop", "tablet"})
    public void registrationLinkTest(){

        loginPage.open();

        //verify registration link
        loginPage.clickRegister();
        Assert.assertTrue(loginPage.isRegisterPageOpened(), "Register link verification failed");

    }


    @Test (groups = {"desktop", "tablet", "mobile"})
    public void emptyFieldsTest() {

        loginPage.open();

        //empty fields
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isUsernameInvalid(), "Username validation failed");
        Assert.assertTrue(loginPage.isPasswordInvalid(), "Password validation failed");

        //empty password
        loginPage.enterUsername(INVALID_USERNAME);
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isPasswordInvalid(), "Password validation failed");

        //empty username
        loginPage.clearUsername();
        loginPage.enterPassword(INVALID_PASSWORD);
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isUsernameInvalid(), "Username validation failed");

    }

    @Test (groups = {"desktop", "tablet", "mobile"})
    public void invalidBothFieldsTest(){

        loginPage.open();

        loginPage.enterUsername("invalid");
        loginPage.enterPassword(INVALID_PASSWORD);
        loginPage.clickLogin();

        if (!isMobile) {
            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message was not displayed");
        } else {
            Assert.assertTrue(loginPage.isMobilePopupDisplayed(), "Error message was not displayed");
        }
    }

    @Test (groups = {"desktop", "tablet", "mobile"})
    public void invalidPasswordTest() {

        loginPage.open();
        loginPage.enterUsername(userData.getUsername());
        loginPage.enterPassword(INVALID_PASSWORD);
        loginPage.clickLogin();

        if(!isMobile) {
            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message was not displayed");
        } else {
            Assert.assertTrue(loginPage.isMobilePopupDisplayed(), "Error message was not displayed");
        }

    }

    @Test (groups = {"desktop", "tablet", "mobile"})
    public void validInputTest() {

        loginPage.open();

        loginPage.enterUsername(userData.getUsername());
        loginPage.enterPassword(userData.getPassword());
        loginPage.clickLogin();
        loginPage.clickAcceptTC();

        //verify that user is logged-in successfully
        Assert.assertTrue(loginPage.isDashboardPageOpened());

        //after test-logout
        loginPage.quietLogout();
    }

    //disabled due to bug
    @Test (groups = {"desktop", "tablet", "mobile"}, enabled = false)
    public void rememberMeTest(){

        loginPage.open();

        loginPage.enterUsername(userData.getUsername());
        loginPage.enterPassword(userData.getPassword());
        loginPage.clickRememberMe();
        loginPage.clickLogin();
        //loginPage.clickAcceptTC();

        //verify that user is logged-in successfully (with remember me checked)
        Assert.assertTrue(loginPage.isDashboardPageOpened());

        loginPage.logout();

        Assert.assertTrue(loginPage.getUsernameInputText().equals(userData.getUsername()), "Username was not saved");
        Assert.assertTrue(loginPage.isRememberMeChecked(), "Remember did not remain checked");
    }
}
