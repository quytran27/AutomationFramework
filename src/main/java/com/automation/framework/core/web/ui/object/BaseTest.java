package com.automation.framework.core.web.ui.object;

import com.automation.framework.core.base.OrgBaseTest;
import com.automation.test.libraries.web.ui.pageobjects.TinyPulseLoginPage;
import com.aventstack.extentreports.ExtentTest;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;

public class BaseTest extends OrgBaseTest {
    private static final Logger LOGGER = Logger.getLogger(BaseTest.class);
    protected WebDriver driver;

    @BeforeTest
    public void runBeforeTest() {
        System.setProperty("webdriver.chrome.driver",
                System.getProperty("user.dir") + File.separator + "WebDriver\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("window-size=1200x600");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        /**
         * Login with valid credential
         */
        TinyPulseLoginPage TinyPulseLoginPage = new TinyPulseLoginPage(driver);
        TinyPulseLoginPage.navigateToURL("https://staging.tinyserver.info/signin");
        TinyPulseLoginPage.getUsernameTxtField().sendKeys("minhquy27910@gmail.com", false);
        TinyPulseLoginPage.getPasswordTxtField().sendKeys("1234qwerASDF", true);
        TinyPulseLoginPage.waitForPageLoad();
    }

    @AfterTest
    public void runAfterTest() {
        closeBrowser();
    }

    public void closeBrowser() {
        try {
            driver.quit();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }


//    @AfterMethod
//    public void getResult(ITestResult result) {
//        ExtentTestManager.getResult(result, logger, driver, true);
//    }

}
