package collection;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Dynasty {
    public List<scrapingdata.entity.Dynasty> loadDataJson() throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/java/json/Dynasty_NKS.json"));
        List<scrapingdata.entity.Dynasty> dynastyList = Arrays.asList(gson.fromJson(reader, scrapingdata.entity.Dynasty[].class));
        return dynastyList;
    }
}
