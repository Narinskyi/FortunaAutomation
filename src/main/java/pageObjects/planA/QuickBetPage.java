package pageObjects.planA;


import org.openqa.selenium.By;
import utils.Driver;

public class QuickBetPage extends AbstractMyAccountPage {

    private static final By INPUT_AMOUNT_1 = By.name("bet1");
    private static final By INPUT_AMOUNT_2 = By.name("bet2");
    private static final By VALIDATION_AMOUNT_2 = By.cssSelector(".field_name_bet2");
    private static final By INPUT_AMOUNT_3 = By.name("bet3");
    private static final By VALIDATION_AMOUNT_3 = By.cssSelector(".field_name_bet3");
    private static final By QICK_BET_CURRENCY = By.xpath("(//span[@class='quickbet-currency'])[1]");
    private static final By INFO_AREA = By.cssSelector("div.fn-wc-container");

    public void fillQuickbet1(String input){
        Driver.clearAndInputTextToField(INPUT_AMOUNT_1,input);
    }

    public void fillQuickbet2(String input){
        Driver.clearAndInputTextToField(INPUT_AMOUNT_2,input);
    }

    public void fillQuickbet3(String input){
        Driver.clearAndInputTextToField(INPUT_AMOUNT_3,input);
    }

    public boolean isQuickbet1(String text){
        return Driver.isElementText(INPUT_AMOUNT_1, text);
    }

    public boolean isQuickbet2(String text){
        return Driver.isElementText(INPUT_AMOUNT_2, text);
    }

    public boolean isQuickbet3(String text){
        return Driver.isElementText(INPUT_AMOUNT_3, text);
    }

    public String getCurrency(){
        return Driver.getElementText(QICK_BET_CURRENCY);
    }

    public void clickInfo(){
        Driver.click(INFO_AREA);
    }

    public boolean isInputRightAligned(){
        return Driver.getCssValue(INPUT_AMOUNT_1,"text-align")
                .equals("right");
    }

    public boolean isQuickbet2Invalid(){
        return isFieldInvalid(VALIDATION_AMOUNT_2);
    }

    public boolean isQuickbet3Invalid(){
        return isFieldInvalid(VALIDATION_AMOUNT_3);
    }

    public boolean areFieldsEmpty(){
        return Driver.getElementText(INPUT_AMOUNT_1).isEmpty()&&
                Driver.getElementText(INPUT_AMOUNT_2).isEmpty()&&
                Driver.getElementText(INPUT_AMOUNT_3).isEmpty();
    }

}
