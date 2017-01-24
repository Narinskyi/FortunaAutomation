package planA.Preconditions;

import core.AbstractTest;
import pageObjects.planA.FortunaextdevPage;
import pageObjects.planA.LoginPage;
import pageObjects.planA.RegistrationPage;
import springConstructors.UserData;
import utils.Backend;
import utils.DataProvider;

public class PreconditionalSteps extends AbstractTest {

    private static FortunaextdevPage fortunaextdevPage = new FortunaextdevPage();
    private static RegistrationPage registrationPage = new RegistrationPage();
    private static LoginPage loginPage = new LoginPage();

    public void loginWithExisitingUser(UserData userData) {
        loginPage.login(userData);
    }

    public static void prepareUser(UserData userData) {
        generateUniqueUserdata(userData);
        registerThroughBackend(userData);
        loginToFortunaExtdev(userData);
    }

    public static void prepareUserAndLogin(UserData userData) {
        prepareUser(userData);
        loginPage.login(userData);
    }

    private static synchronized void generateUniqueUserdata(UserData userData){
        String random = DataProvider.getRandomUsername();
        userData.setUsername(random);
        userData.setEmail(random+"@gmail.com");
    }

    private static void registerThroughUI(UserData userData) {
        registrationPage.open();
        registrationPage.registerUser();
        System.out.println("User with: "+userData.getUsername()+" username was registered successfully");
    }

    private static synchronized void registerThroughBackend(UserData userData){
        Backend.createUser(userData);
    }

    private static void loginToFortunaExtdev(UserData userData){
        fortunaextdevPage.openFortunaextdev();
        fortunaextdevPage.loginWithDefaultCredentials(userData);
    }

    private static void acceptTC(){
        loginPage.clickAcceptTC();
    }

    private static void logout(){
        registrationPage.logout();
    }

    public void makeBets() {
        fortunaextdevPage.makeBets(80);
    }
}
