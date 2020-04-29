package com.automation.framework.core.web.ui.object;

import com.aventstack.extentreports.Status;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


enum SearchBy {
    ID,
    XPath,
    Name
}

public abstract class BasePage {
    private static final Logger LOGGER = Logger.getLogger(BasePage.class);
    private WebDriver driver;
    protected Wrapper baseObj;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        baseObj = new Wrapper();
    }

    public WebDriverWait getWaitObject() {
        return new WebDriverWait(this.driver, 60);
    }

    public WebObject findWebElement(String locator) {
        SearchBy searchType = SearchBy.ID;
        String searchStr = locator;
        WebObject findObject = null;

        if (locator.startsWith("//")) {
            searchType = SearchBy.XPath;
        } else {
            int idx = locator.indexOf('=');

            if (idx > 0) {
                String locatorHead = locator.substring(0, idx);
                SearchBy lookingUpSearchBy = null;

                for (SearchBy s : SearchBy.values()) {
                    if (s.name().equalsIgnoreCase(locatorHead)) {
                        lookingUpSearchBy = s;
                        break;
                    }
                }

                if (lookingUpSearchBy == null) {
                    throw new NullPointerException("SearchBy header text not found");
                }

                searchType = lookingUpSearchBy;
                searchStr = locator.substring(idx + 1);
            }
        }

        try {
            switch (searchType) {
                case ID:
                    this.getWaitObject().until(ExpectedConditions.visibilityOfElementLocated(new By.ById(searchStr)));
                    findObject = new WebObject(driver.findElement(new By.ById(searchStr)));
                    break;
                case XPath:
                    this.getWaitObject().until(ExpectedConditions.visibilityOfElementLocated(new By.ByXPath(searchStr)));
                    findObject = new WebObject(driver.findElement(new By.ByXPath(searchStr)));
                    break;
                case Name:
                    this.getWaitObject().until(ExpectedConditions.visibilityOfElementLocated(new By.ByName(searchStr)));
                    findObject = new WebObject(driver.findElement(new By.ByName(searchStr)));
                    break;
                default:
                    break;
            }
        } catch (StaleElementReferenceException ex) {
            LOGGER.error(ex.getMessage());
        }

        return findObject;
    }

    public void navigateToURL(String sURL) {
        baseObj.info("Navigate to: " + sURL);
        driver.navigate().to(sURL);
    }

    public boolean isPageTitleContains(String searchStr) {
        //String test = driver.getTitle();
        this.waitForPageLoad();
        return driver.getTitle().contains(searchStr);
    }

    public boolean isPageContains(String searStr) {
        this.waitForPageLoad();
        return driver.getPageSource().contains(searStr);
    }

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            LOGGER.trace(e.getMessage(), e);
            throw new AssertionError(e.getMessage());
        }
    }

    public void waitForPageLoad() {
        try {
            sleep(2);
            this.getWaitObject().until(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete';"));
        } catch (WebDriverException e) {
            LOGGER.trace(e.getMessage(), e);
            throw new AssertionError("Page is not loaded completely!!!");
        }
    }

    public void scrollToTheEndOfPage() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        sleep(2);
    }

}
