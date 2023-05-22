package utils;

import objectManager.DriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Action
{
    WebDriver driver;
    JavascriptExecutor exec;

    public Action()
    {
        this.driver= DriverManager.getDriver();
        exec = (JavascriptExecutor)driver;
    }
    //Clicking button
    public void clickButton(WebElement ele, String eleName) throws Exception
    {
        Utility.waitForElementVisibleAndClickable(driver,ele,30);
        Utility.highlightElement(driver, ele);
        ele.click();
    }
    //Entering text in text box
    public void sendKeys(WebElement ele, String testData,String eleName) throws InterruptedException
    {
        Utility.waitForVisibilityOfElement(driver,ele);
        Utility.highlightElement(driver, ele);
        ele.clear();
        ele.sendKeys(testData);
    }

    //Clicking hyperlink
    public void clickLink(WebElement ele,String eleName) throws InterruptedException
    {
        Utility.waitForVisibilityOfElement(driver,ele);
        Utility.highlightElement(driver, ele);
        ele.click();
    }
}
