package pageObjects.planA;

import org.openqa.selenium.By;
import utils.Driver;

import java.security.SecureRandom;

public class ContactUsGuestPage extends AbstractMyAccountPage {
    
    private static final By INPUT_NAME = By.name("firstname");
    private static final By INPUT_SURNAME = By.name("lastname");
    private static final By INPUT_USERNAME = By.name("username");
    private static final By INPUT_EMAIL = By.name("email");
    private static final By INPUT_EMAIL_VALIDATION = By.cssSelector("div.fn-field-email");
    private static final By DROPDOWN_TOPIC = By.name("choose-topic");
    private static final By INPUT_CLIENT_NUMBER = By.name("client-number");
    private static final By DROPDOWN_VALIDATION = By.cssSelector("div.select");
    private static final By INPUT_TICKET = By.name("ticket-no");
    private static final By INPUT_TICKET_VALIDATION = By.xpath("//div[@data-validation-type='ticketNo']");
    private static final By TEXT_AREA = By.name("message-area");
    private static final By CONTACT_FORM = By.xpath("(//ul[1]/li[1])[2]");

    public void clickContactForm() {
        Driver.click(CONTACT_FORM);
    }

    public void fillName(String input) {
        Driver.clearAndInputTextToField(INPUT_NAME,input);
    }

    public void fillSurname(String input) {
        Driver.clearAndInputTextToField(INPUT_SURNAME,input);
    }

    public void fillUsername(String input) {
        Driver.clearAndInputTextToField(INPUT_USERNAME,input);
    }

    public void fillEmail(String input) {
        Driver.clearAndInputTextToField(INPUT_EMAIL,input);
    }

    public void selectDropdownOption(String option) {
        Driver.setDropdownOptionByValue(DROPDOWN_TOPIC, option);
    }

    public void fillClientNumber(String input) {
        Driver.clearAndInputTextToField(INPUT_CLIENT_NUMBER,input);
    }

    public void fillTicketNumber(String input) {
        Driver.clearAndInputTextToField(INPUT_TICKET,input);
    }

    public void fillTextArea(String input) {
        Driver.inputTextToField(TEXT_AREA,input);
    }

    public boolean areAllFieldsCleared(){
        return Driver.getElementText(INPUT_NAME).isEmpty()&&
                Driver.getElementText(INPUT_SURNAME).isEmpty()&&
                Driver.getElementText(INPUT_USERNAME).isEmpty()&&
                Driver.getElementText(INPUT_EMAIL).isEmpty()&&
                Driver.getElementText(INPUT_CLIENT_NUMBER).isEmpty()&&
                Driver.getElementText(TEXT_AREA).isEmpty()&&
                Driver.getSelectedOption(DROPDOWN_TOPIC).equals("Please choose a topic *");
    }

    public String getTextAreaContents(){
        return Driver.getAttribute(TEXT_AREA,"value");
    }

    public boolean isEmailInvalid(){
        return isFieldInvalid(INPUT_EMAIL_VALIDATION);
    }

    public boolean isDropdownInvalid(){
        return isFieldInvalid(DROPDOWN_VALIDATION);
    }

    public boolean isTicketNumberInvalid(){
        return super.isFieldInvalid(INPUT_TICKET_VALIDATION);
    }

    public boolean isTextAreaInvalid(){
        return Driver.getCssValue(TEXT_AREA,"border-bottom-color").equals("rgba(170, 0, 0, 1)");
    }

    public String getRandomInput(int length){

        //generate random string of given length
        final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();

        StringBuilder sb = new StringBuilder(length);
        for( int i = 0; i < length; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();

    }

}
