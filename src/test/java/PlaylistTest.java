import Base.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Listeners.ScreenShotOnFailListener;

import static Base.TestsDescription.*;


@Listeners(ScreenShotOnFailListener.class)
public class PlaylistTest extends TestBase {

    private final String PLAYLIST_NAME = "My Epic Music";
    private final String PLAYLIST_DESC = "PlaylistModel Of my Epic music";
    private final String MUSIC_FOR_SEARCH = "Miyagi";
    private final int SUM_TRACKS_TO_ADD_IN_PLAYLIST = 7;

    @Description(value = CREATE_PLAYLIST_TEST_DESCRIPTION)
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Create playlist", groups = "UIOperation")
    public void createPlaylistTest(){
        app.getNavigationHelper().goToMyMusicPlaylistMenu();
        app.getUserHelper().createPlaylist(PLAYLIST_NAME, PLAYLIST_DESC);
        Assert.assertEquals(app.getAttributeHelper().getNameOfCreatedPlaylist(), PLAYLIST_NAME);
        app.getNavigationHelper().goToMyMusicPlaylistMenu();
        Assert.assertTrue(app.getAttributeHelper().getListOfPlaylistNames().contains(PLAYLIST_NAME));
    }

    @Description(value = ADD_TRACKS_TO_PLAYLIST_TEST_DESCRIPTION)
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Add tracks to playlist", dependsOnMethods = "createPlaylistTest", groups = "UIOperation")
    public void addTracksToPlaylistTest(){
        app.getUserHelper().searchMusic(MUSIC_FOR_SEARCH);
        Assert.assertEquals(app.getAttributeHelper().getSearchConfirmText(), MUSIC_FOR_SEARCH);
        app.getUserHelper().addTracksToPlaylistAndSaveTracksName(PLAYLIST_NAME, SUM_TRACKS_TO_ADD_IN_PLAYLIST);
        app.getNavigationHelper().goToMyMusicPlaylistMenu();
        app.getUserHelper().openPlaylist(PLAYLIST_NAME);
        Assert.assertEquals(app.getAttributeHelper().getActualTracksInPlaylist(),
                                                                app.getAttributeHelper().getExpectedTracksInPlaylist());
    }

    @Description(value = CHECK_PLAYLIST_TOTAL_DURATION_TEST_DESCRIPTION)
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Check playlist duration", dependsOnMethods = "addTracksToPlaylistTest", groups = "UIOperation")
    public void checkPlaylistTotalDurationTest(){
        app.getNavigationHelper().goToMyMusicPlaylistMenu();
        app.getUserHelper().openPlaylist(PLAYLIST_NAME);
        Assert.assertEquals(app.getAttributeHelper().getDurationPlaylistActual(),
                app.getAttributeHelper().getDurationPlaylistExpected());
    }

    @Description(value = CHECK_TRACK_NAME_IN_CONTEXT_MENU_TEST_DESCRIPTION)
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Check track name in context menu", dependsOnMethods = "addTracksToPlaylistTest",
            groups = "UIOperation")
    public void checkTrackNameInContextMenuTest(){
        app.getNavigationHelper().goToMyMusicPlaylistMenu();
        app.getUserHelper().openPlaylist(PLAYLIST_NAME);
        app.getAttributeHelper().saveExpectedTrackName();
        Assert.assertEquals(app.getAttributeHelper().getActualTrackNameFromContextMenu(),
                app.getAttributeHelper().getExpectedTrackNameFromPage());
    }


    @Description(value = DELETE_PLAYLIST_TEST_DESCRIPTION)
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Delete playlist", dependsOnMethods = "createPlaylistTest", priority = 999)
    public void deletePlaylistTest(){
        app.getMyActivityAPI().deletePlaylistById(app.getMyActivityAPI().getPlaylistIdForDelete(PLAYLIST_NAME));
        Assert.assertFalse(app.getMyActivityAPI().getAllPlaylists().contains(PLAYLIST_NAME));
    }

}
