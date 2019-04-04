package Pages;

import Managers.PageManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


public class ChannelsPage extends Page {

    public ChannelsPage(PageManager pages) {
        super(pages);
    }

    @FindBy(xpath = "//a[@class='channel-card']")
    private List<WebElement> channelsList;

    private static String expectedChannelLink;
    private static String chanelName;

    @Step("Save expected link from page")
    public void saveExpectedChannelLinkFromPage() {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//a[@class='channel-card']"), 30));
        WebElement firstChanel = channelsList.get(0);
        scrollWithAmendmentToElement(-150, firstChanel);
        wait.until(ExpectedConditions.elementToBeClickable(firstChanel));
        expectedChannelLink = firstChanel.getAttribute("href");
    }

    @Step("Get expected chanel link")
    public String getExpectedChannelLink() {
        return expectedChannelLink;
    }

    @Step("Open chanel page and save chanel name")
    public void openChanelPageAndSaveChanelName() {
        WebElement firstChanel = channelsList.get(0);
        scrollWithAmendmentToElement(-150, firstChanel);
        wait.until(ExpectedConditions.elementToBeClickable(firstChanel));
        chanelName = firstChanel.getText();
        firstChanel.click();
    }

    @Step("Get actual chanel link")
    public String getActualChannelLink() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[text()='" + chanelName + "']")));
        return driver.getCurrentUrl();
    }

}
