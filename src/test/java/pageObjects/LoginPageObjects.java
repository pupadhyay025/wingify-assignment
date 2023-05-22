package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObjects
{
    public LoginPageObjects(WebDriver driver)
    {
        PageFactory.initElements(driver,this);
    }
    @FindBy(id = "username")
    public WebElement txtUsername;
    @FindBy(id = "password")
    public WebElement txtPassword;
    @FindBy(id = "log-in")
    public WebElement btnLogin;
    @FindBy(xpath = "//*[@class = \"alert alert-warning\"]")
    public WebElement messagePlaceholderForUsernameMustBePresent;
    @FindBy(xpath = "//*[@class = \"alert alert-warning\"]")
    public WebElement messagePlaceholderForPasswordMustBePresent;
    @FindBy(xpath = "//*[@class = \"alert alert-warning\"]")
    public WebElement messagePlaceholderForBothUsernameAndPasswordMustBePresent;
}
