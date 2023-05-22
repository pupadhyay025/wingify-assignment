package scripts;

import context.TestContext;
import dataProvider.ConfigFileReader;
import dataProvider.ReadWriteExcel;
import extentReport.ExtentReport;
import objectManager.DriverManager;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pageObjects.HomePageObjects;
import pageObjects.LoginPageObjects;
import utils.Action;
import utils.Utility;


import java.io.IOException;

public class LoginPageTestCase
{
    WebDriver driver;
    TestContext testContext;
    LoginPageObjects loginPageObjects;
    HomePageObjects homePageObjects;
    Action action;
    Utility utility;
    ExtentReport extentReport;
    ReadWriteExcel excel;

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
        excel = new ReadWriteExcel();
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


    @Test(description="TC001-This test verifies validation message displayed when user login with blank username and password", priority = 1,enabled = true)
    public void tc_1() throws IOException
    {
        String expectedMessage = "Both Username and Password must be present";
        try
        {
            extentReport.createTest("TC001-This test verifies validation message displayed when user login with blank user name and password");
            action.clickButton(loginPageObjects.btnLogin, "LogIn");
            extentReport.info("Login button clicked");
            String actualMessage = loginPageObjects.messagePlaceholderForBothUsernameAndPasswordMustBePresent.getText();
            Assert.assertEquals(actualMessage, expectedMessage, "Application is displaying incorrect message when username and password are not passed while login");
            extentReport.pass("Application is displaying correct message when username and password are not entered while login");
        }
        catch (AssertionError e)
        {
            System.out.println(e.getMessage());
            extentReport.fail(e.getMessage());
        }
        // In case, you want to keep just one Catch block, You can use catch (Throwable t) as Exception and Error extend Throwable
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            extentReport.fail(e.getMessage());
        }
    }


    @Test(description="TC002-This test verifies that user can navigate to homepage when user login with username and password", priority = 2, enabled = true)
    public void tc_2() throws IOException
    {
        String expectedTitle = "Demo App";
        try
        {
            XSSFSheet sheet = excel.getSheet("loginDataSheet1");
            for(int i = 1; i <= sheet.getLastRowNum(); i++)
            {
                extentReport.createTest("TC002-This test verifies that user can navigate to homepage when user login with username and password");
                action.sendKeys(loginPageObjects.txtUsername, sheet.getRow(i).getCell(0).getStringCellValue(), "UserName");
                extentReport.info("Username entered");
                action.sendKeys(loginPageObjects.txtPassword, sheet.getRow(i).getCell(1).getStringCellValue(), "Password");
                extentReport.info("Password entered");
                action.clickButton(loginPageObjects.btnLogin, "LogIn");
                extentReport.info("Login button clicked");

                String actualTitle = driver.getTitle();
                Assert.assertEquals(actualTitle, expectedTitle, "Condition True");
                extentReport.pass("User navigate to home page");
            }

            Thread.sleep(500);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,500)", "");
            Thread.sleep(500);
            driver.get(ConfigFileReader.getUrl());
        }
        catch (AssertionError e)
        {
            System.out.println(e.getMessage());
            extentReport.fail(e.getMessage());
        }
        // In case, you want to keep just one Catch block, You can use catch (Throwable t) as Exception and Error extend Throwable
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            extentReport.fail(e.getMessage());
        }
    }


    @Test(description="TC003-This test verifies validation message displayed when user login with blank username or password", priority = 3, enabled = true)
    public void tc_3() throws IOException
    {
        String expectedMessage = "Username must be present";
        String expectedMessage1 = "Password must be present";

        try
        {
            XSSFSheet sheet = excel.getSheet("loginDataSheet2");
            for(int i = 1; i <= sheet.getLastRowNum(); i++)
            {
                extentReport.createTest("TC003-This test verifies validation message displayed when user login with blank username or password");
                action.sendKeys(loginPageObjects.txtUsername, sheet.getRow(i).getCell(0).getStringCellValue(), "UserName");
                extentReport.info("Username entered");
                action.sendKeys(loginPageObjects.txtPassword, sheet.getRow(i).getCell(1).getStringCellValue(), "Password");
                extentReport.info("Password entered");
                action.clickButton(loginPageObjects.btnLogin, "LogIn");
                extentReport.info("Login button clicked");

                String actualMessage = loginPageObjects.messagePlaceholderForUsernameMustBePresent.getText();
                String actualMessage1 = loginPageObjects.messagePlaceholderForPasswordMustBePresent.getText();

                if(actualMessage.equalsIgnoreCase(expectedMessage))
                {
                    extentReport.pass("Application is displaying correct message when username is not entered while login");
                    extentReport.addScreenshot(driver);
                }
                else if (actualMessage1.equalsIgnoreCase(expectedMessage1))
                {
                    extentReport.pass("Application is displaying correct message when password is not entered while login");
                    extentReport.addScreenshot(driver);
                }
                else
                {
                    extentReport.fail("Username or Password are required validation message is not displayed");
                    extentReport.addScreenshot(driver);
                }
                driver.get(ConfigFileReader.getUrl());
            }
        }
        catch (AssertionError e)
        {
            System.out.println(e.getMessage());
            extentReport.fail(e.getMessage());
        }
        // In case, you want to keep just one Catch block, You can use catch (Throwable t) as Exception and Error extend Throwable
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            extentReport.fail(e.getMessage());
        }
    }
}
