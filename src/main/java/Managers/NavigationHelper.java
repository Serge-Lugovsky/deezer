package Managers;

public class NavigationHelper extends PageManager {

    NavigationHelper(AppManager manager){
        super(manager.getDriver());
    }

    public void goToLoginPage(){
        homePage.moveToLoginPage();
    }

    public void goToMyInfoMenu(){
        mainPage.openProfileMenu()
                        .moveToAccountPage();
        accountPage.moveToMyInformationMenu();
    }

    public void goToMyMusicPlaylistMenu(){
        mainPage.moveToPlaylistMenu();
    }

}
