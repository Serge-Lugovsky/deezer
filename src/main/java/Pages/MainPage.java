package Pages;

import Managers.PageManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class MainPage extends Page {

    public MainPage(PageManager pages) {
        super(pages);
    }

    @FindBy(xpath = "//button[@class='topbar-profile']")
    private WebElement profileButton;

    @FindBy(xpath = "//a[@href='/account']")
    private WebElement accountSettingsLink;

    @FindBy(xpath = "(//a[contains(@class,'sidebar-nav-link')])[5]")
    private WebElement favoriteTracksMenuLink;

    @FindBy(xpath = "(//a[contains(@class,'sidebar-nav-link')])[6]")
    private WebElement playlistsMenuButton;

    @FindBy(xpath = "//input[@type='search']")
    private WebElement searchField;

    @FindBy(xpath = "//button[@class='topbar-search-submit']")
    private WebElement searchButton;

    @FindBy(xpath = "(//a[contains(@class,'sidebar-nav-link')])[2]")
    private WebElement channelsPageLink;

    @Step("Move to Channels page")
    public void moveToChannelsPage() {
        wait.until(ExpectedConditions.elementToBeClickable(channelsPageLink));
        channelsPageLink.click();
    }

    @Step("Entry search text into search field")
    public MainPage inputSearchText(String searchData) {
        wait.until(ExpectedConditions.elementToBeClickable(searchField));
        searchField.clear();
        searchField.sendKeys(searchData);
        return this;
    }

    @Step("Click search button")
    public void clickSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();
    }

    @Step("Check login")
    public boolean verifyLoginButtonIsDisplayed() {
        wait.until(ExpectedConditions.elementToBeClickable(profileButton));
        return profileButton.isDisplayed();
    }

    @Step("Open profile menu")
    public MainPage openProfileMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(profileButton));
        profileButton.click();
        return this;
    }

    @Step("Move to account settings page")
    public void moveToAccountPage() {
        wait.until(ExpectedConditions.elementToBeClickable(accountSettingsLink));
        accountSettingsLink.click();
    }

    @Step("Move to playlists menu")
    public void moveToPlaylistsMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(playlistsMenuButton));
        playlistsMenuButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='create-assistant']")));
    }

    @Step("Move to favorite tracks menu")
    public void moveToMyFavoriteTracksMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(favoriteTracksMenuLink));
        favoriteTracksMenuLink.click();
    }

}
