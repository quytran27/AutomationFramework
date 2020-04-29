package com.automation.framework.core.web.ui.object;

import com.automation.framework.core.Reports.ExtentTestManager;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.log4j.Logger;
import org.testng.Assert;


public class Wrapper {
    private static final Logger LOGGER = Logger.getLogger(Wrapper.class);

    public ExtentTest getExtentTest() {
        return ExtentTestManager.getTest();
    }

    public void info(String msg) {
        LOGGER.info(msg);
        if (this.getExtentTest() != null) {
            this.getExtentTest().log(Status.INFO, msg);
        }

    }

    public void pass(String msg) {
        LOGGER.info(String.format("[Pass]-%s", msg));
        this.getExtentTest().pass("<b style='color:green;'>" + msg + "");
    }

    public void fail(String msg) {
        LOGGER.error(msg);
        this.getExtentTest().fail("<b style='color:red;'>" + msg + "");
        Assert.fail(msg);
    }
}
