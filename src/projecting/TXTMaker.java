package projecting;

import fileReading.TinfReading;
import fileWriting.TextWriting;

import java.util.ArrayList;

public class TXTMaker
{
    private String tinfFile;
    private ArrayList<String> pages;
    public TXTMaker(String tinfFile)
    {
        this.tinfFile = tinfFile;
        pages = new ArrayList<>();
        combine();
        TextWriting.write("extracted.txt", pages);
    }

    private void combine()
    {
        TinfReading reading = new TinfReading();
        reading.scan(tinfFile);
        ArrayList<String> titles = reading.getTitles();
        ArrayList<String> texts = reading.getTexts();

        for (int i = 0; i < titles.size(); i++)
        {
            String block = titles.get(i) + "\n\n" + texts.get(i) + "\n\n\n";
            pages.add(block);
        }
    }

}
