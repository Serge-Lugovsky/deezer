package Managers;

import io.qameta.allure.Step;

public class NavigationHelper extends PageManager {

    NavigationHelper(AppManager manager){
        super(manager.getDriver());
    }

    @Step("Go to login page")
    public void goToLoginPage(){
        homePage.moveToLoginPage();
    }

    @Step("Go to user information menu")
    public void goToMyInfoMenu(){
        mainPage.openProfileMenu()
                .moveToAccountPage();
        accountPage.moveToMyInformationMenu();
    }

    @Step("Go to all playlist")
    public void goToMyMusicPlaylistMenu(){
        mainPage.moveToPlaylistMenu();
    }

    @Step("Move to artist search menu")
    public void goToArtistSearchMenu(){
        searchPage.moveToArtistSearchMenu();
    }

    @Step("Move to favorite tracks page")
    public void goToMyFavoriteTacksPage(){
        mainPage.moveToMyFavoriteTracksMenu();
    }

    @Step("Go to channels page")
    public void goToChannelsPage(){
        mainPage.moveToChannelsPage();
    }

    @Step("Go to chanel page")
    public void goToChanelPage(){
        channelsPage.openChanelPage();
    }

}
