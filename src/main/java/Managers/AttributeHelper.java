package Managers;

import io.qameta.allure.Step;

import java.time.LocalTime;
import java.util.List;


public class AttributeHelper extends PageManager {

    AttributeHelper(AppManager manager) {
        super(manager.getDriver());
    }

    @Step("Get actual user personal information")
    public List<String> getActualUserPersonalInformation() {
        return accountPage.getActualUserPersonalInformation();
    }

    @Step("Get playlist name")
    public String getPlaylistName() {
        return playListPage.getNameOfCreatedPlaylist();
    }

    @Step("Get search confirm result text")
    public String getSearchConfirmText() {
        return searchPage.getSearchResultText();
    }

    @Step("Get artist name after search")
    public String getArtistName() {
        return searchPage.getArtistNameAfterSearch();
    }

    @Step("Get all playlists names")
    public List<String> getAllPlaylistsNames() {
        return myMusicPage.getAllPlaylistsNames();
    }

    @Step("Get actual favorite tracks names")
    public List<String> getActualFavoriteTracksNames() {
        return myMusicPage.getActualFavoriteTracksNames();
    }

    @Step("Get expected favorite tracks names")
    public List<String> getExpectedFavoriteTracksNames() {
        return searchPage.getExpectedFavoriteTracksNames();
    }

    @Step("Get actual tracks in playlist")
    public List<String> getActualTracksInPlaylist() {
        return playListPage.getActualTracksNamesInPlaylist();
    }

    @Step("Get expected tracks in playlist")
    public List<String> getExpectedTracksInPlaylist() {
        return searchPage.getExpectedTracksNamesInPlaylist();
    }

    @Step("Get expected playlist duration")
    public LocalTime getExpectedPlaylistDuration() {
        return playListPage.getExpectedPlaylistDuration();
    }

    @Step("Get actual playlist duration")
    public LocalTime getActualPlaylistDuration() {
        return playListPage.getActualPlaylistDuration();
    }

    @Step("Get actual channel link")
    public String getActualChannelLink() {
        return channelsPage.getActualChannelLink();
    }

    @Step("Get expected channel link")
    public String getExpectedChannelLink() {
        return channelsPage.getExpectedChannelLink();
    }

    @Step("Get actual track name from track context menu")
    public String getActualTrackNameFromContextMenu() {
        return playListPage
                .openTrackContextMenu()
                .getActualTrackNameFromContextMenu();
    }

    @Step("Get expected track name from page")
    public String getExpectedTrackNameFromPage() {
        return playListPage.getExpectedTrackNameFromPage();
    }

    @Step("Save expected track name from page")
    public void saveExpectedTrackName() {
        playListPage.saveExpectedTrackName();
    }

}
