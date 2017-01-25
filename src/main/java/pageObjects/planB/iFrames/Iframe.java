package pageObjects.planB.iFrames;

import utils.Driver;

public interface Iframe {

    void in();

    default void out() {
        Driver.switchToDefaultContent();
    }

}
