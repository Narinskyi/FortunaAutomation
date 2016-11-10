package pageObjects;

import architecture.Page;
import enums.AvailablePages;
import utils.DataProvider;
import utils.WebDriverUtils;

public abstract class AbstractPage implements Page {

    private String baseURL = DataProvider.getBaseUrl();

    public void open() {
        String url = baseURL + AvailablePages.getSuffix(this);
        WebDriverUtils.openPage(url);
    }

    public void refresh() {

    }

}
