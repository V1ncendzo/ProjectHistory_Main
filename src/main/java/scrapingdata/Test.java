package scrapingdata;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import scrapingdata.entity.Festival;
import scrapingdata.entity.Relic;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/java/json/Festival_Wiki.json"));
        List<Festival> festivalList =  Arrays.asList(gson.fromJson(reader, Festival[].class));
        reader.close();

    }


}
