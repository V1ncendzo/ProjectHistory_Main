package scrapingdata.entity;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Event extends BaseEntity {
    public Event() {

    }
    private Dynasty dynasty = new Dynasty();
    public Event(String name, String time, String description, Dynasty dynasty) {
        super(name, time, description);
        this.dynasty = dynasty;
    }
    public void setDynastyname(String dynastyName) {
        this.dynasty.setName(dynastyName);
    }


    public List<Event> loadDataJson() throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/java/json/Event_Wiki.json"));
        List<Event> eventList = Arrays.asList(gson.fromJson(reader, Event[].class));

        return eventList;
    }
    @Override
    public String hienthi() {
        return  "Tên: " + this.getName() + "\n" + "Thời gian: " + this.getTime() + "\n" + "Triều đại: " + this.dynasty.getName() + "\n" + "Chi tiết: " + this.getDescription();
    }

}
