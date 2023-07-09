package collection;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Festival {
    public List<scrapingdata.entity.Festival> loadDataJson() throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/java/json/Festival_Wiki.json"));
        List<scrapingdata.entity.Festival> festivalList = Arrays.asList(gson.fromJson(reader, scrapingdata.entity.Festival[].class)); //dks is arraylist of festival
        reader.close();
        System.out.println(gson.toJson(festivalList));

        return festivalList;
    }
}
