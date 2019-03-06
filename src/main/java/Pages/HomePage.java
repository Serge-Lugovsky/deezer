package Pages;

import Managers.PageManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends Page {

    public HomePage(PageManager pages){
        super(pages);
    }

    @FindBy(xpath = "//a/span[text()='Log in']")
    private WebElement loginLink;

    @Step("Move to login page")
    public void moveToLoginPage(){
        wait.until(ExpectedConditions.elementToBeClickable(loginLink));
        loginLink.click();
    }

    @Step(value = "Check logout")
    public boolean verifyLogout(){
        wait.until(ExpectedConditions.elementToBeClickable(loginLink));
        return loginLink.isDisplayed();
    }

}
