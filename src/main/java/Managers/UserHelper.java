package Managers;

import Models.User;

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

    public void logoutFromAccount(){
        mainPage
                .openProfileMenu()
                .logout();
    }

}
