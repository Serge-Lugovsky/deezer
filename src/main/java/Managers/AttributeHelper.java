package Managers;

import Models.User;

import java.util.List;


public class AttributeHelper extends PageManager {

    AttributeHelper(AppManager manager){
        super(manager.getDriver());
    }

    public List<String> getActualUserInfoList(){
        return accountPage.getAllUserInputData();
    }

    public List<String> getExpectedUserInfoList(User user){
        return user.getUserInfoList();
    }

}
