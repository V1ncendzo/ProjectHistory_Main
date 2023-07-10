package scrapingdata.relation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import scrapingdata.entity.*;
import scrapingdata.entity.Character;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FigureRelateToFestival {
    public List<Festival> loadDataJsonFestival() throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/java/json/Festival_Wiki.json"));
        List<Festival> festivalList =  Arrays.asList(gson.fromJson(reader, Festival[].class));
        reader.close();

        return festivalList;
    }
    public List<Character> loadDataJsonFigure() throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/java/json/Figure.json"));
        List<Character> figureList = Arrays.asList(gson.fromJson(reader, Character[].class));
        reader.close();

        return figureList;
    }


    public Map<Festival, List<Character>> Relating(List<Character> figureList, List<Festival> festivalList) {
        Map<Festival, List<Character>> map = new HashMap<>();
        for(Festival fes : festivalList){
            boolean check = false;
            List<Character> relatedList = new ArrayList<>();
            String chara = fes.getCharacter();
            for(Character c : figureList){
                String cName = c.getName();
                if (chara == null){
                    continue;
                }
                if(chara.contains(cName)){
                    relatedList.add(c);
                }
            }
            map.put(fes, relatedList);
        }
        return map;
    }

    public static void main(String[] args) throws IOException {
        FigureRelateToFestival rl = new FigureRelateToFestival();
        List<Festival>  listFes = rl.loadDataJsonFestival();
        List<Character> listChar = rl.loadDataJsonFigure();
        Map<Festival, List<Character>> maping = rl.Relating(listChar,listFes);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(maping);
        try (FileWriter writer = new FileWriter("src\\main\\java\\json\\Relating2.json")) {
            writer.write(json);
            System.out.println("JSON data has been written to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
