package demo.web.ui;

import com.automation.framework.core.datadriven.utils.DataProviderClass;
import com.automation.framework.core.web.ui.object.BaseTest;
import com.automation.test.libraries.web.ui.pageobjects.GoogleSearchPage;
import com.automation.test.libraries.web.ui.pageobjects.IMDBSearchPage;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;


public class IMDBSearch extends BaseTest {
    private static final Logger LOGGER = Logger.getLogger(IMDBSearch.class);

    private String baseURL;

    public IMDBSearch() {
        baseURL = "http://www.google.com";
    }

    @Test
    public void tc001_VerifyGoogleSearchPageWithoutDataDriven() {
        IMDBSearchPage imdbSearchPage = new IMDBSearchPage(driver);

        imdbSearchPage.navigateToURL(baseURL);

        Assert.assertTrue(imdbSearchPage.isPageTitleContains("Google"));

        imdbSearchPage.inputSearchTextAndSubmit("No data driven automation");
    }

    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void tc002_VerifyGoogleSearchPageWithData(HashMap<String, String> data) {
        GoogleSearchPage googleSearchPage = new GoogleSearchPage(driver);

        googleSearchPage.navigateToURL(baseURL);

        Assert.assertTrue(googleSearchPage.isPageTitleContains("Google"));

        googleSearchPage.inputSearchTextAndSubmit(data.get("search_text"));
    }
}
