package scrapingdata.scraping.event;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import scrapingdata.entity.Event;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScrapingWikiEvent extends ScrapingEvent {
    public ScrapingWikiEvent(String url) {
        super();
    }

    public ScrapingWikiEvent() {
    }



    @Override
    public void getData() throws IOException {
        List<Event> eventList = new ArrayList<>();
        File theFile = new File("src\\main\\java\\json\\Event_Wiki.json");
        String url = "https://vi.wikipedia.org/wiki/Ni%C3%AAn_bi%E1%BB%83u_l%E1%BB%8Bch_s%E1%BB%AD_Vi%E1%BB%87t_Nam";
        Document doc = Jsoup.connect(url).get();
        int count = 0;
        Elements itemsElements = doc.select("div[class=mw-parser-output]");
        for (Element element : itemsElements) {
            Elements e1 = element.getElementsByTag("p");
            for (Element element2 : e1) {
                if(element2.nextElementSibling() != null) {
                    Event event = new Event();
                    if(element2.nextElementSibling().tagName() == "dl") {
                        event.setName(element2.text()+ " " + element2.nextElementSibling().text());
                        event.setTime(element2.getElementsByTag("b").text() + " " + element2.nextElementSibling().getElementsByTag("b").text());
                        event.setDynastyname("abcd");
                        String hrefString = element2.nextElementSibling().getElementsByTag("a").attr("href");
                        Elements eleDocument = Jsoup.connect("https://vi.wikipedia.org/"+hrefString).get().select("div[class=mw-parser-output]");
                        event.setDescription(eleDocument.select("p").get(0).text());

                    }
                    else {
                        event.setName(element2.text());
                        event.setTime(element2.getElementsByTag("b").text());
                        event.setDynastyname("abcd");
                        if(!element2.getElementsByTag("a").attr("class").equals("new")) {
                            String hrefString = element2.getElementsByTag("a").attr("href");
                            Elements eleDocument = Jsoup.connect("https://vi.wikipedia.org/"+hrefString).get().select("div[class=mw-parser-output]");
                            event.setDescription(eleDocument.select("p").get(0).text());

                        }
                    }
                    System.out.println(event.getName());
                    count++;
                    eventList.add(event);
                }
            }
        }
        try {
            FileWriter fileWriter = new FileWriter(theFile);
            Gson pretty_gs = new GsonBuilder().setPrettyPrinting().create();
            pretty_gs.toJson(eventList, fileWriter);
            fileWriter.close();
        }catch (IOException e){
           System.out.println("Error");
        }
    }



    public static void main(String[] args) throws IOException {
        String url = "https://vi.wikipedia.org/wiki/Ni%C3%AAn_bi%E1%BB%83u_l%E1%BB%8Bch_s%E1%BB%AD_Vi%E1%BB%87t_Nam";
        ScrapingWikiEvent eventCrawler = new ScrapingWikiEvent(url);
        eventCrawler.getData();
    }
}