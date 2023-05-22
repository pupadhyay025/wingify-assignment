package objectManager;

import dataProvider.ConfigFileReader;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

import static java.lang.Long.parseLong;

public class DriverManager
{
    private static WebDriver driver;

    public static WebDriver getDriver()
    {
        if(driver==null)
            createDriver();
        return driver;
    }

    public static void createDriver()
    {
        switch(ConfigFileReader.getBrowser().toUpperCase())
        {
            case "FIREFOX":
                driver = new FirefoxDriver();
                break;
            case "CHROME":
                ChromeOptions options = new ChromeOptions();
                options.setAcceptInsecureCerts(true);
                options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
                options.addArguments("start-maximized");
                options.addArguments("--disabled-popup-blocking");
                options.addArguments("--remote-allow-origins=*");
                if(ConfigFileReader.getMode().equalsIgnoreCase("YES"))
                {
                    options.addArguments("--headless");
                    options.addArguments("--disable-gpu");
                }
                driver = new ChromeDriver(options);
                break;
            case "EDGE":
                driver = new EdgeDriver();
                break;
            default:
                System.out.println("No matching browser found");
                System.exit(0);
                break;
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(parseLong(ConfigFileReader.getImplicitWait())));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(parseLong(ConfigFileReader.getPageLoadTimeout())));
        driver.manage().window().maximize();
    }
}

