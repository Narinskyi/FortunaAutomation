package pageObjects.planB;

import org.openqa.selenium.By;
import utils.Driver;

/**
 * Created by tysa on 31.01.2017.
 */
public class BalancePopup extends AbstractCasinoPopup {

    private static final String DEPOSIT_BUTTON = ".popup-modal__button.btn_action_deposit";
    private static final By BALANCE_MAIN = By.cssSelector(".balance-calc-replacer");
    private static final By BALANCE_BONUS = By.cssSelector(".total-bonus-balance-replacer");


    public boolean isDepositButtonPresent(){
        return Driver.isElementVisible(By.cssSelector(DEPOSIT_BUTTON));
    }

    public double getMainBalance(){
        String[] s = Driver.getElementText(BALANCE_MAIN).split(" ");
        s[s.length - 1] = "";
        String result = "";
        for (String el : s){
            result += el;
        }
        return Double.parseDouble(result);
    }

    public double getBonusBalance(){
        String[] s = Driver.getElementText(BALANCE_BONUS).split(" ");
        s[s.length - 1] = "";
        String result = "";
        for (String el : s){
            result += el;
        }
        return Double.parseDouble(result);
    }

    public double getSumBalance(){
        return getMainBalance() + getBonusBalance();
    }
}
