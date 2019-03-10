import Base.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.Test;


public class PlaylistTest extends TestBase {

    private final String playlistName = "My Epic Music";
    private final String playlistDescription = "Playlist Of my Epic music";

    @Test(description = "Create playlist", groups = {"playlistOperation"})
    public void createPlaylistTest(){
        app.getNavigationHelper().goToMyMusicPlaylistMenu();
        app.getUserHelper().createPlaylist(playlistName, playlistDescription);
        Assert.assertEquals(app.getAttributeHelper().getNameOfCreatedPlaylist(), playlistName);
        app.getNavigationHelper().goToMyMusicPlaylistMenu();
        Assert.assertTrue(app.getUserHelper().getListOfPlaylistNames().contains(playlistName));
    }

    @AfterGroups(groups = "playlistOperation")
    public void deletePlaylist(){
        deletePlaylistById(getPlaylistIdForDelete(playlistName));
        Assert.assertFalse(getAllPlaylists().contains(playlistName));
    }

}
