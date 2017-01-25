package pageObjects.planA;

import org.openqa.selenium.By;
import utils.Driver;

public class NotificationsPage extends AbstractMyAccountPage {

    private static final By [] CHECKBOXES = {
            By.name("betslipEmail"),
            By.name("betslipPush"),
            By.name("email"),
            By.name("SMS")
    };

    public void checkAllCheckboxes() {
        for (By locator: CHECKBOXES) {
            if (!Driver.isCheckboxChecked(locator)) {
               Driver.clickJS(locator);
            }
        }
    }

    public void uncheckAllCheckboxes() {
        for (By locator: CHECKBOXES) {
            if (Driver.isCheckboxChecked(locator)) {
                Driver.clickJS(locator);
            }
        }
    }

}
