import fileWriting.TinfWriting;
import org.openqa.selenium.WebDriver;
import projecting.TXTMaker;
import projecting.TinfMaker;
import scraping.Collecting;
import scraping.Properties;

import java.io.File;

public class Main
{
    public static void main(String[] args)
    {
        WebDriver driver = new Properties().getDriver();
        Collecting collecting = new Collecting(driver, "https://normalsozluk.com/yazar/antiktahterevalli--21606/tanimlar", 127);
        collecting.collectAllPages();

        TinfMaker maker = new TinfMaker(collecting.getEntry(), collecting.getTitle(), collecting.getDate(), collecting.getLike(), collecting.getFav());
        maker.tinf("pages.tinf");

        new TXTMaker("pages.tinf");
    }
}