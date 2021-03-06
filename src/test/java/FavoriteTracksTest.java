import Base.TestBase;
import Listeners.ScreenShotOnFailListener;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static Base.TestsDescription.ADD_TRACKS_TO_FAVORITE_TEST_DESCRIPTION;
import static Base.TestsDescription.DELETE_TRACKS_FROM_FAVORITE_TEST_DESCRIPTION;

@Listeners(ScreenShotOnFailListener.class)
public class FavoriteTracksTest extends TestBase {

    private final int SUM_TRACKS_TO_ADD_IN_FAVORITE = 5;
    private final String MUSIC_FOR_SEARCH = "Miyagi";

    @Description(ADD_TRACKS_TO_FAVORITE_TEST_DESCRIPTION)
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Add tracks to favorite", groups = "UIOperation")
    public void addTracksToFavoriteTest() {
        app.getUserHelper().searchMusic(MUSIC_FOR_SEARCH);
        Assert.assertEquals(app.getAttributeHelper().getSearchConfirmText(), MUSIC_FOR_SEARCH);
        app.getUserHelper().addTracksToFavorite(SUM_TRACKS_TO_ADD_IN_FAVORITE);
        app.getNavigationHelper().goToMyFavoriteTacksMenu();
        Assert.assertEquals(app.getAttributeHelper().getActualFavoriteTracksNames(),
                app.getAttributeHelper().getExpectedFavoriteTracksNames());
    }

    @Description(DELETE_TRACKS_FROM_FAVORITE_TEST_DESCRIPTION)
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Delete tracks from favorite", dependsOnMethods = "addTracksToFavoriteTest", groups = "UIOperation")
    public void deleteTracksFromFavoriteTest() {
        app.getNavigationHelper().goToMyFavoriteTacksMenu();
        app.getUserHelper().deleteTracksFromFavorite();
        Assert.assertNotEquals(app.getAttributeHelper().getActualFavoriteTracksNames(),
                app.getAttributeHelper().getExpectedFavoriteTracksNames());
    }

}
