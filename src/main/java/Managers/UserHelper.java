package Managers;

import Models.User;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;

import static Base.TestBase.getBaseUser;


public class UserHelper extends PageManager {

    UserHelper(AppManager manager){
        super(manager.getDriver());
    }

    @Step("Login to account")
    public void loginAs(User user){
        loginPage
                .inputEmail(user.getUserEmail())
                .inputPassword(user.getUserPassword())
                .clickLogin();
    }

    @Step("Check login was successful")
    public boolean checkLogin(){
        return mainPage.verifyLogin();
    }

    @Step("Check logout was successful")
    public boolean checkLogout(){
        return homePage.verifyLogout();
    }

    @Step("Create playlist {name}")
    public void createPlaylist(String name, String desc){
        myMusicPage
                .openCreatePlaylistPopup()
                .inputPlaylistName(name)
                .inputPlaylistDescriptions(desc)
                .clickCreatePlaylist();
    }

    @Step("Logout from account")
    public void logoutFromAccount(){
        mainPage
                .openProfileMenu()
                .logout();
    }

    @Step("Search music")
    public void searchMusic(String searchData){
        mainPage.searchAnyMusic(searchData);
    }

    @Step("Add tracks to favorite")
    public void addTracksToFavoriteTracks(int sumTracks){
        searchPage.addSelectedTrackToFavorite(sumTracks);
    }

    @Step("Delete favorite tracks")
    public void deleteTracksFromFavorite(){
        myMusicPage
                .selectFavoriteTracks()
                .deleteFavoriteTracks()
                .confirmDeleteFavoriteTracks();
    }

    @Step("Add tracks to playlist and save added tracks names")
    public void addTracksToPlaylistAndSaveTracksName(String playlistName, int elemNmb){
        searchPage.addTracksToPlaylistAndSaveTracksNames(playlistName, elemNmb);
    }

    @Step("Open playlist")
    public void openPlaylist(String playlistName){
        myMusicPage.openCreatedPlaylist(playlistName);
    }

    @Step("Save expected chanel link")
    public void saveExpectedChannelLink(){
        channelsPage.saveExpectedChannelLinkFromPage();
    }

    @Step("Get user personal data")
    public static List<String> USER_PERSONAL_DATA(){
        List<String> finalUserInfoList = new ArrayList<>();

        finalUserInfoList.add(getBaseUser().getGender());
        finalUserInfoList.add(getBaseUser().getUserName());
        finalUserInfoList.add(getBaseUser().getLastName());
        finalUserInfoList.add(getBaseUser().getFirstName());
        finalUserInfoList.add(getBaseUser().getDayOfBirth());
        finalUserInfoList.add(getBaseUser().getMonthOfBirth());
        finalUserInfoList.add(getBaseUser().getYearOfBirth());
        finalUserInfoList.add(getBaseUser().getAddress());
        finalUserInfoList.add(getBaseUser().getZipCode());
        finalUserInfoList.add(getBaseUser().getTown());
        finalUserInfoList.add(getBaseUser().getMobilePhone());
        finalUserInfoList.add(getBaseUser().getLanguage());

        return finalUserInfoList;
    }

}
