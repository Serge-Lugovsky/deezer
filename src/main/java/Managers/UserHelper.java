package Managers;

import Models.User;
import io.qameta.allure.Step;


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

    @Step("Search tracks")
    public void searchMusic(String searchData){
        mainPage.searchTracks(searchData);
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

    @Step("Add tracks to playlist")
    public void addTracksToCreatedPlaylist(String playlistName, int elemNmb){
        searchPage.openTracksContextMenuForAddToPlaylist(playlistName, elemNmb);
    }

    @Step("Open playlist")
    public void openPlaylist(String playlistName){
        myMusicPage.openCreatedPlaylist(playlistName);
    }
}
