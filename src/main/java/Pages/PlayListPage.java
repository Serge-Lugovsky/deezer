package Pages;

import Managers.PageManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PlayListPage extends Page {

    public PlayListPage(PageManager pages) {
        super(pages);
    }

    @FindBy(xpath = "//h1")
    private WebElement playListName;

    @FindBy(xpath = "//div[contains(@class,'song')]")
    private List<WebElement> trackList;

    @FindBy(xpath = "//button[contains(@class,'action-force')]")
    private WebElement mainPlaylistPlayButton;

    @FindBy(xpath = "//li[@class='header-info-item'][2]")
    private WebElement playlistTotalTime;

    @FindBy(xpath = "//div[contains(@class,'cell-info')]/h2/a")
    private WebElement trackNameInContextMenu;

    private static LocalTime playlistDuration = LocalTime.of(0, 0, 0);
    private static String trackNameFromPage;

    @Step("Get playlist expected duration")
    public LocalTime getExpectedPlaylistDuration() {
        LocalTime localTime = LocalTime.of(0, 0, 0);
        wait.until(ExpectedConditions.visibilityOf(playlistTotalTime));

        List<Integer> matchResult = new ArrayList<>();
        Pattern p = Pattern.compile("\\d{1,2}");
        Matcher matcher = p.matcher(playlistTotalTime.getText());
        while (matcher.find()) {
            matchResult.add(Integer.parseInt(matcher.group()));
        }

        if (matchResult.size() == 2) {
            localTime = localTime.plusHours(matchResult.get(0));
            localTime = localTime.plusMinutes(matchResult.get(1));
        } else {
            localTime = localTime.plusMinutes(matchResult.get(0));
        }
        return localTime;
    }

    @Step("Get actual playlist duration information")
    public LocalTime getActualPlaylistDuration() {
        waitPlaylistLoaded();
        for (WebElement element : trackList) {
            getSumOfTracksDuration(element);
        }
        return playlistDuration.minusSeconds(playlistDuration.getSecond());
    }

    @Step("Get tracks actual duration")
    private void getSumOfTracksDuration(WebElement element) {
        try {
            jse.executeScript("arguments[0].scrollIntoView(true);", element);
            WebElement trackDurationInfo = element.findElement(By.xpath(".//div[contains(@class,'cell-duration')]/span"));

            LocalTime trackTime = LocalTime.parse("00:" + trackDurationInfo.getText().trim());
            playlistDuration = playlistDuration.plusMinutes(trackTime.getMinute());
            playlistDuration = playlistDuration.plusSeconds(trackTime.getSecond());

        } catch (StaleElementReferenceException e) {
            getSumOfTracksDuration(element);
        }

    }

    @Step("Wait for dynamic playlist loaded")
    private void waitPlaylistLoaded() {
        wait.until(ExpectedConditions.elementToBeClickable(mainPlaylistPlayButton));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(@class,'song')]")));
    }

    @Step("Get actual tracks names in playlist")
    public List<String> getActualTracksNamesInPlaylist() {
        waitPlaylistLoaded();
        List<String> tracksNames = new ArrayList<>();
        for (WebElement element : trackList) {
            scrollToElement(element);
            tracksNames.add(element.findElement(By.xpath(".//a[@itemprop='url']/span")).getText());
        }
        return tracksNames;
    }

    @Step("Scroll to element")
    private void scrollToElement(WebElement element) {
        try {
            jse.executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (StaleElementReferenceException e) {
            scrollToElement(element);
        }
    }

    @Step("Get created playlist name")
    public String getNameOfCreatedPlaylist() {
        try {
            wait.until(ExpectedConditions.visibilityOf(playListName));
        } catch (StaleElementReferenceException e) {
            getNameOfCreatedPlaylist();
        }
        return playListName.getText();
    }

    @Step("Open track context menu")
    public PlayListPage openTrackContextMenu() {
        removeBanner();
        WebElement track = trackList.get(0);
        WebElement trackContextMenuButton = track.findElement(By.xpath(".//button[@aria-label='View menu']"));

        scrollWithAmendmentToElement(-200, track);
        actions.clickAndHold(track).moveToElement(track).perform();
        wait.until(ExpectedConditions.elementToBeClickable(trackContextMenuButton));
        trackContextMenuButton.click();
        return this;
    }

    @Step("Get actual track name from context menu")
    public String getActualTrackNameFromContextMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(trackNameInContextMenu));
        return trackNameInContextMenu.getText();
    }

    @Step("Get expected track name from page")
    public String getExpectedTrackNameFromPage() {
        return trackNameFromPage;
    }

    @Step("Save expected track name from page")
    public void saveExpectedTrackName() {
        wait.until(ExpectedConditions.elementToBeClickable(mainPlaylistPlayButton));
        WebElement track = trackList.get(0);
        scrollWithAmendmentToElement(-200, track);
        trackNameFromPage = track.findElement(By.xpath(".//a[@itemprop='url']/span")).getText();
    }

}
