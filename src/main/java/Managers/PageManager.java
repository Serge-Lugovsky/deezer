package Managers;

import Pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class PageManager {

    private WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    MainPage mainPage;
    AccountPage accountPage;
    MyMusicPage myMusicPage;
    PlayListPage playListPage;


    PageManager(WebDriver driver){
        this.driver = driver;
        homePage = initElements(new HomePage(this));
        loginPage = initElements(new LoginPage(this));
        mainPage = initElements(new MainPage(this));
        accountPage = initElements(new AccountPage(this));
        myMusicPage = initElements(new MyMusicPage(this));
        playListPage = initElements(new PlayListPage(this));
    }

    private <T extends Page> T initElements(T page){
        PageFactory.initElements(driver, page);
        return page;
    }

    public WebDriver getDriver(){
        return driver;
    }
}
