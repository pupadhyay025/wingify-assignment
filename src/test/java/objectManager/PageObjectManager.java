package objectManager;

import org.openqa.selenium.WebDriver;
import pageObjects.HomePageObjects;
import pageObjects.LoginPageObjects;

public class PageObjectManager
{
    private WebDriver driver;
    private LoginPageObjects loginPageObjects;
    private HomePageObjects homePageObjects;

    public PageObjectManager(org.openqa.selenium.WebDriver driver)
    {
        this.driver=driver;
    }

    public LoginPageObjects getLoginPageObjects()
    {
        return (loginPageObjects == null) ? loginPageObjects = new LoginPageObjects(driver): loginPageObjects;
    }

    public HomePageObjects getHomePageObjects()
    {
        return (homePageObjects == null) ? homePageObjects = new HomePageObjects(driver) : homePageObjects;
    }
}

