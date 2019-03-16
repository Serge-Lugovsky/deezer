package Pages;

import Managers.PageManager;
import io.qameta.allure.Step;
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
            jse.executeScript("arguments[0].scrollIntoView(false);", elem);
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
                element.click();
                break;
            }
        }
    }

    @Step("Get list favorite tracks")
    public int getListFavoriteTracks(){
        List<WebElement> favTracksList = new ArrayList<>();
        wait.until(ExpectedConditions.elementToBeClickable(favoriteTracksMainListenButton));
        for (WebElement element: trackList){
            jse.executeScript("arguments[0].scrollIntoView(true);", element);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            favTracksList.add(element.findElement(By.xpath(".//div[contains(@class, 'cell-love')]/button")));
        }
        return favTracksList.size();
    }

}
