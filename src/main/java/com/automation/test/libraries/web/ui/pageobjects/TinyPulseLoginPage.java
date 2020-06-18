package com.automation.test.libraries.web.ui.pageobjects;


import com.automation.framework.core.web.ui.object.BasePage;
import com.automation.framework.core.web.ui.object.WebObject;
import com.automation.framework.core.web.ui.object.Wrapper;
import org.openqa.selenium.WebDriver;


public class TinyPulseLoginPage extends BasePage {
    Wrapper baseObj;
    private String txtUserName = "name=session[email]";
    private String txtPassword = "name=session[password]";


    public TinyPulseLoginPage(WebDriver driver) {
        super(driver);
        baseObj = new Wrapper();
    }

    public WebObject getUsernameTxtField() {
        return findWebElement(txtUserName);
    }

    public WebObject getPasswordTxtField() {
        return findWebElement(txtPassword);
    }


}



