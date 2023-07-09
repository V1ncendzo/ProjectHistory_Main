package collection;

import com.google.gson.Gson;
import scrapingdata.entity.Character;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class HistoricalFigures {
    public List<Character> loadDataJson() throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/java/json/Figure.json"));
        List<Character> characterList = Arrays.asList(gson.fromJson(reader, Character[].class));
        reader.close();
        return characterList;
    }
}
