package Pages;

import Managers.PageManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class MainPage extends Page {

    public MainPage(PageManager pages){
        super(pages);
    }

    @FindBy(xpath = "//button[@class='topbar-profile']")
    private WebElement profileButton;

    @FindBy(xpath = "//a[@href='/account']")
    private WebElement accountSettingsLink;

    @FindBy(xpath = "(//a/span)[3]")
    private WebElement logoutLink;

    @FindBy(xpath = "(//a[contains(@class, 'sidebar-nav-link')])[5]")
    private WebElement favoriteTracksLink;

    @FindBy(xpath = "(//a[contains(@class,'sidebar-nav-link')])[6]")
    private WebElement playlistButton;

    @FindBy(xpath = "//input[@type='search']")
    private WebElement searchField;

    @FindBy(xpath = "//button[@class='topbar-search-submit']")
    private WebElement searchButton;

    @FindBy(xpath = "(//a[contains(@class,'sidebar-nav-link')])[2]")
    private WebElement channelsPageLink;

    @Step("Move to Channels page")
    public void moveToChannelsPage(){
        wait.until(ExpectedConditions.elementToBeClickable(channelsPageLink));
        channelsPageLink.click();
    }

    @Step("Search tracks")
    public void searchTracks(String searchData){
        wait.until(ExpectedConditions.elementToBeClickable(searchField));
        searchField.clear();
        searchField.sendKeys(searchData);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();
    }

    @Step("Check login")
    public boolean verifyLogin(){
        wait.until(ExpectedConditions.elementToBeClickable(profileButton));
        return profileButton.isDisplayed();
    }

    @Step("Open profile menu")
    public MainPage openProfileMenu(){
        wait.until(ExpectedConditions.elementToBeClickable(profileButton));
        profileButton.click();
        return this;
    }

    @Step("Move to account settings page")
    public void moveToAccountPage(){
        wait.until(ExpectedConditions.elementToBeClickable(accountSettingsLink));
        accountSettingsLink.click();
    }

    @Step("Logout")
    public void logout(){
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink));
        logoutLink.click();
    }

    @Step("Open playlist menu")
    public void moveToPlaylistMenu(){
        wait.until(ExpectedConditions.elementToBeClickable(playlistButton));
        playlistButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='create-assistant']")));
    }

    @Step("Move to favorite tracks page")
    public void moveToMyFavoriteTracksMenu(){
        wait.until(ExpectedConditions.elementToBeClickable(favoriteTracksLink));
        favoriteTracksLink.click();
    }

}
