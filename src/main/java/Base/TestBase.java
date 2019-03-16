package Base;

import DeezerAPI.MyActivityAPI;
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

public class TestBase extends MyActivityAPI {
    public AppManager app = SingletonAppManager.getInstance().manager;
    private static Cookie sessionId;

    private User user = new User("john", "Smith", "John",
            "22","08","1990", "Vasylyny",
            "18000", "Cherkasy", "+12 (032) 111-65-43","en",
            "Male", PropertyLoader.loadProperty("EMAIL"), PropertyLoader.loadProperty("PASSWORD"));

    protected User getUser() {
        return user;
    }

    @BeforeSuite(alwaysRun = true)
    public void getSessionCookieAndWriteAllureEnv(){
        setAllureEnvironment(app.getDriver());
        app.getNavigationHelper().goToLoginPage();
        app.getUserHelper().loginAs(getUser());
        Assert.assertTrue(app.getUserHelper().checkLogin(), "Login failed");

        sessionId = app.getDriver().manage().getCookieNamed("sid");
    }

    @BeforeMethod(onlyForGroups = {"UIOperation"})
    public void login(){
        if (!app.getDriver().manage().getCookieNamed("sid").getValue().equals(sessionId.getValue())){
            app.getDriver().manage().addCookie(sessionId);
            app.getDriver().navigate().refresh();
            Assert.assertTrue(app.getUserHelper().checkLogin(), "Login failed");
        }
    }

    @AfterMethod(onlyForGroups = {"UIOperation"})
    public void logout(){
        app.getDriver().manage().deleteAllCookies();
        app.getDriver().navigate().refresh();
        Assert.assertTrue(app.getUserHelper().checkLogout(), "logout failed");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown(){
        app.getDriver().quit();
    }

}
