package pageObjects.planB;

import org.openqa.selenium.By;
import pageObjects.planA.AbstractPage;
import utils.Driver;

public abstract class AbstractCasinoPage extends AbstractPage {

    private static final By BUTTON_SUBMIT = By.cssSelector("button[type='submit']");

    public void clickSubmit(){
        Driver.click(BUTTON_SUBMIT);
    }

}
