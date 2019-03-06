import Base.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import Listeners.ScreenShotOnFailListener;
import org.testng.annotations.Test;


@Listeners({ScreenShotOnFailListener.class})
public class UserInfoTest extends TestBase {

    @Description(value = "Check user personal information")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Check user data", invocationCount = 3)
    public void userInfoTest(){
        app.getNavigationHelper().goToMyInfoMenu();
        Assert.assertEquals(app.getAttributeHelper().getActualUserInfoList(),
                                                    app.getAttributeHelper().getExpectedUserInfoList(getUser()));
    }

}
