import Base.TestBase;
import Listeners.ScreenShotOnFailListener;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static Base.TestsDescription.*;


@Listeners(ScreenShotOnFailListener.class)
public class PlaylistTest extends TestBase {

    private final String PLAYLIST_NAME = "My Epic Music";
    private final String PLAYLIST_DESC = "Playlist Of my Epic music";
    private final String MUSIC_FOR_SEARCH = "Miyagi";
    private final int SUM_TRACKS_TO_ADD_IN_PLAYLIST = 4;

    @Description(CREATE_PLAYLIST_TEST_DESCRIPTION)
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Create playlist", groups = "UIOperation")
    public void createPlaylistTest() {
        app.getNavigationHelper().goToMyMusicPlaylistsMenu();
        app.getUserHelper().createPlaylist(PLAYLIST_NAME, PLAYLIST_DESC);
        Assert.assertEquals(app.getAttributeHelper().getPlaylistName(), PLAYLIST_NAME);
        app.getNavigationHelper().goToMyMusicPlaylistsMenu();
        Assert.assertTrue(app.getAttributeHelper().getAllPlaylistsNames().contains(PLAYLIST_NAME));
    }

    @Description(ADD_TRACKS_TO_PLAYLIST_TEST_DESCRIPTION)
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Add tracks to playlist", dependsOnMethods = "createPlaylistTest", groups = "UIOperation")
    public void addTracksToPlaylistTest() {
        app.getUserHelper().searchMusic(MUSIC_FOR_SEARCH);
        Assert.assertEquals(app.getAttributeHelper().getSearchConfirmText(), MUSIC_FOR_SEARCH);
        app.getUserHelper().addTracksToPlaylist(PLAYLIST_NAME, SUM_TRACKS_TO_ADD_IN_PLAYLIST);
        app.getNavigationHelper().goToMyMusicPlaylistsMenu();
        app.getUserHelper().openPlaylist(PLAYLIST_NAME);
        Assert.assertEquals(app.getAttributeHelper().getActualTracksInPlaylist(),
                app.getAttributeHelper().getExpectedTracksInPlaylist());
    }

    @Description(CHECK_PLAYLIST_TOTAL_DURATION_TEST_DESCRIPTION)
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Check playlist duration", dependsOnMethods = "addTracksToPlaylistTest", groups = "UIOperation")
    public void checkPlaylistTotalDurationTest() {
        app.getNavigationHelper().goToMyMusicPlaylistsMenu();
        app.getUserHelper().openPlaylist(PLAYLIST_NAME);
        Assert.assertEquals(app.getAttributeHelper().getActualPlaylistDuration(),
                app.getAttributeHelper().getExpectedPlaylistDuration());
    }

    @Description(CHECK_TRACK_NAME_IN_CONTEXT_MENU_TEST_DESCRIPTION)
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Check track name in context menu", dependsOnMethods = "addTracksToPlaylistTest",
            groups = "UIOperation")
    public void checkTrackNameInContextMenuTest() {
        app.getNavigationHelper().goToMyMusicPlaylistsMenu();
        app.getUserHelper().openPlaylist(PLAYLIST_NAME);
        app.getAttributeHelper().saveExpectedTrackName();
        Assert.assertEquals(app.getAttributeHelper().getActualTrackNameFromContextMenu(),
                app.getAttributeHelper().getExpectedTrackNameFromPage());
    }

    @Description(DELETE_PLAYLIST_TEST_DESCRIPTION)
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Delete playlist", dependsOnMethods = "createPlaylistTest", priority = 999)
    public void deletePlaylistTest() {
        app.getMyActivityAPI().deletePlaylistById(app.getMyActivityAPI().getPlaylistIDByName(PLAYLIST_NAME));
        Assert.assertFalse(app.getMyActivityAPI().getAllPlaylist().contains(PLAYLIST_NAME));
    }

}
