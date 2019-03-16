package Managers;

import Models.User;
import io.qameta.allure.Step;

import java.util.List;


public class AttributeHelper extends PageManager {

    AttributeHelper(AppManager manager){
        super(manager.getDriver());
    }

    @Step("Get actual user personal information list")
    public List<String> getActualUserInfoList(){
        return accountPage.getAllUserInputData();
    }

    @Step("Get expected user personal information list")
    public List<String> getExpectedUserInfoList(User user){
        return user.getUserInfoList();
    }

    @Step("Get name of created playlist")
    public String getNameOfCreatedPlaylist(){
        return playListPage.getPlaylistName();
    }

    @Step("Get search result text")
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

    @Step("Get get sum of added favorite tracks")
    public int getListFavoriteTracksSizeAfterAdd(){
        return myMusicPage.getListFavoriteTracks();
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
    public int getDurationPlaylistExpected(){
        return playListPage.getPlaylistExpectedDuration();
    }

    @Step("Get actual playlist total duration")
    public int getDurationPlaylistActual(){
        return playListPage.getPlaylistActualDuration();
    }
}
