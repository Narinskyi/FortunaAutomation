package pageObjects.planA;

import org.openqa.selenium.By;
import utils.Driver;

import java.security.SecureRandom;

public class ContactUsUserPage extends AbstractMyAccountPage {

    private static final By DROPDOWN_TOPIC = By.name("choose-topic");
    private static final By DROPDOWN_VALIDATION = By.cssSelector("div.select");
    private static final By INPUT_TICKET = By.name("ticket-no");
    private static final By INPUT_TICKET_VALIDATION = By.xpath("//div[@data-validation-type='ticketNo']");
    private static final By TEXT_AREA = By.name("message-area");

    public void selectDropdownOption(String option) {
        Driver.click(DROPDOWN_VALIDATION);
        Driver.setDropdownOptionByValue(DROPDOWN_TOPIC, option);
    }

    public void fillTicketNumber(String input) {
        Driver.inputTextToField(INPUT_TICKET,input);
    }

    public void fillTextArea(String input) {
        Driver.click(TEXT_AREA);
        Driver.inputTextToField(TEXT_AREA,input);
        Driver.click(TEXT_AREA);
    }

    public String getTextAreaContents(){
        return Driver.getAttribute(TEXT_AREA,"value");
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

    public boolean areAllFieldsCleared(){
        return  Driver.getElementText(TEXT_AREA).isEmpty()&&
                Driver.getSelectedOption(DROPDOWN_TOPIC).equals("Please choose a topic *");
    }
}
