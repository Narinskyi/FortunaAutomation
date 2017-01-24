package pageObjects;

import pageObjects.planA.*;
import pageObjects.planB.HomePage;

public enum AvailablePages {

    /*-------- Plan B pages --------*/
    home("home"),

    /*-------- Plan A pages --------*/
    changePassword("change-password"),
    changePin("change-pin"),
    contactUsGuest("contact-us"),
    contactUsUser("contact-us-user"),
    dashboard("dashboard"),
    deposit("deposit"),
    favouriteSports("favourite-sports"),
    forgottenPassword("forgotten-password"),
    inbox("inbox"),
    liveBetslipHistory("live-betslip-history"),
    login("login"),
    logout("logout"),
    notifications("notifications"),
    personalSettings("personal-settings"),
    prematchBetslipHistory("prematch-betslip-history"),
    quickBet("quick-bet"),
    register("register-step-1"),
    withdraw("withdraw");


    private final String suffix;
    private AbstractPage page;

    AvailablePages (String suffix) {
        this.suffix = suffix;
    }

    public AbstractPage getPage(){

        switch (this){

            /*-------- Plan B pages --------*/
            case home: page = new HomePage();
                break;

            /*-------- Plan A pages --------*/
            case changePassword: page = new ChangePasswordPage();
                break;
            case changePin: page = new ChangePinPage();
                break;
            case contactUsGuest: page = new ContactUsGuestPage();
                break;
            case contactUsUser: page = new ContactUsUserPage();
                break;
            case dashboard: page = new DashboardPage();
                break;
            case deposit: page = new DepositPage();
                break;
            case favouriteSports: page = new FavouriteSportsPage();
                break;
            case forgottenPassword: page = new ForgottenPasswordPage();
                break;
            case inbox: page = new InboxPage();
                break;
            case liveBetslipHistory: page = new LiveBetslipHistoryPage();
                break;
            case login: page = new LoginPage();
                break;
            case logout: page = new LogoutPage();
                break;
            case notifications: page = new NotificationsPage();
                break;
            case personalSettings: page = new PersonalSettingsPage();
                break;
            case prematchBetslipHistory: page = new PrematchBetslipHistoryPage();
                break;
            case quickBet: page = new QuickBetPage();
                break;
            case register: page = new RegistrationPage();
                break;
            case withdraw: page = new WithdrawPage();
                break;
        }
        return this.page;
    }

    public String getSuffix () {
        return this.suffix;
    }

    //get suffix - iterate through enum
    public static String getSuffix(AbstractPage soughtPage) {
        for (AvailablePages pages : AvailablePages.values()){
            if (pages.getPage().getClass().equals(soughtPage.getClass()))
                return pages.suffix;
        }
        return "/";
    }

}
