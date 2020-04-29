package demo.web.ui;

import com.automation.framework.core.Reports.ExtentTestManager;
import com.automation.framework.core.datadriven.utils.DataProviderClass;
import com.automation.framework.core.web.ui.object.BaseTest;
import com.automation.test.libraries.web.ui.pageobjects.KmsContactPage;
import com.aventstack.extentreports.ExtentTest;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.HashMap;

public class Quy_UIDemo extends BaseTest {
    ExtentTest logger;
    private String baseURL;

    public Quy_UIDemo() {
        baseURL = "https://www.kms-technology.com/contact";
    }

    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void tc005_VerifyErrorMessage(HashMap<String, String> data) {
        logger = ExtentTestManager.startTest("tc005_VerifyErrorMessage");
        KmsContactPage KmsContactPage = new KmsContactPage(driver);
        KmsContactPage.navigateToURL(baseURL);
        KmsContactPage.getLastNameTxtField().sendKeys("Tran", false);
        KmsContactPage.getFirstNameTxtField().sendKeys("Quy", true);
        KmsContactPage.waitForPageLoad();
        KmsContactPage.scrollToTheEndOfPage();
        Assert.assertTrue(KmsContactPage.verifyErrorMessage(data.get("expected_error_message")));

    }

    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void tc007_VerifyErrorMessageFromJsonFiles(HashMap<String, String> data) {
        logger = ExtentTestManager.startTest("tc007_VerifyErrorMessageFromJsonFiles");
        KmsContactPage KmsContactPage = new KmsContactPage(driver);
        KmsContactPage.navigateToURL(baseURL);
        KmsContactPage.getLastNameTxtField().sendKeys(data.get("lastName"), false);
        KmsContactPage.getFirstNameTxtField().sendKeys(data.get("firstName"), true);
        KmsContactPage.waitForPageLoad();
        KmsContactPage.scrollToTheEndOfPage();
        Assert.assertTrue(KmsContactPage.verifyErrorMessage(data.get("expectedErrorMessage")));
    }

    @AfterMethod
    public void getResult(ITestResult result) {
        ExtentTestManager.getResult(result, logger, driver, true);
    }

}

