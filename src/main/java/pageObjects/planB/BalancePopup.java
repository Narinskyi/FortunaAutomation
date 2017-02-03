package pageObjects.planB;

import org.openqa.selenium.By;
import utils.Driver;

/**
 * Created by tysa on 31.01.2017.
 */
public class BalancePopup extends AbstractCasinoPopup {

    private static final String DEPOSIT_BUTTON = ".popup-modal__button.btn_action_deposit";
    private static final String BALANCE = ".balance-calc-replacer";

    public boolean isDepositButtonPresent(){
        return Driver.isElementVisible(By.cssSelector(DEPOSIT_BUTTON));
    }

    public String getBalance(){
        return Driver.getElementText(By.cssSelector(BALANCE));
    }
}
