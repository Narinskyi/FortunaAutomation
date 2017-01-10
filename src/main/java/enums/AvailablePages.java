package enums;

import pageObjects.*;

public enum AvailablePages {

    changePassword("change-password"),
    changePin("change-pin"),
    dashboard("dashboard"),
    deposit("deposit"),
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

            case changePassword: page = new ChangePasswordPage();
                break;
            case changePin: page = new ChangePinPage();
                break;
            case dashboard: page = new DashboardPage();
                break;
            case deposit: page = new DepositPage();
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
