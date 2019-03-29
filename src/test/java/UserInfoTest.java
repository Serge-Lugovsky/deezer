import Base.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.*;
import Listeners.ScreenShotOnFailListener;

import static Base.TestsDescription.USER_INFO_TEST_DESCRIPTION;
import static Managers.UserHelper.USER_PERSONAL_DATA;


@Listeners(ScreenShotOnFailListener.class)
public class UserInfoTest extends TestBase {

    @Description(value = USER_INFO_TEST_DESCRIPTION)
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Check user data", groups = "UIOperation")
    public void userInfoTest(){
        app.getNavigationHelper().goToMyInfoMenu();
        Assert.assertEquals(app.getAttributeHelper().getActualUserInfoList(), USER_PERSONAL_DATA());
    }

}
