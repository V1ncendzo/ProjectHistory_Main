package collection;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Relic {
    public List<scrapingdata.entity.Relic> loadDataJson() throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/java/json/Relic.json"));
        List<scrapingdata.entity.Relic> relics = Arrays.asList(gson.fromJson(reader, scrapingdata.entity.Relic[].class));
        return relics;
    }
}
