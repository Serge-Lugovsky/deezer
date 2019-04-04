package Pages;

import Managers.PageManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;


public class SearchPage extends Page {

    public SearchPage(PageManager pages) {
        super(pages);
    }

    @FindBy(xpath = "//h1")
    private WebElement searchResultText;

    @FindBy(xpath = "(//nav//a)[2]")
    private WebElement artistsSearchMenu;

    @FindBy(xpath = "//div[contains(@class, 'thumbnail-caption')]//a")
    private List<WebElement> artistNameListAfterSearch;

    @FindBy(xpath = "//div[contains(@class,'song')]")
    private List<WebElement> trackList;

    private static List<String> expectedAddedToPlaylistTracksNamesList = new ArrayList<>();
    private static List<String> expectedFavoriteTracksNamesList = new ArrayList<>();

    @Step("Get expected tracks names in playlist")
    public List<String> getExpectedTracksNamesInPlaylist() {
        return expectedAddedToPlaylistTracksNamesList;
    }

    @Step("Add tracks to playlist and save added tracks names")
    public void addTracksToPlaylistAndSaveTracksNamesToExpected(String playlistName, int sumTracksToAdd) {
        removeBanner();
        for (int count = 0; count < sumTracksToAdd; count++) {
            WebElement track = trackList.get(count);
            scrollWithAmendmentToElement(-200, track);
            WebElement addTrackToPlaylistButton = track.findElement(By
                    .xpath(".//button[@class='action-item-btn datagrid-action']"));
            openContextTrackMenu(track, addTrackToPlaylistButton);
            addTrackToPlaylist(playlistName);
            saveAddedToPlaylistTrackNameToList(track);
        }
    }

    @Step("Hover on track and click add to playlist button")
    private void openContextTrackMenu(WebElement track, WebElement addTrackToPlaylistButton) {
        try {
            actions.clickAndHold(track).moveToElement(track).perform();
            wait.until(ExpectedConditions.elementToBeClickable(addTrackToPlaylistButton));
            addTrackToPlaylistButton.click();
        } catch (StaleElementReferenceException e) {
            openContextTrackMenu(track, addTrackToPlaylistButton);
        }
    }

    @Step("Add track to playlist {playlistName}")
    private void addTrackToPlaylist(String playlistName) {
        WebElement playlistMenuItem = driver.findElement(By.xpath("//span[text()='" + playlistName + "']"));
        wait.until(ExpectedConditions.elementToBeClickable(playlistMenuItem));
        playlistMenuItem.click();
    }

    @Step("Save name of track added to playlist")
    private void saveAddedToPlaylistTrackNameToList(WebElement track) {
        expectedAddedToPlaylistTracksNamesList.add(track.findElement(By.xpath(".//a[@itemprop='url']/span")).getText());
    }

    @Step("Add tracks to favorite and save tracks names to expected list")
    public void addTracksToFavoriteAndSaveTracksNamesToExpected(int sumTracksToAdd) {
        removeBanner();
        for (int count = 0; count < sumTracksToAdd; count++) {
            WebElement track = trackList.get(count);
            WebElement likeButton = track.findElement(By.xpath(".//div[contains(@class, 'cell-love')]/button"));
            scrollWithAmendmentToElement(-200, track);
            clickLikeButton(likeButton);
            waitForXHRRequestsLoaded();
            saveFavoriteTracksNamesToList(track);
        }
    }

    @Step("Click like button for add track to favorite")
    private void clickLikeButton(WebElement likeButton) {
        wait.until(ExpectedConditions.elementToBeClickable(likeButton));
        jse.executeScript("arguments[0].click();", likeButton);
    }

    @Step("Save favorite track name to expected list")
    private void saveFavoriteTracksNamesToList(WebElement track) {
        expectedFavoriteTracksNamesList.add(track.findElement(By.xpath(".//a[@itemprop='url']/span")).getText());
    }

    @Step("Get expected favorite tracks names")
    public List<String> getExpectedFavoriteTracksNames() {
        return expectedFavoriteTracksNamesList;
    }

    @Step("Get confirm search result text")
    public String getSearchResultText() {
        removeBanner();
        try {
            wait.until(ExpectedConditions.visibilityOf(searchResultText));
        } catch (StaleElementReferenceException e) {
            getSearchResultText();
        }
        return searchResultText.getText().replaceAll("^(\\w+\\s+)+«\\s?", "").replaceAll("\\s?»$", "");
    }

    @Step("Move to artists Search menu")
    public void moveToArtistSearchMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(artistsSearchMenu));
        artistsSearchMenu.click();
    }

    @Step("Get artist name after search")
    public String getArtistNameAfterSearch() {
        removeBanner();
        jse.executeScript("arguments[0].scrollIntoView(false);", artistNameListAfterSearch.get(0));
        wait.until(ExpectedConditions.elementToBeClickable(artistNameListAfterSearch.get(0)));
        return artistNameListAfterSearch.get(0).getText().toLowerCase();
    }

}
