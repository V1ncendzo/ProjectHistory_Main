package scrapingdata.scraping.festival;




import scrapingdata.Base;

public abstract class BaseScrapingFestival extends Base {
    public BaseScrapingFestival(String url) {
        super(url);
    }

    public BaseScrapingFestival() {
    }

    public abstract void getData() ;

}
