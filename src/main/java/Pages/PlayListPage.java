package Pages;

import Managers.PageManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;


public class PlayListPage extends Page {

    public PlayListPage(PageManager pages){
        super(pages);
    }

    @FindBy(xpath = "//h1")
    private WebElement playListName;

    @FindBy(xpath = "//div[contains(@class,'song')]")
    private List<WebElement> trackList;

    @FindBy(xpath = "(//div[contains(@class,'cell-duration')]//span)[position()>1]")
    private List<WebElement> durationTracksList;

    @FindBy(xpath = "//button[contains(@class,'action-force')]")
    private WebElement mainPlaylistPlayButton;

    @FindBy(xpath = "//li[@class='header-info-item'][2]")
    private WebElement playlistTotalTime;


    @Step("Get playlist total time")
    public int getPlaylistExpectedDuration(){
        wait.until(ExpectedConditions.visibilityOf(playlistTotalTime));
        return Integer.parseInt(playlistTotalTime.getText().trim().replaceAll("\\s\\w+$", ""));
    }

    @Step("Get playlist duration information")
    public int getPlaylistActualDuration(){
        float sum = 0;
        wait.until(ExpectedConditions.elementToBeClickable(mainPlaylistPlayButton));
        for (WebElement element: durationTracksList){
            jse.executeScript("arguments[0].scrollIntoView(true);", element);
            wait.until(ExpectedConditions.visibilityOf(element));

            sum = sum + Float.parseFloat(element.getText().trim().replaceAll(":","."));
        }
        return Math.round(sum);
    }

    @Step("Get list tracks in playlist")
    public List<String> getActualListTracksInPlaylist(){
        wait.until(ExpectedConditions.elementToBeClickable(mainPlaylistPlayButton));
        List<String> tracksNames = new ArrayList<>();
        for (WebElement element: trackList){
            jse.executeScript("arguments[0].scrollIntoView(true);", element);
            tracksNames.add(element.findElement(By.xpath(".//a[@itemprop='url']/span")).getText());
        }
        return tracksNames;
    }

    @Step("Get created playlist name")
    public String getPlaylistName(){
        wait.until(ExpectedConditions.elementToBeClickable(playListName));
        return playListName.getText();
    }

}
