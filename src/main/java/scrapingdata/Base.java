package scrapingdata;

import org.jsoup.nodes.Document;

public abstract class Base {
    protected String url;
    protected Document doc = null;

    public Base(){
    }
    public Base(String url){
        this.url = url;
    }


}
