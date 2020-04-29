package com.automation.framework.core;


import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion;
import jdk.net.SocketFlow;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;

public class Utility {

    public static String getScreenshot(WebDriver driver) {
        TakesScreenshot ts = (TakesScreenshot) driver;

        File src = ts.getScreenshotAs(OutputType.FILE);

        String path = System.getProperty("user.dir") + "/Screenshot/" + System.currentTimeMillis() + ".png";

        File destination = new File(path);

        try {
            FileUtils.copyFile(src, destination);
        } catch (IOException e) {
            System.out.println("Capture Failed " + e.getMessage());
        }
        return path;
    }

}
