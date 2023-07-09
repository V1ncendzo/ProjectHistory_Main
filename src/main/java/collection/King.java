package collection;

import com.google.gson.Gson;
import scrapingdata.entity.KingWiki;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class King {
    public List<KingWiki> loadDataJson() throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/java/json/King_Wiki.json"));
        List<KingWiki> kingWikiList = Arrays.asList(gson.fromJson(reader, KingWiki[].class));
        reader.close();

        return kingWikiList;
    }
}
