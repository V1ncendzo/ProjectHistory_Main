package scrapingdata.scraping.relic;

import scrapingdata.Base;

import java.util.List;


public abstract class BaseScrapingRelic extends Base {
    public BaseScrapingRelic(){
        super();
        this.doc =null;
    }

    public abstract List<String> getRelicLinks(String url);
    public abstract void getRelicData(List<String> allRelicLinks);

}
