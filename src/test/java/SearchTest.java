import Base.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Listeners.ScreenShotOnFailListener;

@Listeners({ScreenShotOnFailListener.class})
public class SearchTest extends TestBase {

    private final String searchArtist = "eminem";

    @Description(value = "Search music  test")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Add test to playlist", groups = {"UIOperation"})
    public void searchTest(){
        app.getUserHelper().searchMusic(searchArtist);
        Assert.assertEquals(app.getAttributeHelper().getSearchConfirmText(), searchArtist);
        app.getNavigationHelper().goToArtistSearchMenu();
        Assert.assertTrue(app.getAttributeHelper().getNameArtist().contains(searchArtist));
    }

}
