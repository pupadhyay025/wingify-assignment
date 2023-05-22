package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageObjects
{
    public HomePageObjects(WebDriver driver)
    {
        PageFactory.initElements(driver,this);
    }
    @FindBy(id = "amount")
    public WebElement amountHeader;
}
