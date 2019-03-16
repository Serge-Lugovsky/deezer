import Base.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.*;
import Listeners.ScreenShotOnFailListener;


@Listeners({ScreenShotOnFailListener.class})
public class UserInfoTest extends TestBase {

    @Description(value = "Check user personal information")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Check user data", groups = {"UIOperation"})
    public void userInfoTest(){
        app.getNavigationHelper().goToMyInfoMenu();
        Assert.assertEquals(app.getAttributeHelper().getActualUserInfoList(),
                                                    app.getAttributeHelper().getExpectedUserInfoList(getUser()));
    }

}
