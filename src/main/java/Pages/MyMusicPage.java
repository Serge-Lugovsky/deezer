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

    public MyMusicPage(PageManager pages){
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

    @FindBy(xpath = "(//input[@class='checkbox-input'])[1]/parent::label")
    private WebElement checkboxInputForDeleteFavoriteTracks;

    @FindBy(xpath = "//div[@class='header-action-item']/button")
    private WebElement deleteTracksButton;

    @FindBy(xpath = "//div[@class='toolbar-item']/button")
    private WebElement favoriteTracksMainListenButton;

    @Step("Open create playlist popup")
    public MyMusicPage openCreatePlaylistPopup(){
        wait.until(ExpectedConditions.elementToBeClickable(createPlaylistButton));
        createPlaylistButton.click();
        return this;
    }

    @Step("Entry playlist name")
    public MyMusicPage inputPlaylistName(String name){
        wait.until(ExpectedConditions.elementToBeClickable(playlistNameField));
        playlistNameField.clear();
        playlistNameField.sendKeys(name);
        return this;
    }

    @Step("Entry playlist description")
    public MyMusicPage inputPlaylistDescriptions(String desc){
        wait.until(ExpectedConditions.elementToBeClickable(listDescriptionsField));
        listDescriptionsField.clear();
        listDescriptionsField.sendKeys(desc);
        return this;
    }

    @Step("Click create playlist button")
    public void clickCreatePlaylist(){
        wait.until(ExpectedConditions.elementToBeClickable(createButtonOnPopup));
        createButtonOnPopup.click();
    }

    @Step("Get all playlist names")
    public List<String> getListPlaylistName(){
        List<String> listOfNames = new ArrayList<>();
        for (WebElement elem : allUserPlaylistLinks){
            jse.executeScript("arguments[0].scrollIntoView(true);", elem);
            listOfNames.add(elem.getText());
        }
        return listOfNames;
    }

    @Step("Open playlist after added tracks")
    public void openCreatedPlaylist(String createdPlaylistName){
        for (WebElement element: allUserPlaylistLinks){
            jse.executeScript("arguments[0].scrollIntoView(true);", element);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            if (element.getText().equalsIgnoreCase(createdPlaylistName)){
                jse.executeScript("arguments[0].click();", element);
                break;
            }
        }
    }

    @Step("Get list favorite tracks")
    public List<String> getActualFavoriteTracksNames(){
        List<String> favoriteTracksNamesList = new ArrayList<>();
        wait.until(ExpectedConditions.elementToBeClickable(favoriteTracksMainListenButton));
        jse.executeScript("$('div.ads-bottom-alone').remove();");
        for (WebElement track: trackList){
            jse.executeScript("arguments[0].scrollIntoView(true);", track);
            wait.until(ExpectedConditions.elementToBeClickable(track));
            favoriteTracksNamesList.add(track.findElement(By.xpath(".//a[@itemprop='url']/span")).getText());
        }
        return favoriteTracksNamesList;
    }

    @Step("Select favorite tracks for delete")
    public MyMusicPage selectFavoriteTracks(){
        jse.executeScript("$('div.ads-bottom-alone').remove();");
        wait.until(ExpectedConditions.elementToBeClickable(checkboxInputForDeleteFavoriteTracks));
        checkboxInputForDeleteFavoriteTracks.click();
        return this;
    }

    @Step("Delete favorite tracks")
    public MyMusicPage deleteFavoriteTracks(){
        wait.until(ExpectedConditions.elementToBeClickable(deleteTracksButton));
        deleteTracksButton.click();
        return this;
    }

    @Step("Confirm delete favorite tracks")
    public void confirmDeleteFavoriteTracks(){
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        wait.until(ExpectedConditions.elementToBeClickable(favoriteTracksMainListenButton));
        driver.navigate().refresh();
    }

}
