package Pages;

import Managers.PageManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends Page {

    public LoginPage(PageManager pages){
        super(pages);
    }

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button/span[text()='Log in']")
    private WebElement loginButton;

    @Step("Entry email")
    public LoginPage inputEmail(String email){
        wait.until(ExpectedConditions.elementToBeClickable(emailField));
        emailField.clear();
        emailField.sendKeys(email);
        return this;
    }

    @Step("Entry password")
    public LoginPage inputPassword(String password){
        wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        passwordField.clear();
        passwordField.sendKeys(password);
        return this;
    }

    @Step("Click login button")
    public void clickLogin(){
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }

}
