package pageObjects.planA;

import pageObjects.AvailablePages;
import org.openqa.selenium.By;
import utils.Driver;

public class DashboardPage extends AbstractMyAccountPage {

    private static final By ACCOUNT_INFO = By.cssSelector("div.myaccount_dashboard");
    private static final By NAME = By.cssSelector(".user-info-replacer:nth-of-type(1)");
    private static final By SURNAME = By.cssSelector(".user-info-replacer:nth-of-type(2)");
    private static final By USERNAME = By.cssSelector(".user-info-replacer:nth-of-type(3)");
    private static final By BALANCE = By.cssSelector(".val_type_user_balance");
    private static final By BUTTON_MESSAGES = By.cssSelector(".myaccount__account-button-unread-messages");
    private static final By UNREAD_MESSAGES_COUNTER = By.cssSelector("span.inbox-counter");
    private static final By BUTTON_DEPOSIT = By.cssSelector(".myaccount__account-button-deposit");
    private static final By BUTTON_WITHDRAW = By.cssSelector(".myaccount__account-button-widthdraw");
    private static final By BUTTON_EDIT_PROFILE = By.cssSelector(".myaccount__account-button-edit-profile");
    private static final By BUTTON_SHOW_PREMATCH = By.cssSelector(".myaccount__goto-prematch-bets");
    private static final By BUTTON_SHOW_LIVE = By.cssSelector(".myaccount__goto-live-bets");
    private static final By MESSAGE_TABLE_EMPTY = By.cssSelector(".info-list__message-empty");

    public String getName(){
        return Driver.getElementText(NAME);
    }

    public String getSurname(){
        return Driver.getElementText(SURNAME);
    }

    public String getUsername(){
        return Driver.getElementText(USERNAME);
    }

    public String getBalance(){
        return Driver.getElementText(BALANCE);
    }

    public int getMessagesCounter(){
        return Integer.valueOf(Driver.getElementText(UNREAD_MESSAGES_COUNTER));
    }

    public String getMessagesButtonLabel() {
        return Driver.getElementText(BUTTON_MESSAGES);
    }

    boolean isAccountInfoDisplayed(){
        return Driver.isElementVisible(ACCOUNT_INFO);
    }

    public boolean isMessagesLinkCorrect(AvailablePages page) {
        return Driver.getAttribute(BUTTON_MESSAGES, "data-url").contains(page.getSuffix());
    }

    public boolean isDepositLinkCorrect(AvailablePages page) {
        return Driver.getAttribute(BUTTON_DEPOSIT, "data-url").contains(page.getSuffix());
    }

    public boolean isWithdrawLinkCorrect(AvailablePages page) {
        return Driver.getAttribute(BUTTON_WITHDRAW, "data-url").contains(page.getSuffix());
    }

    public boolean isEditProfileLinkCorrect(AvailablePages page) {
        return Driver.getAttribute(BUTTON_EDIT_PROFILE, "data-url").contains(page.getSuffix());
    }

    public boolean isShowPrematchLinkCorrect(AvailablePages page) {
        return Driver.getAttribute(BUTTON_SHOW_PREMATCH, "data-url").contains(page.getSuffix());
    }

    public boolean isShowLiveLinkCorrect(AvailablePages page) {
        return Driver.getAttribute(BUTTON_SHOW_LIVE, "data-url").contains(page.getSuffix());
    }

    public boolean areTablesEmpty(){
        return Driver.areSeveralElementsVisible(MESSAGE_TABLE_EMPTY, 2);
    }

}
