package com.automation.test.libraries.web.ui.pageobjects;

import com.automation.framework.core.web.ui.object.BasePage;
import com.automation.framework.core.web.ui.object.WebObject;
import com.automation.framework.core.web.ui.object.Wrapper;
import org.openqa.selenium.WebDriver;

public class AddPeoplePage extends BasePage {

    private String iptFirstName = "//input[@field='firstName' and @refkey='1']";
    private String iptLastName = "//input[@field='lastName' and @refkey='1']";
    private String iptEmail = "//input[@field='email' and @refkey='1']";
    private String spanFirstManagerSpan = "//*[@id='content-container']//tr[1]/td[@data-cucumber='manager' ]//span";
    private String btnAddPeople = "//span[text()='Add People']";
    private String divResultMessage = "//div[contains(@class,'f3 flex items-center')]";

    public AddPeoplePage(WebDriver driver) {
        super(driver);
        baseObj = new Wrapper();
    }

    public WebObject getFirstNameInputField() {
        return findWebElement(iptFirstName);
    }

    public WebObject getLastNameInputField() {
        return findWebElement(iptLastName);
    }

    public WebObject getEmailField() {
        return findWebElement(iptEmail);
    }

    public WebObject getFirstManagerDropdown() {
        return findWebElement(spanFirstManagerSpan);
    }

    public WebObject getResultMessageDiv() {
        return findWebElement(divResultMessage);
    }

    public WebObject getAddPeopleButton() {
        return findWebElement(btnAddPeople);
    }

    public void addPeople(String firstName, String lastName, String email, String managerName) throws InterruptedException {
        this.getFirstNameInputField().sendKeys(firstName, false);
        this.getLastNameInputField().sendKeys(lastName, false);
        this.getEmailField().sendKeys(email, false);
        this.getFirstManagerDropdown().click();
        this.sleep(1);
        this.getFirstManagerDropdown().selectByPartialText(managerName);
        this.sleep(1);
        this.getAddPeopleButton().click();
    }

    public boolean verifyAddPeopleStatus(String status) {
        String fullResultMessage = getResultMessageDiv().getText();
        String resultMessage = fullResultMessage.substring(fullResultMessage.indexOf("\n") + 1);
        String successMessage = "Congratulations";
        String existingEmailMessage = "Uh oh! Unable to add user because email already exists";
        switch (status) {
            case "Successfully":
                if (resultMessage.equals(successMessage)) {
                    baseObj.info("Add people successfully with correct message:'" + successMessage + "'");
                    return true;
                } else {
                    baseObj.info("Add people successfully with incorrect message:'" + resultMessage + "' instead of '" + successMessage + "'");
                    return false;
                }
            case "Existing Email":
                if (resultMessage.equals(existingEmailMessage)) {
                    baseObj.info("Message displays correctly:'" + existingEmailMessage + "'");
                    return true;
                } else {
                    baseObj.info("Message displays incorrectly:'" + resultMessage + "' instead of '" + existingEmailMessage + "'");
                    return false;
                }
            default: {
                baseObj.info("Invalid status");
                return false;
            }
        }
    }
}
