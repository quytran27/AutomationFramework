package com.automation.framework.core.Reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



/** ExtentReport extent instance is created here. */
public class ExtentReportManager {
    private static ExtentReports extent;
    public static String filePath;

    public static synchronized ExtentReports getReporter() {
        if (extent == null) {
            ExtentHtmlReporter reporter;
            filePath = "./Reports/ReportExample" + System.currentTimeMillis() + ".html";
            reporter = new ExtentHtmlReporter(filePath);

            reporter.config().setDocumentTitle("Group 1");
            reporter.config().setTheme(Theme.DARK);
            reporter.config().setReportName("Build 1.0.0");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
        return extent;
    }


}
