package com.automation.framework.core.web.ui.object;

import com.automation.framework.core.Reports.ExtentReportManager;
import com.automation.framework.core.Reports.ExtentTestManager;
import com.automation.framework.core.base.OrgBaseTest;
import com.aventstack.extentreports.ExtentTest;
import com.lowagie.text.DocumentException;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.*;

public class BaseTest extends OrgBaseTest {
    private static final Logger LOGGER = Logger.getLogger(BaseTest.class);
    ExtentTest logger;
    protected WebDriver driver;

    @BeforeTest
    public void runBeforeTest() {
        System.setProperty("webdriver.chrome.driver",
                System.getProperty("user.dir") + File.separator + "WebDriver\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("window-size=1200x600");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @AfterTest
    public void runAfterTest()  {
        closeBrowser();
        ExtentReportManager.getReporter().flush();
    }

    public void closeBrowser() {
        try {
            driver.quit();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }


    @BeforeMethod
    public void runBeforeMethod() {
        logger = ExtentTestManager.startTest("tc005_VerifyErrorMessage");
    }
    @AfterMethod
    public void getResult(ITestResult result) {
        ExtentTestManager.getResult(result, logger, driver, true);
    }

}
