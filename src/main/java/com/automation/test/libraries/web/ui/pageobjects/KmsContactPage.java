package com.automation.test.libraries.web.ui.pageobjects;


import com.automation.framework.core.web.ui.object.BasePage;
import com.automation.framework.core.web.ui.object.WebObject;
import com.automation.framework.core.web.ui.object.Wrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class KmsContactPage extends BasePage {
    Wrapper baseObj;
    private String txtLastName = "name=lastname";
    private String txtFirstName = "name=firstname";
    private String lblErrorMessage = "//div[@class='hs_error_rollup']//label";

    public KmsContactPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        baseObj = new Wrapper();
    }

    public WebObject getLastNameTxtField() {
        return findWebElement(txtLastName);
    }

    public WebObject getFirstNameTxtField() {
        return findWebElement(txtFirstName);
    }

    public WebObject getErrorMessagelbl() {
        return findWebElement(lblErrorMessage);
    }

    public boolean verifyErrorMessage(String expectedMessage) {
        baseObj.info("Verify" + " '" + expectedMessage + "' " + "error message displays");
        String actualErrorMessage = getErrorMessagelbl().getText();
        if (actualErrorMessage.equalsIgnoreCase(expectedMessage)) {
            baseObj.pass("Error message displays correctly");
        } else {
            baseObj.fail("Error message displays incorrectly. Expected: '" + expectedMessage + "', Actual: '" + actualErrorMessage + "'");
        }
        return getErrorMessagelbl().getText().equalsIgnoreCase(expectedMessage);
    }


}



