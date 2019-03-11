package Pages;

import Managers.PageManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    @Step("Get search result text")
    public String getSearchResultText(){
        wait.until(ExpectedConditions.visibilityOf(searchResultText));
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
