import Base.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Listeners.ScreenShotOnFailListener;


@Listeners({ScreenShotOnFailListener.class})
public class PlaylistTest extends TestBase {

    private final String playlistName = "My Epic Music";
    private final String playlistDescription = "Playlist Of my Epic music";
    private final String musicForSearch = "Miyagi";
    private final int sumTracksToAddInFavorite = 5;
    private final int sumTracksToAddInPlaylist = 6;

    @Description(value = "Create playlist test")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Create playlist test", groups = {"playlistOperation", "UIOperation"}, priority = 1)
    public void createPlaylistTest(){
        app.getNavigationHelper().goToMyMusicPlaylistMenu();
        app.getUserHelper().createPlaylist(playlistName, playlistDescription);
        Assert.assertEquals(app.getAttributeHelper().getNameOfCreatedPlaylist(), playlistName);
        app.getNavigationHelper().goToMyMusicPlaylistMenu();
        Assert.assertTrue(app.getAttributeHelper().getListOfPlaylistNames().contains(playlistName));
    }

    @Description(value = "Add tracks to playlist test")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Add track to playlist test", dependsOnMethods = "createPlaylistTest", priority = 2,
            groups = {"UIOperation"})
    public void addTrackToPlaylistTest(){
        app.getUserHelper().searchMusic(musicForSearch);
        Assert.assertEquals(app.getAttributeHelper().getSearchConfirmText(), musicForSearch);
        app.getUserHelper().addTracksToCreatedPlaylist(playlistName, sumTracksToAddInPlaylist);
        app.getNavigationHelper().goToMyMusicPlaylistMenu();
        app.getUserHelper().openPlaylist(playlistName);
        Assert.assertEquals(app.getAttributeHelper().getActualTracksInPlaylist(),
                                                                app.getAttributeHelper().getExpectedTracksInPlaylist());
    }

    @Test(description = "Check playlist duration", dependsOnMethods = "addTrackToPlaylistTest", priority = 3,
            groups = {"UIOperation"})
    public void checkPlaylistTotalDurationTest(){
        app.getNavigationHelper().goToMyMusicPlaylistMenu();
        app.getUserHelper().openPlaylist(playlistName);
        Assert.assertEquals(app.getAttributeHelper().getDurationPlaylistActual(),
                app.getAttributeHelper().getDurationPlaylistExpected());
    }

    @Description(value = "Add track to favorite")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Add track to favorite", groups = {"playlistOperation", "UIOperation"}, priority = 4)
    public void addTrackToFaforiteTest(){
        app.getUserHelper().searchMusic(musicForSearch);
        Assert.assertEquals(app.getAttributeHelper().getSearchConfirmText(), musicForSearch);
        app.getUserHelper().addTracksToFavoriteTracks(sumTracksToAddInFavorite);
        app.getNavigationHelper().goToMyFavoriteTacksPage();
        Assert.assertEquals(app.getAttributeHelper().getListFavoriteTracksSizeAfterAdd(), sumTracksToAddInFavorite);
    }

    @Description(value = "Delete track from favorite")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Delete track from favorite", dependsOnMethods = "addTrackToFaforiteTest",
            groups = {"playlistOperation", "UIOperation"}, priority = 5)
    public void deleteTracksFromFavoriteTest(){
        app.getNavigationHelper().goToMyFavoriteTacksPage();
        app.getUserHelper().deleteTracksFromFavorite();
        Assert.assertNotEquals(app.getAttributeHelper().getListFavoriteTracksSizeAfterAdd(), sumTracksToAddInFavorite);
    }

    @Description(value = "Delete playlist")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Delete playlist", dependsOnMethods = "createPlaylistTest", priority = 6, alwaysRun = true)
    public void deletePlaylistTest(){
        deletePlaylistById(getPlaylistIdForDelete(playlistName));
        Assert.assertFalse(getAllPlaylists().contains(playlistName));
    }

}
