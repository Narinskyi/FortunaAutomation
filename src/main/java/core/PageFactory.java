package core;

import pageObjects.AvailablePages;
import pageObjects.planA.AbstractPage;
import pageObjects.planB.iFrames.Iframe;

public class PageFactory {

    @SuppressWarnings("unchecked")
    public static <T extends AbstractPage> T getPage(AvailablePages page) {
            return (T) page.getPage();
    }

}
