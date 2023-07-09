package collection;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Event {

    public List<scrapingdata.entity.Event> loadDataJson() throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/java/json/Event.json"));
        List<scrapingdata.entity.Event> eventList = Arrays.asList(gson.fromJson(reader, scrapingdata.entity.Event[].class));

        return eventList;
    }
}
