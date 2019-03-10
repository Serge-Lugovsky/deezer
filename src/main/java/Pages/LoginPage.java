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

    @FindBy(xpath = "(//input)[1]")
    private WebElement emailField;

    @FindBy(xpath = "(//input)[2]")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@id='login_form_submit']")
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
