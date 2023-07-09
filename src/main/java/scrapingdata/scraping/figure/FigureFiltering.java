package scrapingdata.scraping.figure;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import scrapingdata.entity.Character;
import scrapingdata.entity.KingWiki;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FigureFiltering {
    public List<Character> loadDataJsonNKS() throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/java/json/Figure_NKS.json"));
        List<Character> NKS = Arrays.asList(gson.fromJson(reader, Character[].class));
        reader.close();

        return NKS;
    }
    public List<Character> loadDataJsonVanSu() throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/java/json/Figure_VanSu.json"));
        List<Character> VanSu = Arrays.asList(gson.fromJson(reader, Character[].class));
        reader.close();

        return VanSu;
    }
    public List<KingWiki> loadDataJsonKingWiki() throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/java/json/King_Wiki.json"));
        List<KingWiki> kingList = Arrays.asList(gson.fromJson(reader, KingWiki[].class));
        reader.close();

        return kingList;
    }

    public List<Character> Aggregation(List<Character> NKS, List<Character> VanSu, List<KingWiki> kingList) {
        List<Character> nv = new ArrayList<Character>();
        int numb = 0;
        int NKSnumb = 0;
        int VSnumb = 0;
        int kingNumb = 0;
        for(Character e : NKS ) {
            NKSnumb++;
            boolean ktra = false;
            String name1 = e.getName();
            for(Character f : VanSu) {
//                System.out.println(f.hienthi());
                String name2 = f.getName();
                if(name1.equals(name2)) {
                    ktra = true;
                    String name = name2;
                    String time = null;
                    String timeNKS = e.getTime();
                    String timeVS = f.getTime();
                    time = timeVS;
                    if(timeNKS.equals("Không rõ")||timeNKS.equals("Không rõ---Không rõ")) time = timeVS;
                    if(timeVS.equals("Không rõ")) time = timeNKS;

                    String depcription = e.getDescription();
                    String aotherName = f.getotherName();
//                    String place = f.getPlace();
//                    List<String> era = f.getEra();
                    Character nvNew = new Character(name,time,depcription,aotherName);
                    nv.add(nvNew);

                    numb++;

                    break;
                }
            }
            if(ktra == false) {
                nv.add(e);
                numb++;

            }
        }
        for(Character f: VanSu) {
            VSnumb++;
            boolean ktra = false;
            String name1 = f.getName();
            for(Character e : nv) {
                String name2 = e.getName();
                if(name1.equals(name2)) {
                    ktra = true;
                    break;
                }
            }
            if(ktra == false) {
                numb++;
                nv.add(f);

            }
        }
        // Remove King
        for(KingWiki k : kingList ){
            String kName = k.getName();
            for(Character c : nv){
                String cName = c.getName();
                if(kName.equals(cName)){
                    kingNumb++;
                    nv.remove(c);
                }
            }
        }

        System.out.println(kingNumb);
        System.out.println(NKSnumb);
        System.out.println(VSnumb);
        System.out.println(numb);
        return nv;
    }




    public static void main(String[] args) throws IOException {
        FigureFiltering dA = new FigureFiltering();
        List<Character> listNKS = dA.loadDataJsonNKS();
        List<Character> listVS = dA.loadDataJsonVanSu();
        List<KingWiki> kingList = dA.loadDataJsonKingWiki();
        List<Character> nv = dA.Aggregation(listNKS, listVS, kingList );

        try (Writer file = new FileWriter("src\\main\\java\\json\\Figure.json")){
            file.write("[\n");
            for(Character e : nv) {
                if(e.getName() == null){
                    continue;
                }
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                gson.toJson(e, file);
                file.write(",\n");
            }
            file.write("]");
        }catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}

