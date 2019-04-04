package Managers;

import Models.User;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;

import static Base.TestBase.getBaseUser;


public class UserHelper extends PageManager {

    UserHelper(AppManager manager) {
        super(manager.getDriver());
    }

    @Step("Login to account")
    public void loginAs(User user) {
        loginPage
                .inputEmail(user.getUserEmail())
                .inputPassword(user.getUserPassword())
                .clickLogin();
    }

    @Step("Check login was successful")
    public boolean checkLogin() {
        return mainPage.verifyLoginButtonIsDisplayed();
    }

    @Step("Check logout was successful")
    public boolean checkLogout() {
        return homePage.verifyLogoutButtonIsDisplayed();
    }

    @Step("Create playlist {name}")
    public void createPlaylist(String name, String desc) {
        myMusicPage
                .openCreatePlaylistPopup()
                .inputPlaylistName(name)
                .inputPlaylistDescriptions(desc)
                .clickCreatePlaylistButton();
    }

    @Step("Search music")
    public void searchMusic(String searchData) {
        mainPage
                .inputSearchText(searchData)
                .clickSearchButton();
    }

    @Step("Add tracks to favorite")
    public void addTracksToFavorite(int sumTracksToAdd) {
        searchPage.addTracksToFavoriteAndSaveTracksNamesToExpected(sumTracksToAdd);
    }

    @Step("Delete tracks from favorite")
    public void deleteTracksFromFavorite() {
        myMusicPage
                .selectCheckboxFieldForDeleteAllFavoriteTracks()
                .clickFavoriteTracksDeleteButton()
                .acceptAlertForDeleteFavoriteTracks();
    }

    @Step("Add tracks to playlist")
    public void addTracksToPlaylist(String playlistName, int sumTracksToAdd) {
        searchPage.addTracksToPlaylistAndSaveTracksNamesToExpected(playlistName, sumTracksToAdd);
    }

    @Step("Open playlist")
    public void openPlaylist(String playlistName) {
        myMusicPage.openCreatedPlaylist(playlistName);
    }

    @Step("Save expected chanel link")
    public void saveExpectedChannelLink() {
        channelsPage.saveExpectedChannelLinkFromPage();
    }

    @Step("Get expected user personal information")
    public List<String> getExpectedUserPersonalInformation() {
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
