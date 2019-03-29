package Managers;

import io.qameta.allure.Step;

import java.time.LocalTime;
import java.util.List;


public class AttributeHelper extends PageManager {

    AttributeHelper(AppManager manager){
        super(manager.getDriver());
    }

    @Step("Get actual user personal information list")
    public List<String> getActualUserInfoList(){
        return accountPage.getAllUserInputData();
    }

    @Step("Get name of created playlist")
    public String getNameOfCreatedPlaylist(){
        return playListPage.getPlaylistName();
    }

    @Step("Get search confirm result text")
    public String getSearchConfirmText(){
        return searchPage.getSearchResultText();
    }

    @Step("Get name artist after search")
    public String getNameArtist(){
        return searchPage.getArtistNameAfterSearch();
    }

    @Step("Get all user playlist names")
    public List<String> getListOfPlaylistNames(){
        return myMusicPage.getListPlaylistName();
    }

    @Step("Get actual favorite tracks names")
    public List<String> getActualListFavoriteTracksNames(){
        return myMusicPage.getActualFavoriteTracksNames();
    }

    @Step("Get expected favorite tracks names")
    public List<String> getExpectedListFavoriteTracksNames(){
        return searchPage.getExpectedFavoriteTracksNames();
    }

    @Step("Get actual playlist total tracks")
    public List<String> getActualTracksInPlaylist(){
        return playListPage.getActualListTracksInPlaylist();
    }

    @Step("Get expected playlist total tracks")
    public List<String> getExpectedTracksInPlaylist(){
        return searchPage.getExpectedListTracksAddedToPlaylist();
    }

    @Step("Get expected playlist total duration")
    public LocalTime getDurationPlaylistExpected(){
        return playListPage.getPlaylistExpectedDuration();
    }

    @Step("Get actual playlist total duration")
    public LocalTime getDurationPlaylistActual(){
        return playListPage.getPlaylistActualDuration();
    }

    @Step("Get actual channel link")
    public String getActualChannelLink(){
        return channelsPage.getActualChannelLink();
    }

    @Step("Get expected channel link")
    public String getExpectedChannelLink(){
        return channelsPage.getExpectedChannelLink();
    }

    @Step("Get actual track name from context menu")
    public String getActualTrackNameFromContextMenu(){
        return playListPage
                .openTrackContextMenu()
                .getActualNameTrackFromContextMenu();
    }

    @Step("Get expected track name in context menu")
    public String getExpectedTrackNameFromPage(){
        return playListPage
                .getExpectedNameTrackFromPage();
    }

    @Step("Save expected track name from page")
    public void saveExpectedTrackName(){
        playListPage.saveExpectedNameTrack();
    }

}
