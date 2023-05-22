package scripts;

import context.TestContext;
import dataProvider.ConfigFileReader;
import extentReport.ExtentReport;
import objectManager.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pageObjects.HomePageObjects;
import pageObjects.LoginPageObjects;
import utils.Action;
import utils.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomePageTestCase
{
    WebDriver driver;
    TestContext testContext;
    LoginPageObjects loginPageObjects;
    HomePageObjects homePageObjects;
    Action action;
    Utility utility;
    ExtentReport extentReport;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() throws IOException
    {
        driver = DriverManager.getDriver();
        driver.get(ConfigFileReader.getUrl());
        testContext = new TestContext();
        loginPageObjects = testContext.getPageObjectManager().getLoginPageObjects();
        homePageObjects = testContext.getPageObjectManager().getHomePageObjects();
        action = testContext.getActionObject();
        utility = new Utility();
        extentReport = testContext.getExtentReport();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite()
    {
        extentReport.flush();
        driver.quit();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod()
    {

    }

    @AfterMethod(alwaysRun = true)
    public void closeTestMethod(ITestResult result) throws IOException
    {
        if (ITestResult.FAILURE == result.getStatus())
        {
            System.out.println("Failed Status Check");
            extentReport.addScreenshot(driver);
        }
    }


    @Test(description="TC001-This test verifies that values are sorted after clicking the amount header in the transaction table", priority = 1, enabled = true)
    public void tc_1() throws IOException
    {
        try
        {
            extentReport.createTest("TC001-This test verifies that values are sorted after clicking the amount header in the transaction table");
            Utility.login(driver);
            extentReport.info("User logged in");
            List<WebElement> rows = driver.findElements(By.xpath("//table[@id = 'transactionsTable']//tbody//tr//td[5]"));
            List <String> array = new ArrayList<>();
            for(int i = 1; i <= rows.size(); i++)
            {
                WebElement val = driver.findElement(By.xpath("//table[@id = 'transactionsTable']//tr["+i+"]//td[5]"));
                String values = val.getText();
                array.add(values);
            }

            action.clickLink(homePageObjects.amountHeader, "Amount");
            extentReport.info("Amount header clicked");

            List <WebElement> rows1 = driver.findElements(By.xpath("//table[@id = 'transactionsTable']//tbody//tr//td[5]"));
            List <String> sortArray = new ArrayList<>();
            for(int i = 1; i <= rows1.size(); i++)
            {
                WebElement val = driver.findElement(By.xpath("//table[@id = 'transactionsTable']//tr["+i+"]//td[5]"));
                String values = val.getText();
                sortArray.add(values);
            }
            //scroll down
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,500)", "");

            Thread.sleep(3000);
            boolean equalArray = array.equals(sortArray);
            Assert.assertFalse(equalArray, "Values are sorted after clicking the amount header");
            extentReport.pass("Values are sorted after clicking the amount header");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            extentReport.fail(e.getMessage());
            extentReport.addScreenshot(driver);
        }
    }
}
