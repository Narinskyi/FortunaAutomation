package planA.Tests;

import core.AbstractTest;
import core.PageFactory;
import pageObjects.AvailablePages;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.planA.ContactUsUserPage;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

@Features("User")
@Stories("Contact Us")
public class ContactUsUserTest extends AbstractTest {
   
    private ContactUsUserPage contactUsPage = PageFactory.getPage(AvailablePages.contactUsUser);

    @BeforeClass(alwaysRun = true)
    public void loginWithExisitingUser (){
        contactUsPage.loginWithExisitingUser(userData);
    }
   
    @Test (groups = {"desktop", "tablet", "mobile"})
    public void validInputVerification(){

        contactUsPage.open();
        contactUsPage.selectDropdownOption("registration");
        contactUsPage.fillTextArea("Hello");
        contactUsPage.clickSubmit();

        if (!isMobile) {
            Assert.assertTrue(contactUsPage.areSuccessMessagesDisplayed(1), "Success message was not displayed");
            Assert.assertTrue(contactUsPage.areAllFieldsCleared(), "Not all fields were cleared");
        } else {
            Assert.assertTrue(contactUsPage.isMobilePopupDisplayed(), "Success message was not displayed");
        }
    }

    @Test (groups = {"desktop", "tablet", "mobile"})
    public void emptyFieldsVerification() {

        contactUsPage.open();

        //empty fields submission

        if (!isMobile) {
            contactUsPage.clickSubmit();
            Assert.assertTrue(contactUsPage.isDropdownInvalid(), "Dropdown validation failed");
            Assert.assertTrue(contactUsPage.isTextAreaInvalid(), "Text area validation failed");

            contactUsPage.selectDropdownOption("registration");
            contactUsPage.clickSubmit();
            Assert.assertTrue(contactUsPage.isTextAreaInvalid(), "Text area validation failed");

            //verify additional fields
            contactUsPage.selectDropdownOption("betslipComplaint");
            contactUsPage.clickSubmit();
            Assert.assertTrue(contactUsPage.isTicketNumberInvalid(), "Ticket number validation failed");
            Assert.assertTrue(contactUsPage.isTextAreaInvalid(), "Text area validation failed");

            //fill ticket and again text area is invalid
            contactUsPage.fillTicketNumber("12345");
            contactUsPage.clickSubmit();
            Assert.assertTrue(contactUsPage.isTextAreaInvalid(), "Text area validation failed");

            contactUsPage.fillTextArea("Hi");
            contactUsPage.clickSubmit();

            //now all fields are filled
            Assert.assertTrue(contactUsPage.areSuccessMessagesDisplayed(1), "Success message was not displayed");
        } else {
            contactUsPage.clickSubmit();
            Assert.assertTrue(contactUsPage.isMobilePopupDisplayed(), "Success message was not displayed");
        }
    }

    @Test (groups = {"desktop", "tablet", "mobile"})
    public void textAreaCapacityVerification(){

        //text area should accept no more than 2000 symbols
        contactUsPage.open();

        String tooManySymbols = contactUsPage.getRandomInput(2001);
        contactUsPage.fillTextArea(tooManySymbols);

        //verify that text area trims extra symbol leaving only 2000
        Assert.assertTrue(contactUsPage.getTextAreaContents().length()==2000, "Text area capacity test failed");
    }
}
