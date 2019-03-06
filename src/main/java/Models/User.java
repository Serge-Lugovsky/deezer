package Models;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String userName;
    private String lastName;
    private String firstName;
    private String address;
    private String dayOfBirth;
    private String monthOfBirth;
    private String yearOfBirth;
    private String zipCode;
    private String town;
    private String mobilePhone;
    private String language;
    private String gender;
    private String userEmail;
    private String userPassword;

    public User(String userName, String lastName, String firstName,String dayOfBirth, String monthOfBirth,
                String yearOfBirth, String address, String zipCode, String town, String mobilePhone,
                String language, String gender, String userEmail, String userPassword) {

        this.userName = userName;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.dayOfBirth = dayOfBirth;
        this.monthOfBirth = monthOfBirth;
        this.yearOfBirth = yearOfBirth;
        this.zipCode = zipCode;
        this.town = town;
        this.mobilePhone = mobilePhone;
        this.language = language;
        this.gender = gender;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public List<String> getUserInfoList(){
        List<String> finalUserInfoList = new ArrayList<>();

        finalUserInfoList.add(gender);
        finalUserInfoList.add(userName);
        finalUserInfoList.add(lastName);
        finalUserInfoList.add(firstName);
        finalUserInfoList.add(dayOfBirth);
        finalUserInfoList.add(monthOfBirth);
        finalUserInfoList.add(yearOfBirth);
        finalUserInfoList.add(address);
        finalUserInfoList.add(zipCode);
        finalUserInfoList.add(town);
        finalUserInfoList.add(mobilePhone);
        finalUserInfoList.add(language);

        return finalUserInfoList;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

}
