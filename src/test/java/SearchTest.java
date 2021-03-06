import Base.TestBase;
import Listeners.ScreenShotOnFailListener;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static Base.TestsDescription.SEARCH_TEST_DESCRIPTION;

@Listeners(ScreenShotOnFailListener.class)
public class SearchTest extends TestBase {

    private final String SEARCH_ARTIST = "eminem";

    @Description(SEARCH_TEST_DESCRIPTION)
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Search music", groups = "UIOperation")
    public void searchTest() {
        app.getUserHelper().searchMusic(SEARCH_ARTIST);
        Assert.assertEquals(app.getAttributeHelper().getSearchConfirmText(), SEARCH_ARTIST);
        app.getNavigationHelper().goToArtistsSearchMenu();
        Assert.assertTrue(app.getAttributeHelper().getArtistName().contains(SEARCH_ARTIST));
    }

}
