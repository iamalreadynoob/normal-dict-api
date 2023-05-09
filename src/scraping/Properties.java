package scraping;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Properties
{

    private WebDriver driver;

    public Properties()
    {
        System.setProperty("webdriver.gecko.driver", "geckodriver");
        driver = new FirefoxDriver();
    }

    public WebDriver getDriver() {return driver;}
}
