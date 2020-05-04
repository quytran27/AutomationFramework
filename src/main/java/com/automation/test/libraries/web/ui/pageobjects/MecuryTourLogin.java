package com.automation.test.libraries.web.ui.pageobjects;


import com.automation.framework.core.web.ui.object.BasePage;
import com.automation.framework.core.web.ui.object.WebObject;
import com.automation.framework.core.web.ui.object.Wrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MecuryTourLogin extends BasePage {
    Wrapper baseObj;
    private String txtUserName = "name=userName";
    private String txtPassword = "name=password";



    public MecuryTourLogin(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        baseObj = new Wrapper();
    }

    public WebObject getUsernameTxtField() {
        return findWebElement(txtUserName);
    }

    public WebObject getPasswordTxtField() {
        return findWebElement(txtPassword);
    }


}



