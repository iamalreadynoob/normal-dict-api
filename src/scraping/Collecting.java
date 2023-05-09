package scraping;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stringHandling.ShortedProcesses;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Collecting
{
    private String urlRoot;
    private int lastPage;
    private WebDriver driver;
    private ArrayList<String> entry, title, date, like, fav;
    public Collecting(WebDriver driver, String url, int lastPage)
    {
        urlRoot = url;
        this.lastPage = lastPage;
        this.driver = driver;

        entry = new ArrayList<>();
        title = new ArrayList<>();
        date = new ArrayList<>();
        like = new ArrayList<>();
        fav = new ArrayList<>();

    }

    public ArrayList<String> getEntry() {return entry;}
    public ArrayList<String> getTitle() {return title;}
    public ArrayList<String> getDate() {return date;}
    public ArrayList<String> getLike() {return like;}
    public ArrayList<String> getFav() {return fav;}

    public void collectAllPages()
    {
        int loc = 1;

        while (loc <= lastPage)
        {
            if (loc == 1) driver.get(urlRoot);
            else driver.get(urlRoot + "?page=" + loc);

            retrieve();

            loc++;
        }
    }
    private void retrieve()
    {
        Duration duration = Duration.ofSeconds(30);
        WebDriverWait driverWait = new WebDriverWait(driver, duration);
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='entrytext']")));

        List<WebElement> entries = driver.findElements(By.xpath("//div[@class='entrytext']"));
        List<WebElement> titles = driver.findElements(By.xpath("//span[@itemprop='name']"));
        List<WebElement> dates = driver.findElements(By.xpath("//a[@class='entrydate']"));
        List<WebElement> likes = driver.findElements(By.xpath("//span[@class='mx-2']"));

        for (WebElement e: entries) entry.add(e.getText());
        for (WebElement t: titles) title.add(t.getText());
        for (int i = 0; i < likes.size(); i++)
        {
            if (i % 2 == 0) like.add(likes.get(i).getText().substring(1, likes.get(i).getText().length() - 1));
            else
            {
                fav.add(likes.get(i).getText().substring(1, likes.get(i).getText().length() - 1));
                date.add(dates.get(i).getText());
            }
        }
    }

}
