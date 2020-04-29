package demo.web.api;

import com.automation.framework.core.Reports.ExtentTestManager;
import com.automation.framework.core.base.OrgBaseTest;
import com.automation.framework.core.datadriven.utils.DataProviderClass;
import com.automation.framework.core.web.ui.object.BaseTest;
import com.automation.test.libraries.web.api.models.Post;
import com.automation.test.libraries.web.api.utils.PostMethods;
import com.aventstack.extentreports.ExtentTest;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.Test;

import java.util.HashMap;

public class Quy_APIDemo extends BaseTest {
    private static final Logger LOGGER = Logger.getLogger(Quy_APIDemo.class);
    ExtentTest logger;

    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void tc006_VerifyCreatePostFromFiles(HashMap<String, String> data) {
        logger = ExtentTestManager.startTest("tc006_VerifyCreatePostFromFiles");
        PostMethods postMethods = new PostMethods();

        Post postObj = postMethods.createPostObject(
                new Post(Integer.parseInt(data.get("userId")),
                        Integer.parseInt(data.get("id")),
                        data.get("title"),
                        data.get("body"))
        );

        LOGGER.info(postObj);

        Assert.assertFalse(postMethods.verifyPostObject(postObj, data.get("userId"), data.get("title"), data.get("body")));
    }

    @AfterMethod
    public void getResult(ITestResult result) {
        ExtentTestManager.getResult(result, logger);
    }
}