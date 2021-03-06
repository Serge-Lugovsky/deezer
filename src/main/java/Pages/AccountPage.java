package Pages;

import Managers.PageManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;


public class AccountPage extends Page {

    public AccountPage(PageManager pages) {
        super(pages);
    }

    @FindBy(xpath = "(//nav//a)[1]")
    private WebElement myInformationMenuLink;

    @FindBy(xpath = "//input[@class='form-control']|//select|//input[@name='sex']")
    private List<WebElement> userDataInputList;

    @Step("Move to My Information menu")
    public void moveToMyInformationMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(myInformationMenuLink));
        myInformationMenuLink.click();
    }

    @Step("Get actual user information")
    public List<String> getActualUserPersonalInformation() {
        List<String> dataList = new ArrayList<>();
        wait.until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(By.xpath("//input[@class='form-control']|//select|//input[@name='sex']")));
        for (WebElement elm : userDataInputList) {
            jse.executeScript("arguments[0].scrollIntoView(true);", elm);
            if (elm.getAttribute("type").equals("radio")) {
                if (elm.isSelected()) {
                    dataList.add(elm.getAttribute("value").equals("M") ? "Male" : "Female");
                }
            } else {
                dataList.add(elm.getAttribute("value"));
            }
        }
        return dataList;
    }

}
