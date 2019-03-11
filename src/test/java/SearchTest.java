import Base.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends TestBase {
    private final String searchArtist = "eminem";

    @Description(value = "Search music  test")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Add test to playlist")
    public void searchTest(){
        app.getUserHelper().searchMusic(searchArtist);
        Assert.assertEquals(app.getAttributeHelper().getSearchConfirmText(), searchArtist);
        app.getNavigationHelper().goToArtistSearchMenu();
        Assert.assertTrue(app.getAttributeHelper().getNameArtist().contains(searchArtist));
    }

}
