package Pages;

import Managers.PageManager;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class MyMusicPage extends Page {

    public MyMusicPage(PageManager pages) {
        super(pages);
    }

    @FindBy(xpath = "//button[@class='create-assistant-container']")
    private WebElement createPlaylistButton;

    @FindBy(xpath = "//input[@type='text']")
    private WebElement playlistNameField;

    @FindBy(xpath = "//textarea")
    private WebElement listDescriptionsField;

    @FindBy(xpath = "//button[@id='modal_playlist_assistant_submit']")
    private WebElement createButtonOnPopup;

    @FindBy(xpath = "//li//div//a")
    private List<WebElement> allUserPlaylistLinks;

    @FindBy(xpath = "//div[contains(@class,'song')]")
    private List<WebElement> trackList;

    @FindBy(xpath = "(//label[contains(@class,'checkbox')])[1]")
    private WebElement checkboxFieldForDeleteAllFavoriteTracks;

    @FindBy(xpath = "//div[@class='header-action-item']/button")
    private WebElement deleteTracksButton;

    @FindBy(xpath = "//div[@class='toolbar-item']/button")
    private WebElement favoriteTracksMainListenButton;

    @Step("Open create playlist popup")
    public MyMusicPage openCreatePlaylistPopup() {
        wait.until(ExpectedConditions.elementToBeClickable(createPlaylistButton));
        createPlaylistButton.click();
        return this;
    }

    @Step("Entry playlist name")
    public MyMusicPage inputPlaylistName(String name) {
        wait.until(ExpectedConditions.elementToBeClickable(playlistNameField));
        playlistNameField.clear();
        playlistNameField.sendKeys(name);
        return this;
    }

    @Step("Entry playlist description")
    public MyMusicPage inputPlaylistDescriptions(String desc) {
        wait.until(ExpectedConditions.elementToBeClickable(listDescriptionsField));
        listDescriptionsField.clear();
        listDescriptionsField.sendKeys(desc);
        return this;
    }

    @Step("Click create playlist button")
    public void clickCreatePlaylistButton() {
        wait.until(ExpectedConditions.elementToBeClickable(createButtonOnPopup));
        createButtonOnPopup.click();
    }

    @Step("Get all playlists names")
    public List<String> getAllPlaylistsNames() {
        List<String> listOfNames = new ArrayList<>();
        for (WebElement elem : allUserPlaylistLinks) {
            jse.executeScript("arguments[0].scrollIntoView(true);", elem);
            listOfNames.add(elem.getText());
        }
        return listOfNames;
    }

    @Step("Open created playlist")
    public void openCreatedPlaylist(String createdPlaylistName) {
        for (WebElement element : allUserPlaylistLinks) {
            jse.executeScript("arguments[0].scrollIntoView(true);", element);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            if (element.getText().equalsIgnoreCase(createdPlaylistName)) {
                jse.executeScript("arguments[0].click();", element);
                break;
            }
        }
    }

    @Step("Get actual favorite tracks names")
    public List<String> getActualFavoriteTracksNames() {
        List<String> favoriteTracksNamesList = new ArrayList<>();
        wait.until(ExpectedConditions.elementToBeClickable(favoriteTracksMainListenButton));
        removeBanner();
        for (WebElement track : trackList) {
            jse.executeScript("arguments[0].scrollIntoView(true);", track);
            wait.until(ExpectedConditions.elementToBeClickable(track));
            favoriteTracksNamesList.add(track.findElement(By.xpath(".//a[@itemprop='url']/span")).getText());
        }
        return favoriteTracksNamesList;
    }

    @Step("Select checkbox field for delete all favorite tracks")
    public MyMusicPage selectCheckboxFieldForDeleteAllFavoriteTracks() {
        removeBanner();
        wait.until(ExpectedConditions.elementToBeClickable(checkboxFieldForDeleteAllFavoriteTracks));
        checkboxFieldForDeleteAllFavoriteTracks.click();
        return this;
    }

    @Step("Click favorite tracks delete button")
    public MyMusicPage clickFavoriteTracksDeleteButton() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteTracksButton));
        deleteTracksButton.click();
        return this;
    }

    @Step("Accept alert for delete favorite tracks")
    public void acceptAlertForDeleteFavoriteTracks() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'sortable cell-date')]")));
        waitForXHRRequestsLoaded();
        driver.navigate().refresh();
    }

}
