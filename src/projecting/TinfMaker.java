package projecting;

import fileWriting.TinfWriting;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class TinfMaker
{
    private ArrayList<String> entry, title, date, like, fav, header, text;
    public TinfMaker(ArrayList<String> entry, ArrayList<String> title, ArrayList<String> date, ArrayList<String> like, ArrayList<String> fav)
    {
        this.entry = entry;
        this.title = title;
        this.date = date;
        this.like = like;
        this.fav = fav;

        header = new ArrayList<>();
        text = new ArrayList<>();

        textMake();
    }

    private void textMake()
    {
        for (int i = 0; i < entry.size(); i++)
        {
            String temp = entry.get(i) + "\n\ndate: " + date.get(i) + "\nlike(s): " + like.get(i) + "\nfav(s): " + fav.get(i);
            text.add(temp);
            header.add(title.get(i));
        }
    }

    public void tinf(String name)
    {
        File file = new File(name);

        TinfWriting writing = new TinfWriting();
        writing.write(file.getPath(), header, text);
    }

}
