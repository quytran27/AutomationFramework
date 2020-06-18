package demo.web.ui;

import com.automation.framework.core.web.ui.object.BaseTest;
import com.automation.test.libraries.web.ui.pageobjects.AddPeoplePage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Auto_Add_People extends BaseTest {

    private String baseURL;

    public Auto_Add_People() {
        baseURL = "https://tps-staging.tinyserver.info/invite";
    }

    @Test
    public void tc001_AddNewPeopleSuccessfully() throws InterruptedException {
        AddPeoplePage AddPeoplePage = new AddPeoplePage(driver);
        AddPeoplePage.navigateToURL(baseURL);
        AddPeoplePage.addPeople("Nam", "Nguyen", "abcd2@mail.com", "tran van a");
        AddPeoplePage.waitForPageLoad();
        Assert.assertTrue(AddPeoplePage.verifyAddPeopleStatus("Successfully"));
    }

    @Test
    public void tc002_AddNewPeopleWithExistingEmail() throws InterruptedException {
        AddPeoplePage AddPeoplePage = new AddPeoplePage(driver);
        AddPeoplePage.navigateToURL(baseURL);
        AddPeoplePage.addPeople("Nam", "Nguyen", "abc@mail.com", "tran van a");
        AddPeoplePage.waitForPageLoad();
        Assert.assertTrue(AddPeoplePage.verifyAddPeopleStatus("Existing Email"));
    }
}

