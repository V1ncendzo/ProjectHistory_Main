package scrapingdata.scraping.dynasty;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import scrapingdata.entity.Character;
import scrapingdata.entity.Dynasty;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DynastyFiltering {
    public List<Dynasty> loadDataJsonDynastyNKS() throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/java/json/Dynasty_NKS.json"));
        List<Dynasty> NKS = Arrays.asList(gson.fromJson(reader, Dynasty[].class));
        reader.close();

        return NKS;
    }

    public List<Dynasty> loadDataJsonDynastyWiki() throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/java/json/Dynasty_Wiki.json"));
        List<Dynasty> wiki = Arrays.asList(gson.fromJson(reader, Dynasty[].class));
        reader.close();

        return wiki;
    }

    public List<Dynasty> Merging(List<Dynasty> NKS, List<Dynasty> Wiki) {
        List<Dynasty> merge = new ArrayList<>();
        int numb = 0;
        int wikiNumb = 0;
        int NKSnumb = 0;
        for (Dynasty d : NKS) {
            NKSnumb++;
            boolean check = false;
            String name1 = d.getName();
            for (Dynasty d2 : Wiki) {
                String name2 = d.getName();
                if (name1.equals(name2)) {
                    check = true;
                    String name = name2;
                    String kingdom = d2.getKingdom();
                    String capital = d2.getCapital();
                    String description = null;
                    String descriptionNKS = d.getDescription();
                    String descriptionWiki = d2.getDescription();
                    if (descriptionNKS == null && descriptionWiki == null) {
                        description = "không rõ";
                    } else if (descriptionWiki == null && descriptionNKS != null) {
                        description = descriptionNKS;
                    } else if (descriptionNKS == null && descriptionWiki != null) {
                        description = descriptionWiki;
                    }
                    Dynasty newDynasty = new Dynasty(name, kingdom, capital, description);
                    merge.add(newDynasty);
                    numb++;
                    break;
                }
            }
            if (check == false) {
                merge.add(d);
                numb++;
            }
        }
        for (Dynasty dWiki : Wiki) {
            wikiNumb++;
            boolean check2 = false;
            String nameWiki = dWiki.getName();
            for (Dynasty m : merge) {
                String nameNKS = m.getName();
                if (nameWiki.equals(nameNKS)) {
                    check2 = true;
                    break;
                }
            }
            if (check2 == false) {
                numb++;
                merge.add((dWiki));
            }
        }
        System.out.println(wikiNumb);
        System.out.println(NKSnumb);
        System.out.println(numb);
        return merge;
    }

    public static void main(String[] args) throws IOException {
        DynastyFiltering dF = new DynastyFiltering();
        List<Dynasty> dNKS = dF.loadDataJsonDynastyNKS();
        List<Dynasty> dWiki = dF.loadDataJsonDynastyWiki();
        List<Dynasty> merge = dF.Merging(dNKS, dWiki);
        try (Writer file = new FileWriter("src\\main\\java\\json\\Dynasty.json")) {
            file.write("[\n");
            for (Dynasty d : merge) {
                if (d.getName() == null) {
                    continue;
                }
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                gson.toJson(d, file);
                file.write(",\n");
            }
            file.write("]");
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}