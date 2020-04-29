package com.automation.framework.core.web.ui.object;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static java.lang.Thread.sleep;

public class WebObject {
    Wrapper baseObj;
    private WebElement webElement;

    public WebObject(WebElement webElement) {
        this.webElement = webElement;
        baseObj = new Wrapper();

    }

    public String getText() {
        return webElement.getText();
    }

    public String getValue() {
        return webElement.getAttribute("value");
    }

    public void click() throws InterruptedException {
        try {
            webElement.click();
        } catch (ElementClickInterceptedException e) {
            sleep(2000);
            webElement.click();
        }
    }

    public void sendKeys(String inputText, boolean submit) throws IllegalArgumentException {
        webElement.clear();
        try {
            webElement.sendKeys(inputText);
            baseObj.info("'" + inputText + "' is inputted");
        } catch (IllegalArgumentException e) {
            baseObj.info("Input data can not be blank");
        }
        if (submit) {
            webElement.submit();
            baseObj.info("Test data is submitted");
        }
    }
}


