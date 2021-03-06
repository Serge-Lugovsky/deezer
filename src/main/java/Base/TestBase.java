package Base;

import Managers.AppManager;
import Managers.SingletonAppManager;
import Models.User;
import Utils.PropertyLoader;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import static Utils.AllureEnvironmentWriter.setAllureEnvironment;

public class TestBase {
    public AppManager app = SingletonAppManager.getInstance().manager;

    private static Cookie sessionId;
    private static User baseUser = new User();

    @BeforeSuite(alwaysRun = true)
    public void getSessionCookieAndWriteAllureEnv() {
        setBaseUserData();
        setAllureEnvironment(app.getDriver());
//        app.getNavigationHelper().goToLoginPage();
//        app.getUserHelper().loginAs(getUser());
//        Assert.assertTrue(app.getUserHelper().checkLogin(), "Login failed");
//
//        sessionId = app.getDriver().manage().getCookieNamed("arl");

        sessionId = new Cookie.Builder("arl", PropertyLoader.loadProperty("SESSION_ID"))
                .path("/").domain(".deezer.com").build();
    }

    @BeforeMethod(onlyForGroups = "UIOperation")
    public void login() {
        app.getDriver().manage().addCookie(sessionId);
        app.getDriver().navigate().refresh();
        Assert.assertTrue(app.getUserHelper().checkLogin(), "Login failed");
    }

    @AfterMethod(onlyForGroups = "UIOperation")
    public void logout() {
        app.getDriver().manage().deleteAllCookies();
        app.getDriver().get(PropertyLoader.loadProperty("BASE_URL"));
        Assert.assertTrue(app.getUserHelper().checkLogout(), "logout failed");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.getDriver().quit();
    }

    private void setBaseUserData() {
        baseUser
                .setGender(PropertyLoader.loadProperty("USER_GENDER"))
                .setUserName(PropertyLoader.loadProperty("USER_NAME"))
                .setLastName(PropertyLoader.loadProperty("USER_LAST_NAME"))
                .setFirstName(PropertyLoader.loadProperty("USER_FIRST_NAME"))
                .setDayOfBirth(PropertyLoader.loadProperty("USER_DAY_OF_BIRTH"))
                .setMonthOfBirth(PropertyLoader.loadProperty("USER_MONTH_OF_BIRTH"))
                .setYearOfBirth(PropertyLoader.loadProperty("USER_YEAR_OF_BIRTH"))
                .setAddress(PropertyLoader.loadProperty("USER_ADDRESS"))
                .setZipCode(PropertyLoader.loadProperty("USER_ZIP_CODE"))
                .setTown(PropertyLoader.loadProperty("USER_TOWN"))
                .setMobilePhone(PropertyLoader.loadProperty("USER_MOBILE_PHONE"))
                .setLanguage(PropertyLoader.loadProperty("USER_LANGUAGE"))
                .setUserEmail(PropertyLoader.loadProperty("EMAIL"))
                .setUserPassword(PropertyLoader.loadProperty("PASSWORD"));
    }

    public static User getBaseUser() {
        return baseUser;
    }

}