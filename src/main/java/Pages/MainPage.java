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

    @FindBy(xpath = "//button[contains(@class, 'topbar-profile')]")
    private WebElement profileButton;

    @FindBy(xpath = "//a/span[text()='Account Settings']")
    private WebElement accountSettingsLink;

    @FindBy(xpath = "//a/span[text()='Log out']")
    private WebElement logoutLink;

    @FindBy(xpath = "//a//span[text()='Playlists']")
    private WebElement playlistButton;

    @Step(value = "Check login")
    public boolean verifyLogin(){
        wait.until(ExpectedConditions.elementToBeClickable(profileButton));
        return profileButton.isDisplayed();
    }

    @Step(value = "Open profile menu")
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

    @Step(value = "Logout")
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

}
