package demo.web.api;

import com.automation.framework.core.base.OrgBaseTest;
import com.automation.framework.core.datadriven.utils.DataProviderClass;
import com.automation.test.libraries.web.api.models.Post;
import com.automation.test.libraries.web.api.utils.PostMethods;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class JsonDemo extends OrgBaseTest {
    private static final Logger LOGGER = Logger.getLogger(JsonDemo.class);


    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void tc009_VerifyValidExistingData(HashMap<String, String> data) {

        PostMethods post = new PostMethods();
        String id = (String) data.get("id");
        System.out.println(post.getPostObjectByID(id));

        Post oriObj = post.getPostObjectByID(id);

        LOGGER.info(post);

        Assert.assertTrue(oriObj.getUserId() == Integer.parseInt(data.get("userId"))
                && oriObj.getTitle().equals(data.get("title"))
                && oriObj.getBody().equals(data.get("body")));
    }

    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void tc010_VerifyUnExistingData(HashMap<String, String> data) {

        PostMethods post = new PostMethods();
        String id = (String) data.get("id");
        System.out.println(post.getPostObjectByID(id));

        Post oriObj = post.getPostObjectByID(id);

        LOGGER.info(post);

        Assert.assertFalse(oriObj.getUserId() == Integer.parseInt(data.get("userId"))
                && oriObj.getTitle().equals(data.get("title"))
                && oriObj.getBody().equals(data.get("body")));
    }
}
