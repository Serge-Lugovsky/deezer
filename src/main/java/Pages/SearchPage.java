package Pages;

import Managers.PageManager;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.util.ArrayList;
import java.util.List;


public class SearchPage extends Page {

    public SearchPage(PageManager pages){
        super(pages);
    }

    @FindBy(xpath = "//h1")
    private WebElement searchResultText;

    @FindBy(xpath = "(//nav//a)[2]")
    private WebElement artistSearchMenu;

    @FindBy(xpath = "//div[contains(@class, 'thumbnail-caption')]//a")
    private List<WebElement> artistNameListAfterSearch;

    @FindBy(xpath = "//div[contains(@class,'song')]")
    private List<WebElement> trackList;

    private static List<String> expectedListTracksNames = new ArrayList<>();

    @Step("Get Expected tracks list")
    public List<String> getExpectedListTracksAddedToPlaylist(){
        return expectedListTracksNames;
    }

    @Step("Open tracks context menu for add to playlist")
    public void openTracksContextMenuForAddToPlaylist(String playlistName, int elemNmb){

        for (int count = 0; count < elemNmb ; count++){
            WebElement track = trackList.get(count);
            jse.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -200)", track);
            WebElement addTrackToPlaylistButton = track.findElement(By
                    .xpath(".//button[@class='action-item-btn datagrid-action']"));
            openContextTrackMenu(track, addTrackToPlaylistButton);
            addTrackToPlaylist(playlistName);
            expectedListTracksNames.add(track.findElement(By.xpath(".//a[@itemprop='url']/span")).getText());
        }
    }

    @Step("Hover on track and click add to track button")
    private void openContextTrackMenu(WebElement track, WebElement addTrackToPlaylistButton){
        try {
            actions.moveToElement(track).perform();
            wait.until(ExpectedConditions.elementToBeClickable(addTrackToPlaylistButton));
            addTrackToPlaylistButton.click();
        }catch (StaleElementReferenceException | TimeoutException e){
            openContextTrackMenu(track, addTrackToPlaylistButton);
        }
    }

    @Step("Add track to playlist {playlistName}")
    private void addTrackToPlaylist(String playlistName){
        WebElement playlistMenuItem = driver.findElement(By.xpath("//span[text()='"+playlistName+"']"));
        wait.until(ExpectedConditions.elementToBeClickable(playlistMenuItem));
        playlistMenuItem.click();
    }

    @Step("Get search result text")
    public String getSearchResultText(){
        try {
            wait.until(ExpectedConditions.visibilityOf(searchResultText));
        }catch (StaleElementReferenceException e){
            getSearchResultText();
        }
        return searchResultText.getText().replaceAll("^(\\w+\\s+)+«\\s?", "").replaceAll("\\s?»$", "");
    }

    @Step("Move to artist Search menu")
    public void moveToArtistSearchMenu(){
        wait.until(ExpectedConditions.elementToBeClickable(artistSearchMenu));
        artistSearchMenu.click();
    }

    @Step("Get artist name after search")
    public String getArtistNameAfterSearch(){
        jse.executeScript("arguments[0].scrollIntoView(false);", artistNameListAfterSearch.get(0));
        wait.until(ExpectedConditions.elementToBeClickable(artistNameListAfterSearch.get(0)));
        return artistNameListAfterSearch.get(0).getText().toLowerCase();
    }

}
