package Managers;

import DeezerAPI.MyActivityAPI;
import Drivers.Driver;
import Utils.PropertyLoader;
import org.openqa.selenium.WebDriver;


public class AppManager {

    private WebDriver driver;
    private UserHelper userHelper;
    private NavigationHelper navigationHelper;
    private AttributeHelper attributeHelper;
    private MyActivityAPI myActivityAPI;

    AppManager() {
        String browserName = PropertyLoader.loadProperty("browser.name");
        String headLess = PropertyLoader.loadProperty("head.less");
        String baseUrl = PropertyLoader.loadProperty("BASE_URL");

        driver = new Driver().setupDriver(browserName, headLess);
        driver.get(baseUrl);
        myActivityAPI = new MyActivityAPI();

        userHelper = new UserHelper(this);
        navigationHelper = new NavigationHelper(this);
        attributeHelper = new AttributeHelper(this);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public UserHelper getUserHelper() {
        return userHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public AttributeHelper getAttributeHelper() {
        return attributeHelper;
    }

    public MyActivityAPI getMyActivityAPI() {
        return myActivityAPI;
    }

}
