package demo.web.ui;


import com.automation.framework.core.web.ui.object.BaseTest;
import com.automation.test.libraries.web.ui.pageobjects.MecuryTourLogin;
import org.testng.annotations.Test;


public class Quy_LoginTest extends BaseTest {

    private String baseURL;

    public Quy_LoginTest() {
        baseURL = "http://newtours.demoaut.com/";
    }

    @Test
    public void tc001_VerifyLoginSuccessfully() {
        MecuryTourLogin MecuryTourLogin = new MecuryTourLogin(driver);
        MecuryTourLogin.navigateToURL(baseURL);
        MecuryTourLogin.getUsernameTxtField().sendKeys("shopeetest", false);
        MecuryTourLogin.getPasswordTxtField().sendKeys("Shopee@2020", true);
        MecuryTourLogin.waitForPageLoad();
    }

    @Test
    public void tc002_VerifyLoginUnsuccessfullyWithIncorrectCredential() {
        MecuryTourLogin MecuryTourLogin = new MecuryTourLogin(driver);
        MecuryTourLogin.navigateToURL(baseURL);
        MecuryTourLogin.getUsernameTxtField().sendKeys("abc", false);
        MecuryTourLogin.getPasswordTxtField().sendKeys("abc", true);
        MecuryTourLogin.waitForPageLoad();
    }

}

