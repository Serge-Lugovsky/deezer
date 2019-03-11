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

    @Step("Go to Playlists menu")
    public void goToMyMusicPlaylistMenu(){
        mainPage.moveToPlaylistMenu();
    }

    @Step("Move to artist search menu")
    public void goToArtistSearchMenu(){
        searchPage.moveToArtistSearchMenu();
    }

}
