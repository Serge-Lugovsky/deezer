package Managers;

import Models.User;
import io.qameta.allure.Step;

import java.util.List;

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

    @Step("Get all user playlist name")
    public List<String> getListOfPlaylistNames(){
        return myMusicPage.getListPlaylistName();
    }

    @Step("Logout from account")
    public void logoutFromAccount(){
        mainPage
                .openProfileMenu()
                .logout();
    }

}
