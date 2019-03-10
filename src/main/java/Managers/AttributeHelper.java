package Managers;

import Models.User;
import io.qameta.allure.Step;

import java.util.List;


public class AttributeHelper extends PageManager {

    AttributeHelper(AppManager manager){
        super(manager.getDriver());
    }

    @Step("Get actual user personal information list")
    public List<String> getActualUserInfoList(){
        return accountPage.getAllUserInputData();
    }

    @Step("Get expected user personal information list")
    public List<String> getExpectedUserInfoList(User user){
        return user.getUserInfoList();
    }

    @Step("Get name of created playlist")
    public String getNameOfCreatedPlaylist(){
        return playListPage.getPlaylistName();
    }

}
