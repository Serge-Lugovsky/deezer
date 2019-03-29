import Base.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Listeners.ScreenShotOnFailListener;

import static Base.TestsDescription.CHECK_CHANNEL_LINK_TEST_DESCRIPTION;

@Listeners(ScreenShotOnFailListener.class)
public class ChannelTest extends TestBase {

    @Description(value = CHECK_CHANNEL_LINK_TEST_DESCRIPTION)
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Check channel link", groups = "UIOperation")
    public void checkChannelLinkTest(){
        app.getNavigationHelper().goToChannelsPage();
        app.getUserHelper().saveExpectedChannelLink();
        app.getNavigationHelper().goToChanelPage();
        Assert.assertEquals(app.getAttributeHelper().getActualChannelLink(),
                app.getAttributeHelper().getExpectedChannelLink());
    }

}
