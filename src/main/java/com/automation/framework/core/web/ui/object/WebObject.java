package com.automation.framework.core.web.ui.object;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;

import java.util.Iterator;
import java.util.List;

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

    public String selectByPartialText(String partialText) {
        String itemText = null;
        List<WebElement> options = webElement.findElements(By.xpath("//h4[contains(text(),'" + partialText + "')]"));
        for (Iterator var4 = options.iterator(); var4.hasNext(); ) {
            WebElement option = (WebElement) var4.next();
            itemText = option.getText();
            option.click();
            baseObj.info("'" + partialText + "' is selected");
        }
        return itemText;
    }


}


