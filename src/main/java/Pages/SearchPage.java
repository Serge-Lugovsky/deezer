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

    private static List<String> expectedAddedToPlaylistTracksNamesList = new ArrayList<>();
    private static List<String> expectedFavoriteTracksNamesList = new ArrayList<>();

    @Step("Get expected tracks added to playlist")
    public List<String> getExpectedListTracksAddedToPlaylist(){
        return expectedAddedToPlaylistTracksNamesList;
    }

    @Step("Add tracks to playlist and save added tracks names")
    public void addTracksToPlaylistAndSaveTracksNames(String playlistName, int elemNmb){
        jse.executeScript("$('div.ads-bottom-alone').remove();");
        for (int count = 0; count < elemNmb ; count++){
            WebElement track = trackList.get(count);
            jse.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -200)", track);
            WebElement addTrackToPlaylistButton = track.findElement(By
                    .xpath(".//button[@class='action-item-btn datagrid-action']"));
            openContextTrackMenu(track, addTrackToPlaylistButton);
            addTrackToPlaylist(playlistName);
            saveAddedToPlaylistTrackNameToList(track);
        }
    }

    @Step("Hover on track and click add to track button")
    private void openContextTrackMenu(WebElement track, WebElement addTrackToPlaylistButton){
        try {
            actions.clickAndHold(track).moveToElement(track).perform();
            wait.until(ExpectedConditions.elementToBeClickable(addTrackToPlaylistButton));
            addTrackToPlaylistButton.click();
        }catch (StaleElementReferenceException e){
            openContextTrackMenu(track, addTrackToPlaylistButton);
        }
    }

    @Step("Add track to playlist {playlistName}")
    private void addTrackToPlaylist(String playlistName){
        WebElement playlistMenuItem = driver.findElement(By.xpath("//span[text()='"+playlistName+"']"));
        wait.until(ExpectedConditions.elementToBeClickable(playlistMenuItem));
        playlistMenuItem.click();
    }

    @Step("Save name of track added to playlist")
    private void saveAddedToPlaylistTrackNameToList(WebElement track){
        expectedAddedToPlaylistTracksNamesList.add(track.findElement(By.xpath(".//a[@itemprop='url']/span")).getText());
    }

    @Step("Add tracks to favorite")
    public void addSelectedTrackToFavorite(int sumTracks){
        jse.executeScript("$('div.ads-bottom-alone').remove();");
        for (int count = 0; count < sumTracks ; count++){
            WebElement track = trackList.get(count);
            WebElement likeButton = track.findElement(By.xpath(".//div[contains(@class, 'cell-love')]/button"));
            jse.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -200)", track);
            wait.until(ExpectedConditions.elementToBeClickable(likeButton));
            jse.executeScript("arguments[0].click();", likeButton);
            saveFavoriteTracksNamesToList(track);
        }
    }

    @Step("Save favorite tracks names to list")
    private void saveFavoriteTracksNamesToList(WebElement track){
        expectedFavoriteTracksNamesList.add(track.findElement(By.xpath(".//a[@itemprop='url']/span")).getText());
    }

    @Step("Get expected favorite tracks")
    public List<String> getExpectedFavoriteTracksNames(){
        return expectedFavoriteTracksNamesList;
    }

    @Step("Get confirm search result text")
    public String getSearchResultText(){
        jse.executeScript("$('div.ads-bottom-alone').remove();");
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
        jse.executeScript("$('div.ads-bottom-alone').remove();");
        jse.executeScript("arguments[0].scrollIntoView(false);", artistNameListAfterSearch.get(0));
        wait.until(ExpectedConditions.elementToBeClickable(artistNameListAfterSearch.get(0)));
        return artistNameListAfterSearch.get(0).getText().toLowerCase();
    }

}
