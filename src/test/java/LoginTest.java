import architecture.PageFactory;
import enums.AvailablePages;
import org.junit.Test;
import pageObjects.LoginPage;
import utils.AbstractTest;

public class LoginTest extends AbstractTest{

    @Test
    public void loginTest(){
        LoginPage page = PageFactory.getPage(AvailablePages.login);
        page.open();
    }
}
