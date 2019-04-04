package Pages;

import Managers.PageManager;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;
    JavascriptExecutor jse;

    Page(PageManager pages) {
        driver = pages.getDriver();
        wait = new WebDriverWait(driver, 10);
        actions = new Actions(driver);
        jse = (JavascriptExecutor) driver;
    }

    @Step("Scroll to element with amendment")
    void scrollWithAmendmentToElement(int yCoordinate, WebElement element) {
        jse.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, " + yCoordinate + ");", element);
    }

    @Step("Remove banner")
    void removeBanner() {
        jse.executeScript("$('div.ads-bottom-alone').remove();");
    }

    @Step("Wait for XHR requests loaded")
    void waitForXHRRequestsLoaded() {
        ExpectedCondition<Boolean> jQueryLoad = driver -> {
            try {
                return ((Long) jse.executeScript("return jQuery.active") == 0);
            } catch (Exception e) {
                // no jQuery present
                return true;
            }
        };
        ExpectedCondition<Boolean> jsLoad = driver -> jse.executeScript("return document.readyState")
                .toString().equals("complete");

        wait.until(ExpectedConditions.and(
                jQueryLoad,
                jsLoad
        ));
    }
}
