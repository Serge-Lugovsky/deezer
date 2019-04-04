import Base.TestBase;
import Listeners.ScreenShotOnFailListener;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static Base.TestsDescription.USER_INFO_TEST_DESCRIPTION;


@Listeners(ScreenShotOnFailListener.class)
public class UserInfoTest extends TestBase {

    @Description(USER_INFO_TEST_DESCRIPTION)
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Check user data", groups = "UIOperation")
    public void userInfoTest() {
        app.getNavigationHelper().goToMyInformationMenu();
        Assert.assertEquals(app.getAttributeHelper().getActualUserPersonalInformation(),
                app.getUserHelper().getExpectedUserPersonalInformation());
    }

}
