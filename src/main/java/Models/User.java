package Models;


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

    public User() {
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    public User setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
        return this;
    }

    public User setMonthOfBirth(String monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
        return this;
    }

    public User setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
        return this;
    }

    public User setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public User setTown(String town) {
        this.town = town;
        return this;
    }

    public User setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public User setLanguage(String language) {
        this.language = language;
        return this;
    }

    public User setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public User setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public User setUserPassword(String userPassword) {
        this.userPassword = userPassword;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getAddress() {
        return address;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public String getMonthOfBirth() {
        return monthOfBirth;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getTown() {
        return town;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getLanguage() {
        return language;
    }

    public String getGender() {
        return gender;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }
}
