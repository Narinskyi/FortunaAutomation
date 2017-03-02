package pageObjects.planB.iFrames;

import org.openqa.selenium.By;
import utils.Driver;

public class GameIframe implements Iframe {

    private static final By GAME_IFRAME = By.cssSelector(".fn-iframe-wrapper iframe");

    private GameIframe(){}

    @Override
    public void in() {
        Driver.switchToFrame(GAME_IFRAME);
    }

    public boolean isLaunchedInRealMode(){
        boolean result = Driver.getAttribute(GAME_IFRAME,"src")
                .contains("preferedmode=real");
        return result;
    }

    public boolean isLaunchedInDemoMode(){
        boolean result = Driver.getAttribute(GAME_IFRAME,"src")
                .contains("preferedmode=offline");
        return result;
    }

    public static GameIframe get() {
        return new GameIframe();
    }
}
