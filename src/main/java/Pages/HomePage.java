package Pages;

import Managers.PageManager;
import io.qameta.allure.Step;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends Page {

    public HomePage(PageManager pages){
        super(pages);
    }

    @FindBy(xpath = "(//a[starts-with(@href, '/login')])[1]")
    private WebElement loginLink;

    @Step("Move to login page")
    public void moveToLoginPage(){
        wait.until(ExpectedConditions.elementToBeClickable(loginLink));
        loginLink.click();
    }

    @Step("Check logout")
    public boolean verifyLogout(){
        try {
            wait.until(ExpectedConditions.elementToBeClickable(loginLink));
        }catch (StaleElementReferenceException e){
            verifyLogout();
        }
        return loginLink.isDisplayed();
    }

}
