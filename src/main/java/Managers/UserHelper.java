package Managers;

import Models.User;

import java.util.List;

public class UserHelper extends PageManager {

    UserHelper(AppManager manager){
        super(manager.getDriver());
    }

    public void loginAs(User user){
        loginPage
                .inputEmail(user.getUserEmail())
                .inputPassword(user.getUserPassword())
                .clickLogin();
    }

    public boolean checkLogin(){
        return mainPage.verifyLogin();
    }

    public boolean checkLogout(){
        return homePage.verifyLogout();
    }

    public void createPlaylist(String name, String desc){
        myMusicPage
                .openCreatePlaylistPopup()
                .inputPlaylistName(name)
                .inputPlaylistDescriptions(desc)
                .clickCreatePlaylist();
    }

    public List<String> getListOfPlaylistNames(){
        return myMusicPage.getListPlaylistName();
    }

    public void logoutFromAccount(){
        mainPage
                .openProfileMenu()
                .logout();
    }

}
