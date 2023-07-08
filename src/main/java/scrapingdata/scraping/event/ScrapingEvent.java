package scrapingdata.scraping.event;


import scrapingdata.Base;

import java.io.IOException;

public abstract class ScrapingEvent extends Base {
    public ScrapingEvent(String url) {
        super();
    }

    public ScrapingEvent() {
    }

//    protected List<Event> eventList = new ArrayList<>();
//
//    public List<Event> getEventList() {
//        return eventList;
//    }

//    public void setEventList(List<Event> eventList) {
//        this.eventList = eventList;
//    }

    public abstract void getData() throws IOException;

}