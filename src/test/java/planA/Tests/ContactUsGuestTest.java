package planA.Tests;

import core.AbstractTest;
import core.PageFactory;
import pageObjects.AvailablePages;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.planA.ContactUsGuestPage;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

@Features("Guest")
@Stories("Contact Us")
public class ContactUsGuestTest extends AbstractTest {

    private ContactUsGuestPage contactUsPage = PageFactory.getPage(AvailablePages.contactUsGuest);

    @Test(groups = {"desktop", "tablet", "mobile"})
    public void validInputVerification(){

        contactUsPage.open();
        if (isMobile){
            contactUsPage.clickContactForm();
        }
        contactUsPage.fillName("Stan");
        contactUsPage.fillSurname("Smith");
        contactUsPage.fillUsername("stansmith");
        contactUsPage.fillEmail("test@test.com");
        contactUsPage.selectDropdownOption("registration");
        contactUsPage.fillClientNumber("12345");
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
            Assert.assertTrue(contactUsPage.isEmailInvalid(), "Email validation failed");
            Assert.assertTrue(contactUsPage.isDropdownInvalid(), "Dropdown validation failed");
            Assert.assertTrue(contactUsPage.isTextAreaInvalid(), "Text area validation failed");

            contactUsPage.fillEmail("test@test.com");
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
            contactUsPage.clickContactForm();
            contactUsPage.clickSubmit();
            Assert.assertTrue(contactUsPage.isMobilePopupDisplayed(), "Success message was not displayed");
        }
    }

    @Test
    public void invalidEmailVerification() {

        //rules for email: from 6 to 100 EN characters and only A-Z a-z 0-9 @.-_ symbols
        contactUsPage.open();

        if (isMobile){
            contactUsPage.clickContactForm();
        }

        //generally invalid email
        contactUsPage.fillEmail("1nval!dEm@iL");
        contactUsPage.selectDropdownOption("registration");
        contactUsPage.fillTextArea("Hello");
        contactUsPage.clickSubmit();
        Assert.assertTrue(contactUsPage.isEmailInvalid(), "Invalid email test failed");

        //email is too short
        contactUsPage.fillEmail("a@a.a");
        contactUsPage.clickSubmit();
        Assert.assertTrue(contactUsPage.isEmailInvalid(), "Too short email test failed");

        //email is too long (101 symbols)
        contactUsPage.fillEmail("itsVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryLong@email.com");
        contactUsPage.clickSubmit();
        Assert.assertTrue(contactUsPage.isEmailInvalid(), "Too long email test failed");

        //valid short email (6 symbols)
        contactUsPage.fillEmail("A@z.co");
        contactUsPage.clickSubmit();
        if (!isMobile) {
            Assert.assertTrue(contactUsPage.areSuccessMessagesDisplayed(1), "Success message for valid email was not displayed");
        } else {
            Assert.assertTrue(contactUsPage.isMobilePopupDisplayed(), "Success message was not displayed");
        }

        //valid long email (100 symbols)
        contactUsPage.fillEmail("Reasonably_Long_Email-meantToVerifyTheUpperBoundaryValueThatAcceptsUpTo100Symbols.Unlikely@email.com");
        contactUsPage.selectDropdownOption("registration");
        contactUsPage.fillTextArea("Message");
        contactUsPage.clickSubmit();
        if (!isMobile) {
            Assert.assertTrue(contactUsPage.areSuccessMessagesDisplayed(1), "Success message for valid email was not displayed");
        } else {
            Assert.assertTrue(contactUsPage.isMobilePopupDisplayed(), "Success message was not displayed");
        }
    }

    @Test (groups = {"desktop", "tablet", "mobile"})
    public void textAreaCapacityVerification(){

        //text area should accept no more than 2000 symbols
        contactUsPage.open();
        if (isMobile){
            contactUsPage.clickContactForm();
        }

        String tooManySymbols = contactUsPage.getRandomInput(2001);
        contactUsPage.fillTextArea(tooManySymbols);

        //verify that text area trims extra symbol leaving only 2000
        Assert.assertTrue(contactUsPage.getTextAreaContents().length()==2000, "Text area capacity test failed");
    }

}
