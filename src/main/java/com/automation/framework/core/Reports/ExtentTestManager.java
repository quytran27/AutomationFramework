package com.automation.framework.core.Reports;

import com.aspose.pdf.PageSize;
import com.automation.framework.core.Utility;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



/**
 * At startTest() method, an instance of ExtentTest created and put into extentTestMap with current thread id.
 * At getTest() method, return ExtentTest instance in extentTestMap by using current thread id.
 */
public class ExtentTestManager {
    static Map extentTestMap = new HashMap();
    static ExtentReports extent = ExtentReportManager.getReporter();
    //static String filePath = System.getProperty("user.dir")+ExtentReportManager.filePath.replace(".","").replace("html",".html").replace("/","\\");

    public static synchronized ExtentTest startTest(String tesCaseName) {
        ExtentTest test = extent.createTest(tesCaseName);
        extentTestMap.put((int) (Thread.currentThread().getId()), test);
        return test;
    }

    public static synchronized ExtentTest getTest() {
        return (ExtentTest) extentTestMap.get((int) (Thread.currentThread().getId()));
    }

    public static void getResult(ITestResult result, ExtentTest logger) {
        if (result.getStatus() == ITestResult.FAILURE) {
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED ", ExtentColor.RED));
            logger.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED ", ExtentColor.GREEN));
        } else {
            logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED ", ExtentColor.ORANGE));
            logger.skip(result.getThrowable());
        }
    }
    public static void getResult(ITestResult result, ExtentTest logger,  WebDriver driver, Boolean isScreenshot) {
        if (result.getStatus() == ITestResult.FAILURE) {
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED ", ExtentColor.RED));
            logger.fail(result.getThrowable());
            if (isScreenshot){
            String temp = Utility.getScreenshot(driver);
            try {
                logger.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
            } catch (IOException e) {
                e.printStackTrace();
            }
            }
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED ", ExtentColor.GREEN));
        } else {
            logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED ", ExtentColor.ORANGE));
            logger.skip(result.getThrowable());
        }
    }




}
