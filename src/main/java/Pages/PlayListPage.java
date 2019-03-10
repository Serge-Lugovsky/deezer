package Pages;

import Managers.PageManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PlayListPage extends Page {

    public PlayListPage(PageManager pages){
        super(pages);
    }

    @FindBy(xpath = "//h1")
    private WebElement playListName;

    @Step("Get created playlist name")
    public String getPlaylistName(){
        wait.until(ExpectedConditions.elementToBeClickable(playListName));
        return playListName.getText();
    }

}
