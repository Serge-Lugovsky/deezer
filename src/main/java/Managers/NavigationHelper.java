package Managers;

import io.qameta.allure.Step;

public class NavigationHelper extends PageManager {

    NavigationHelper(AppManager manager) {
        super(manager.getDriver());
    }

    @Step("Go to login page")
    public void goToLoginPage() {
        homePage.moveToLoginPage();
    }

    @Step("Go to my information menu")
    public void goToMyInformationMenu() {
        mainPage.openProfileMenu()
                .moveToAccountPage();
        accountPage.moveToMyInformationMenu();
    }

    @Step("Go to playlists menu")
    public void goToMyMusicPlaylistsMenu() {
        mainPage.moveToPlaylistsMenu();
    }

    @Step("Go to artist search menu")
    public void goToArtistsSearchMenu() {
        searchPage.moveToArtistSearchMenu();
    }

    @Step("Go to favorite tracks menu")
    public void goToMyFavoriteTacksMenu() {
        mainPage.moveToMyFavoriteTracksMenu();
    }

    @Step("Go to channels page")
    public void goToChannelsPage() {
        mainPage.moveToChannelsPage();
    }

    @Step("Go to chanel page")
    public void goToChanelPage() {
        channelsPage.openChanelPageAndSaveChanelName();
    }

}
